package com.sd.xingong.service.impl;

import com.sd.xingong.mapper.StudentMapper;
import com.sd.xingong.pojo.Student;
import com.sd.xingong.service.StudentService;
import com.sd.xingong.vo.StudentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> getStudents(int startIndex,int pageSize) {
        //计算转换 ， 方便limit使用
        startIndex = pageSize * (startIndex - 1);
        List<Student> students = studentMapper.getStudents(startIndex, pageSize);
        //TODO 先将密码置为空，将所有数据都返回给前端，后期再设实体类来去除密码字段
        for(Student student : students){
            student.setPassword("");
        }
        return  students;
    }

    @Override
    public StudentResult studentLogin(long studentId, String password) {

        Student student = studentMapper.studentLogin(studentId,password);

        //如果学生用户名密码正确，则返回学生详细信息以及一个token
        if(student != null){
            String uuid = UUID.randomUUID().toString();
            student.setPassword("");
            return new StudentResult(uuid,student);
        }
        //没有找到则返回空
        return null;

    }

    @Override
    public List<Student> searchStudents(String name, String studentId, String title) {
        //字符拼接方便模糊查询
        name = "%" + name + "%";
        studentId = "%" + studentId + "%";
        title = "%" + title + "%";
        List<Student> students =  studentMapper.searchStudents(name,studentId,title);
        for(Student student : students){
            student.setPassword("");
        }
        return  students;
    }
}
