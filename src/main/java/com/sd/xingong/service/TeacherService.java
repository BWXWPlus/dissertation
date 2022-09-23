package com.sd.xingong.service;

import com.sd.xingong.pojo.Teacher;
import com.sd.xingong.vo.TeacherCount;

import java.util.List;

public interface TeacherService {
    TeacherCount getTeachers(String name , int startIndex, int pageSize);

    Boolean teacherSelectStudents(int teacherId, String studentId);
}
