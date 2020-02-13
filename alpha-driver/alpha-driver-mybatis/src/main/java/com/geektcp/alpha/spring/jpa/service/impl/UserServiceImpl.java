package com.geektcp.alpha.spring.jpa.service.impl;

import com.geektcp.alpha.common.spring.jpa.JpaBase;
import com.geektcp.alpha.spring.jpa.domain.User;
import com.geektcp.alpha.spring.jpa.repository.UserJpaRepository;
import com.geektcp.alpha.spring.jpa.repository.UserRepository;
import com.geektcp.alpha.spring.jpa.service.IUserService;
import com.querydsl.core.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl extends JpaBase implements IUserService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    public List<User> findByName(String name) {
        List<User> userList1 = userRepository.findByName1(name);
        List<User> userList2 = userRepository.findByName2(name);
        List<User> userList3 = userRepository.findByNameAndAddress(name, "3");
        log.info("userList1:" + userList1);
        log.info("userList2:" + userList2);
        log.info("userList3:" + userList3);
        return userRepository.findByName(name);
    }

    public void saveUser(User book) {
        userJpaRepository.save(book);
    }

    @Cacheable("users")
    public User findOne(long id) {
        log.info("Cached Pages");
        return userJpaRepository.findOne(id);
    }

    public void delete(long id) {
        userJpaRepository.delete(id);
    }


}
