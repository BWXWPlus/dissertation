package com.sd.xingong.controller;

import com.sd.xingong.pojo.Student;
import com.sd.xingong.pojo.Teacher;
import com.sd.xingong.service.TeacherService;
import com.sd.xingong.vo.TeacherCount;
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

    /**
     * 分页显示所有教师信息
     * @param startIndex
     * @param pageSize
     * @return
     */
    @GetMapping
    public TeacherCount getTeachers(@RequestParam("name") String name, @RequestParam("startIndex") String startIndex, @RequestParam("pageSize") String pageSize){

        TeacherCount teachers = teacherService.getTeachers(name,Integer.parseInt(startIndex),Integer.parseInt(pageSize));
        return  teachers;
    }

    /**
     * 老师挑选学生
     * @param teacherId  teacherId  应为 Id  不能为工号， 因为有的外聘老师没有工号
     * @param studentId  studentId  用学号来表示
     * @return
     */
    @GetMapping("/select")
    public Boolean teacherSelectStudents(@RequestParam("teacherId") int teacherId, @RequestParam("studentId") String studentId){
        //教师选学生，选择成功返回true否则就是false
       Boolean result =  teacherService.teacherSelectStudents(teacherId,studentId);
       return result;
    }
}
