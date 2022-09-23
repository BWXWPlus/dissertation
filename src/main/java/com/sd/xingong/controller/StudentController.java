package com.sd.xingong.controller;


import com.sd.xingong.pojo.Student;
import com.sd.xingong.service.StudentService;
import com.sd.xingong.vo.StudentResult;
import com.sd.xingong.vo.StudentVo;
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
     * 分页获取所有学生信息
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
    public List<Student> searchStudents(@RequestParam("name") String name, @RequestParam("studentId") String studentId, @RequestParam("title") String title){
            List<Student> students = studentService.searchStudents(name,studentId,title);
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


}
