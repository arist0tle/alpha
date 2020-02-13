package com.geektcp.alpha.driver.mybatis.business;

import com.geektcp.alpha.driver.mybatis.model.City;
import com.geektcp.alpha.driver.mybatis.model.Card;
import com.geektcp.alpha.driver.mybatis.model.Student;

/**
 * 数据库表关联逻辑处理接口
 *
 * @author tanghaiyang
 * @since 2018-09-01
 */
public interface AppBusiness {

    boolean addStudent(Student student, City city, Card card);

}
