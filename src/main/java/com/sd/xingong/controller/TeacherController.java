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

    /**
     * 分页显示所有教师信息
     * @param startIndex
     * @param pageSize
     * @return
     */
    @GetMapping
    public List<Teacher> getTeachers(@RequestParam("startIndex") int startIndex, @RequestParam("pageSize") int pageSize){

        List<Teacher> teachers = teacherService.getTeachers(startIndex,pageSize);
        return  teachers;
    }

    /**
     * 老师挑选学生
     * @param teacherId
     * @param studentId
     * @return
     */
    @GetMapping("/select")
    public Boolean teacherSelectStudents(@RequestParam("teacherId") int teacherId, @RequestParam("studentId") String studentId){
        //教师选学生，选择成功返回true否则就是false
       Boolean result =  teacherService.teacherSelectStudents(teacherId,studentId);
       return result;
    }
}
