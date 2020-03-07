package com.geektcp.alpha.tool.upload.dao;

import com.geektcp.alpha.tool.upload.bean.FileObj;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Author lk
 */
public interface AttachmentDao extends JpaRepository<FileObj,String> {

   void findById();
}
