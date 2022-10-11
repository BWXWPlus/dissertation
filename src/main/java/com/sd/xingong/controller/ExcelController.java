package com.sd.xingong.controller;

import com.sd.xingong.mapper.TeacherMapper;
import com.sd.xingong.pojo.Student;
import com.sd.xingong.pojo.Teacher;
import com.sd.xingong.service.StudentService;
import com.sd.xingong.service.TeacherService;
import com.sd.xingong.util.ExcelUtils;
import com.sd.xingong.vo.StudentCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ExcelController {


    Map<Integer,String> map2 = new HashMap();

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/excel/getAll")
    public String getStudents(@RequestParam("teacherName") String teacherName , @RequestParam("stuClass") String stuClass){


        StudentCount studentCount = studentService.searchStudents("", "", "", stuClass, teacherName, 1, 100000);
        List<Student> students = studentCount.getStudent();
        List<Map<String, Object>> mapArrayList = new ArrayList<>();
         int j =1;
        for (Student student : students) {

            Map<String, Object> map = new HashMap<>();

            map.put("学生姓名", student.getName());
            map.put("学号", student.getStudentId());
            if( student.getSpecialities() != null){
                map.put("专业", student.getSpecialities());
            }else {
                map.put("专业", "无");
            }

            map.put("班级", student.getStuClass());
            if(student.getTitle() != null){
                map.put("设计（论文）题目", student.getTitle());
            }else {
                map.put("设计（论文）题目", "无");
            }
            if(student.getThesisType() != null){
                map.put("成果类别(论文/设计)", student.getThesisType());
            }else {
                map.put("成果类别(论文/设计)", "无");
            }
         /*   if(student.getNatureTopicSelection() ==0){
                map.put("选题性质(开题报告写法一致)", "无");
            }else if(student.getNatureTopicSelection() == 1){
                map.put("选题性质(开题报告写法一致)", "理论研究");
            }else if(student.getNatureTopicSelection() == 2){
                map.put("选题性质(开题报告写法一致)", "应用研究");
            }else if(student.getNatureTopicSelection() == 3){
                map.put("选题性质(开题报告写法一致)", "技术开发");
            }else if(student.getNatureTopicSelection() == 4){
                map.put("选题性质(开题报告写法一致)", "工程设计");
            }else {

            }*/
            map.put("选题性质(开题报告写法一致)", "技术开发");
         /*   if(student.getSourceTopicSelection() == 0){
                map.put("选题来源(开题报告写法一致)", "无");
            }else  if(student.getSourceTopicSelection() == 1){
                map.put("选题来源(开题报告写法一致)", "指定选题");
            }else  if(student.getSourceTopicSelection() == 2){
                map.put("选题来源(开题报告写法一致)", "自拟选题");
            }else {

            }*/
            if(student.getSourceTopicSelection() != null){
                map.put("选题来源(开题报告写法一致)", student.getSourceTopicSelection());
            }else {
                map.put("选题来源(开题报告写法一致)", "无");
            }


            map.put("序号", j);
            j++;
            Teacher teacher = teacherService.getATeacherById((int) student.getMentorId());
            Teacher teacher2 = teacherService.getATeacherById((int) student.getMentor2Id());
            if(teacher != null){
                map.put("指导教师(姓名)", teacher.getName());
                if(teacher.getTeacherTitle() != null){
                    map.put("指导教师(职称)", teacher.getTeacherTitle());
                }else {
                    map.put("指导教师(职称)", "无");
                }

            }else {
                map.put("指导教师(姓名)", "无");
                map.put("指导教师(职称)", "无");
            }
            if(teacher2 != null){
                if(teacher.getName() != null){
                    map.put("企业导师(姓名)", teacher2.getName());
                }else {
                    map.put("企业导师(姓名)", "无");
                }
                if(teacher2.getTeacherTitle() != null){
                    map.put("企业导师(职称)",teacher2.getTeacherTitle());
                }else {
                    map.put("企业导师(职称)","无");
                }

            }else {
                map.put("企业导师(姓名)", "无");
                map.put("企业导师(职称)","无");
            }

            mapArrayList.add(map);
        }
        System.out.println(mapArrayList);
        ExcelUtils.getInstance().createExcel(mapArrayList,"data","2023届本科毕业设计（论文）题目汇总");

        return  "下载成功";
    }
}
