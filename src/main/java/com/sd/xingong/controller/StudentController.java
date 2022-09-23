package com.sd.xingong.controller;


import com.sd.xingong.pojo.Student;
import com.sd.xingong.service.StudentService;
import com.sd.xingong.vo.StudentCount;
import com.sd.xingong.vo.StudentResult;
import com.sd.xingong.vo.StudentVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 分页获取所有学生信息   该方法废了
     * @return
     */
    @GetMapping
    public List<Student> getStudents(@RequestParam("startIndex") int startIndex, @RequestParam("pageSize") int pageSize){
    //    System.out.println(startIndex+ "====" +pageSize);

        List<Student> students = studentService.getStudents(startIndex,pageSize);
        return  students;
    }

    /**
     * 根据学生的 姓名 、 学号、 论文名称 进行模糊查询
     * @param name
     * @param studentId
     * @param title
     * @return
     */
    @GetMapping("/search")
    public StudentCount searchStudents(@RequestParam("name") String name, @RequestParam("studentId") String studentId, @RequestParam("title") String title,@RequestParam("startIndex") String startIndex, @RequestParam("pageSize") String pageSize){
                //获取所有学生列表
               StudentCount students = studentService.searchStudents(name,studentId,title,Integer.parseInt(startIndex),Integer.parseInt(pageSize));

                return  students;
        }

    /**
     * 学生登录， 接收的为封装好的StudentVo类 返回的为StudentResult类
     * @param studentVo
     * @return
     */
    @PostMapping("/login")
    public StudentResult studentLogin(@RequestBody StudentVo studentVo){
        StudentResult student = studentService.studentLogin(studentVo.getStudentId(),studentVo.getPassword());
        return student;
    }

    /**
     * 学生选择老师
     * @param teacherId
     * @param studentId
     * @return
     */
    @GetMapping("/select")
    public Boolean studentSelectTeacher(@RequestParam("teacherId") String teacherId, @RequestParam("studentId") String studentId){
        //学生选择老师，需要向老师发送请求，如果老师同意才可以选择成功, 学生中有一个字段来表示是否已经有导师
        Boolean result = studentService.studentSelectTeacher(Integer.parseInt(teacherId),studentId);
        return  result;
    }
}
