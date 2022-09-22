package com.sd.xingong.controller;


import com.sd.xingong.pojo.Student;
import com.sd.xingong.service.StudentService;
import com.sd.xingong.vo.StudentResult;
import com.sd.xingong.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//学生相关操作
@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<Student> getStudents(){
        //获取所有学生的列表
        List<Student> students = studentService.getStudents();
        return  students;
    }
    @PostMapping("/login")
    public StudentResult studentLogin(@RequestBody StudentVo studentVo){
        StudentResult student = studentService.studentLogin(studentVo.getStudentId(),studentVo.getPassword());
        return student;
    }
}
