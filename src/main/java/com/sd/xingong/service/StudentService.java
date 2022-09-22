package com.sd.xingong.service;

import com.sd.xingong.pojo.Student;
import com.sd.xingong.vo.StudentResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {

    List<Student> getStudents(int startIndex,int pageSize);

    StudentResult studentLogin(long studentId, String password);

    List<Student> searchStudents(String name, String studentId, String title);
}
