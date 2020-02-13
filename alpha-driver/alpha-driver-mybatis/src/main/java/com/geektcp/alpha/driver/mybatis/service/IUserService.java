package com.geektcp.alpha.driver.mybatis.service;

import com.geektcp.alpha.driver.mybatis.domain.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    void saveUser(User book);


    User findOne(long id);

    void delete(long id);

    List<User> findByName(String name);

}
