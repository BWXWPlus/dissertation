package com.sd.xingong.controller;


import com.sd.xingong.pojo.Student;
import com.sd.xingong.service.StudentService;
import com.sd.xingong.vo.DissertationVo;
import com.sd.xingong.vo.Login;
import com.sd.xingong.vo.StudentCount;
import com.sd.xingong.vo.StudentResult;
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
     * @param
     * @return
     */
    @PostMapping("/login")
    public StudentResult studentLogin(@RequestBody Login login){
        StudentResult student = studentService.studentLogin(login.getId(),login.getPassWord());
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

    /**
     * 学生编辑自己的论文信息
     * @param
     * @return
     */
    @PutMapping
    public Boolean editDissertation(@RequestBody Student student){
        Boolean result = studentService.editDissertation(student);
        System.out.println(student);
        return result;
    }

}
