package com.geektcp.alpha.driver.mybatis.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.geektcp.alpha.driver.mybatis.model.Student;
import com.geektcp.alpha.driver.mybatis.dao.StudentDao;
import com.geektcp.alpha.driver.mybatis.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Override
    public boolean addStudent(Student student) {

        if (queryStudentByIdCardId(student.getIdcardId()) == null) {
            return this.insert(student);
        }

        return true;
    }

    @Override
    public Student queryStudentByIdCardId(Long idCardId) {
        Wrapper<Student> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq(Student.IDCARD_ID, idCardId);
        List<Student> studentList = this.selectList(queryWrapper);
        if (studentList == null || studentList.isEmpty()) {
            return null;
        }
        if (studentList.size() > 1) {
            log.error("queryStudentByIdCardId 有多个结果，idCardId={}", idCardId);
        }
        return studentList.get(0);
    }



    // 通过名字进行筛选
    @Override
    public void test1() {
        Wrapper<Student> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq("name", "冯文议");
        List<Student> studentList = this.selectList(queryWrapper);
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
    }



    @Override
    public void test2() {
        Wrapper<Student> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq("name", "冯文议")
//                .eq(Student::getAge, 26)
                .eq("age", 25);
        List<Student> studentList = this.selectList(queryWrapper);
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
    }

    @Override
    public void test3() {
        Wrapper<Student> queryWrapper = new EntityWrapper<>();
        queryWrapper.and();
        queryWrapper.eq("name", "冯文议");
        queryWrapper.eq("age", 26);

        List<Student> studentList = this.selectList(queryWrapper);
        for (Student student : studentList) {
            log.info(JSON.toJSONString(student));
        }
    }

    @Override
    public void test4() {
        Wrapper<Student> queryWrapper = new EntityWrapper<>();
        queryWrapper.or();
        queryWrapper.eq("name", "冯文议");
        queryWrapper.or("age", "1");
        List<Student> studentList = selectList(queryWrapper);
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
    }


    @Resource
    StudentDao studentDao;

    @Override
    public void selectAll() {
        Page page = new Page();
        List<Student> studentList = studentDao.selectAll(page);
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
    }



    @Override
    public Student findOne() {
        return selectOne(null);
    }

    @Override
    public Student findById(Long id) {
        return selectById(id);
    }

    @Override
    public List<Student> findByNameAndAge(String name, Integer age) {
        Wrapper<Student> lambdaQueryWrapper = new EntityWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            lambdaQueryWrapper.eq("age", name);
        }

        if (age != null) {
            lambdaQueryWrapper.eq("age", age);
        }

        List<Student> studentList = selectList(lambdaQueryWrapper);
        for (Student student : studentList) {
            log.info(JSON.toJSONString(student));
        }
        return studentList;
    }

    @Override
    public List<Student> findList() {
        return null;
    }
}
