package com.geektcp.alpha.tool.upload.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * @author tanghaiyang on 2019/10/13 9:43.
 */
@Controller
@Slf4j
public class UploadController {

    private static final String SAVE_PATH = "F:\\tmp\\upload";

    @PostMapping("/upload")
    @ResponseBody
    public JSONObject upload(MultipartFile file, String name) {
        try {
            File destFile = new File(SAVE_PATH, name);
            boolean isRead = destFile.setReadable(true);
            boolean isWrite = destFile.setWritable(true);
            boolean isExecute = destFile.setExecutable(true);
            boolean isRWE= isRead && isWrite && isExecute;
            if(!isRWE){
                log.error("no permission!");
            }
           boolean isNewFile =  destFile.createNewFile();
            if(isNewFile) {
                file.transferTo(destFile);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        JSONObject result = new JSONObject();
        result.put("msg","successful");
        return result;
    }
//
//    @PostMapping("offset")
//    public ResponseEntity offset(String md5, String name) {
//        Optional<FileObj> fileObjOpt = null;
////        Optional<FileObj> fileObjOpt = attachmentDao.findById(md5);
//        ResponseEntity<Object> resultEntity = null;
//        if (fileObjOpt.isPresent()) {
//            FileObj fileObj = fileObjOpt.get();
//            if (fileObj.getIsmerge() == 1) {
//                resultEntity = ResponseEntity.ok("100");
//            } else {
//                Double dChunk = Double.valueOf(fileObj.getChunk());
//                Double Dchunks = Double.valueOf(fileObj.getChunkNum());
//                Double percent = dChunk / Dchunks * 100;
//                resultEntity = ResponseEntity.ok(percent);
//            }
//        } else {
//            resultEntity = ResponseEntity.ok("0");
//        }
//        //每次上传时检查下当前名称和md5相同的文件是否存在
//        AttachmentDetail attachmentDetail = new AttachmentDetail();
//        attachmentDetail.setName(name);
//        attachmentDetail.setMd5(md5);
//        Example<AttachmentDetail> exple = Example.of(attachmentDetail);
////        Optional<AttachmentDetail> attachmentOp = attachmentDetailDao.findOne(exple);
////        if(!attachmentOp.isPresent()){
////            attachmentDetail.setId(UUID.randomUUID().toString().replace("-",""));
////            attachmentDetailDao.save(attachmentDetail);
////        }
//        return resultEntity;
//    }
//
//    private void saveBaseInfo(File filePath, FileObj fileObj) throws IOException {
//        File fileInfo = new File(filePath, "/fileInfo.txt");
//        if (!fileInfo.exists()) {
//            fileInfo.createNewFile();
//            BufferedOutputStream bufferedOutputStream = null;
//            try {
//                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileInfo));
//                bufferedOutputStream.write(fileObj.toString().getBytes());
//                bufferedOutputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//                bufferedOutputStream.close();
//            }
//        }
//    }
//
//    private void saveBaseInfo2DS(File filePath, FileObj fileObj) {
//        Optional<FileObj> byId = null;
////        Optional<FileObj> byId = attachmentDao.findById(fileObj.getMd5());
//        fileObj.setId(fileObj.getMd5());
//        fileObj.setPath(filePath.getPath());
//        if (!byId.isPresent()) {
//            attachmentDao.save(fileObj);
//        } else {
//            if (fileObj.getChunkNum() == fileObj.getChunk() + 1) {
//                fileObj.setIsmerge(2);
//            }
//            attachmentDao.save(fileObj);
//        }
//    }
//
//
//    public File getPathFile(String md5) {
//        String firstPath = md5.substring(0, 2);
//        String secondPath = md5.substring(2, 4);
//        String finalPath = md5;
//        return new File(panProperties.getUploadDir() + firstPath + "/" + secondPath + "/" + finalPath);
//    }
}
