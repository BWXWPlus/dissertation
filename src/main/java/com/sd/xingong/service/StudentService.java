package com.sd.xingong.service;

import com.sd.xingong.pojo.Student;
import com.sd.xingong.vo.StudentCount;
import com.sd.xingong.vo.StudentResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {

    List<Student> getStudents(int startIndex,int pageSize);

    StudentResult studentLogin(String studentId, String password);

    StudentCount searchStudents(String name, String studentId, String title, int startIndex, int pageSize);

    Boolean studentSelectTeacher(int teacherId, String studentId);

    Boolean editStudentInformation(Student student);
}
