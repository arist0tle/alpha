package com.geektcp.alpha.driver.mybatis.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.geektcp.alpha.driver.mybatis.model.City;
import com.geektcp.alpha.driver.mybatis.dao.CityDao;
import com.geektcp.alpha.driver.mybatis.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
@Service
@Slf4j
public class CityServiceImpl extends ServiceImpl<CityDao, City> implements CityService {

    @Override
    public List<City> queryCityAll() {
        return this.selectList(null);
    }

    @Override
    public boolean addCity(City city) {
        String name = city.getName();

        if (queryCityByName(name) == null)
            return this.insert(city);

        // 数据库已经存在
        return true;
    }

    @Override
    public City queryCityByName(String name) {
        Wrapper<City> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq(City.KEY, name);
        List<City> cityList = this.selectList(queryWrapper);
        if (cityList == null || cityList.isEmpty()) {
            return null;
        }
        if (cityList.size() > 1) {
            log.error("queryCityByName结果有多个，name={}", name);
        }
        return cityList.get(0);
    }
}
