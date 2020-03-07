package com.geektcp.alpha.tool.upload.config;

import com.geektcp.alpha.tool.upload.bean.FileObj;
import com.geektcp.alpha.tool.upload.mapper.UploadMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author lk
 * @Description: 合并文件的线程
 */
@Configuration
public class ApplicationStartUp implements ApplicationContextAware,ApplicationRunner {

    @Autowired
    private UploadMapper uploadMapper;

    public void mergeFile(FileObj fileObj){
        Integer lenth = fileObj.getChunkNum();
        File[] fpaths =  new File[fileObj.getChunkNum()];
        for (Integer i=0;i<lenth;i++){
            fpaths[i] = new File(fileObj.getPath(),i.toString());
        }
        String resultFile = fileObj.getPath() + "/" + fileObj.getName();
        try {
            int bufSize = 1024;
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(resultFile));
            byte[] buffer = new byte[bufSize];

            for (int i = 0; i < fpaths.length; i ++) {
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fpaths[i]));
                int readcount;
                while ((readcount = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, readcount);
                }
                inputStream.close();
            }
            outputStream.close();
            uploadMapper.setMergeComplete(fileObj.getId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Stream.of(fpaths).forEach(File::delete);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        UploadMapper uploadMapper = applicationContext.getBean(UploadMapper.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (ApplicationStartUp.class){
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    List<FileObj> fileObjList = uploadMapper.findNotMerges();
                    for (FileObj fileObj : fileObjList) {
                        mergeFile(fileObj);
                    }
                }
            }
        };
        runnable.run();
    }
}
