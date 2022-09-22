package com.sd.xingong.service.impl;

import com.sd.xingong.mapper.StudentMapper;
import com.sd.xingong.pojo.Student;
import com.sd.xingong.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> getStudents() {
        return studentMapper.getStudents();
    }
}
