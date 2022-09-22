package com.sd.xingong.service;

import com.sd.xingong.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {

    List<Student> getStudents();
}
