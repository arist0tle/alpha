package com.geektcp.alpha.driver.mybatis.service;

import com.geektcp.alpha.driver.mybatis.model.City;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface CityService {

    List<City> queryCityAll();

    boolean addCity(City city);

    City queryCityByName(String name);

}
