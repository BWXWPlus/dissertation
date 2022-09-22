package com.sd.xingong.controller;

import com.sd.xingong.pojo.Student;
import com.sd.xingong.pojo.Teacher;
import com.sd.xingong.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//教师相关操作
@RestController
@CrossOrigin
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> getTeachers(@RequestParam("startIndex") int startIndex, @RequestParam("pageSize") int pageSize){

        List<Teacher> teachers = teacherService.getTeachers(startIndex,pageSize);
        return  teachers;
    }
}
