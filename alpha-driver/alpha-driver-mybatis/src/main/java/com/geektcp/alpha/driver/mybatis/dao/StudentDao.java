package com.geektcp.alpha.driver.mybatis.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.geektcp.alpha.driver.mybatis.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface StudentDao extends BaseMapper<Student> {

    List<Student> selectAll(@Param("page") Page page);

}
