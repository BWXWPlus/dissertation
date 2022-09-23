package com.sd.xingong.service;

import com.sd.xingong.pojo.Teacher;
import com.sd.xingong.vo.TeacherCount;
import com.sd.xingong.vo.TeacherResult;

import java.util.List;

public interface TeacherService {
    TeacherCount getTeachers(String name , int startIndex, int pageSize);

    Boolean teacherSelectStudents(int teacherId, String[] studentIds);

    TeacherResult teacherLogin(String id, String passWord);

    Boolean teacherRefuseStudent(String studentId);
}
