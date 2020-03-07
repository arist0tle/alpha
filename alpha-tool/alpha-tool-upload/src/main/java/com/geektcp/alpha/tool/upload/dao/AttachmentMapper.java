package com.geektcp.alpha.tool.upload.dao;

import com.geektcp.alpha.tool.upload.bean.AttachmentDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lk
 */
@Mapper
public interface AttachmentMapper {

    void insert(AttachmentDetail attachment);

    int findOne(AttachmentDetail attachmentDetail);
}
