package com.sd.xingong.controller;


import com.sd.xingong.pojo.Student;
import com.sd.xingong.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<Student> getStudents(){

        List<Student> students = studentService.getStudents();
        return  students;
    }
}
