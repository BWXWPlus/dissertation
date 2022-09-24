package com.sd.xingong.controller;

import com.sd.xingong.pojo.Student;
import com.sd.xingong.pojo.Teacher;
import com.sd.xingong.service.TeacherService;
import com.sd.xingong.vo.Login;
import com.sd.xingong.vo.TeacherCount;
import com.sd.xingong.vo.TeacherResult;
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
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    @GetMapping
    public TeacherCount getTeachers(@RequestParam("name") String name, @RequestParam("startIndex") String startIndex, @RequestParam("pageSize") String pageSize) {

        TeacherCount teachers = teacherService.getTeachers(name, Integer.parseInt(startIndex), Integer.parseInt(pageSize));
        return teachers;
    }

    /**
     * 老师挑选学生
     *
     * @param teacherId teacherId  应为 Id  不能为工号， 因为有的外聘老师没有工号
     * @param studentId studentId  用学号来表示
     * @return
     */
    @GetMapping("/select")
    public Boolean teacherSelectStudents(@RequestParam("teacherId") String teacherId, @RequestParam("studentId") String studentId) {

        //studentId 可以批量传入
        String[] studentIds = studentId.split(",");
     /*   for(String s : studentIds){
            System.out.println(s);
        }*/
        //教师选学生，选择成功返回true否则就是false
        Boolean result = teacherService.teacherSelectStudents(Integer.parseInt(teacherId), studentIds);
        return result;
    }

    /**
     * 教师拒绝学生的选择
     * @param studentId
     * @return
     */

    @GetMapping("/refuse")
    public Boolean teacherRefuseStudent(@RequestParam("studentId") String studentId){
        //教师拒绝学生的请求，直接将学生的mentor_id字段置为 0 就行
        Boolean result = teacherService.teacherRefuseStudent(studentId);
        return  result;
    }

    /**
     * 教师登录
     * @param
     * @param
     * @return
     */
    @PostMapping("/login")
    public TeacherResult teacherLogin(@RequestBody Login login) {

        TeacherResult teacher = teacherService.teacherLogin(login.getId(), login.getPassWord());
        return teacher;
    }

    /**
     * 获取当前老师下的所有学生
     * @param id
     * @return
     */
    @GetMapping("/student")
    public List<Student> teacherGetStudents(@RequestParam("teacherId") String id,@RequestParam("isSelected") String isSelected){

        List<Student> students = teacherService.teacherGetStudents(Integer.parseInt(id),Integer.parseInt(isSelected));
        return  students;
    }

    /**
     * 根据教师的Id来获取老师的信息
     * @param teacherId
     * @return
     */
    @GetMapping("/get/one")
    public Teacher getATeacherById(@RequestParam("teacherId") String teacherId){
        Teacher teacher = teacherService.getATeacherById(Integer.parseInt(teacherId));
        return  teacher;
    }

}
