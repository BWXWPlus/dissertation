package com.sd.xingong.service.impl;

import com.sd.xingong.mapper.StudentMapper;
import com.sd.xingong.pojo.Student;
import com.sd.xingong.service.StudentService;
import com.sd.xingong.vo.StudentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> getStudents() {
        return studentMapper.getStudents();
    }

    @Override
    public StudentResult studentLogin(long studentId, String password) {
        Student student = studentMapper.studentLogin(studentId,password);
        if(student != null){
            String uuid = UUID.randomUUID().toString();
            return new StudentResult(uuid,student);
        }
        return null;

    }
}
