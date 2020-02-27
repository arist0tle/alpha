package com.lik.upload.mutipartupload.mapper;

import com.lik.upload.mutipartupload.bean.AttachmentDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lk
 */
@Mapper
public interface AttachmentMapper {

    void insert(AttachmentDetail attachment);

    int findOne(AttachmentDetail attachmentDetail);
}
