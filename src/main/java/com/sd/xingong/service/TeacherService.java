package com.sd.xingong.service;

import com.sd.xingong.pojo.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getTeachers(int startIndex, int pageSize);
}
