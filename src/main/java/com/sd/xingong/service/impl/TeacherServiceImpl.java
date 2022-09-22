package com.sd.xingong.service.impl;

import com.sd.xingong.mapper.TeacherMapper;
import com.sd.xingong.pojo.Teacher;
import com.sd.xingong.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getTeachers(int startIndex, int pageSize) {
        List<Teacher> teachers = teacherMapper.getTeachers(startIndex,pageSize);
        return teachers;
    }
}
