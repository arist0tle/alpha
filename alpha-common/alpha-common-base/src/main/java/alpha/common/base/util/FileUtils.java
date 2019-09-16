package alpha.common.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by tanghaiyang on 2018/1/26.
 */
@Slf4j
public class FileUtils {
    public static String CHARSET = "utf-8";

    public static <T> T readJSONObject(String fileName, Class<T> cls) {
        String path = FileUtils.class.getResource("/" + fileName).getPath();
        return JSON.parseObject(readTxt(path), cls);
    }

    public static List<Map<String, Object>> readListMap(String fileName) {
        String path = FileUtils.class.getResource("/" + fileName).getPath();
        return JSON.parseObject(readTxt(path), new TypeReference<List<Map<String, Object>>>() {
        });
    }

    public static String readTxt(String filePath) {
        StringBuilder result = new StringBuilder();
        try {
            File file = new File(filePath);
            if (!file.isFile() || !file.exists()) {
                return "";
            }
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), CHARSET);
            BufferedReader bufferedReader = new BufferedReader(read);

            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                result.append(lineTxt);
                result.append("\n");
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String readTxtFile(String fileName) {
        String path = FileUtils.class.getResource("/" + fileName).getPath();
        return readTxt(path);
    }

    public static <T> T readObject(String fileName, Class<T> objectType) {
        String path = FileUtils.class.getResource("/" + fileName).getPath();
        return JSON.parseObject(readTxt(path), objectType);
    }

    /**
     * Recursive access folder all the files below.
     *
     * @param filePath  file path
     */
    public static List<File> getFiles(String filePath) {
        List<File> listFile = new ArrayList<>();
        try {
            File file = new File(filePath);
            log.info("Start scanning folder: {} {}", file.getPath(), file.isDirectory());
            if (file.isDirectory()) {
                if (!filePath.endsWith(File.separator)) {
                    filePath += File.separator;
                }
                String[] files = file.list();
                for(String fileStr:files){
                    String strPath = filePath + fileStr;
                    File tmp = new File(strPath);
                    if (tmp.isDirectory()) {
                        getFileList(listFile, tmp);
                    } else {
                        listFile.add(tmp);
                    }
                }
            } else {
                listFile.add(file);
            }
        } catch (Exception e) {
            log.info("Read files has error due to: " + e.getMessage());
        }
        log.info("File list size: {}", listFile.size());
        return listFile;
    }

    private static void getFileList(List<File> list, File dir) {
        File[] files = dir.listFiles();
        if (Objects.isNull(files)) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                getFileList(list, file);
            } else {
                list.add(file);
            }
        }

    }

    public static double getDirSize(File file) {
        double size = 0.0;
        if (!file.exists()) {
            log.warn("File does not exists,{}", file.getPath());
            return size;
        }
        if (!file.isDirectory()) {
            return (double) file.length() / 1024 / 1024;
        }
        File[] children = file.listFiles();
        if (Objects.isNull(children)) {
            return size;
        }
        for (File f : children) {
            size += getDirSize(f);
        }
        return size;
    }

    /**
     * @return get resource absolute path
     */
    public static String getResourcePath() {
        return FileUtils.class.getResource("/").getPath();
    }
}
