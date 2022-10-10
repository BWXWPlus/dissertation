package com.sd.xingong.service.impl;

import com.sd.xingong.mapper.StudentMapper;
import com.sd.xingong.mapper.TeacherMapper;
import com.sd.xingong.pojo.Student;
import com.sd.xingong.pojo.Teacher;
import com.sd.xingong.service.StudentService;
import com.sd.xingong.service.TeacherService;
import com.sd.xingong.vo.StudentCount;
import com.sd.xingong.vo.StudentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
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
    public StudentResult studentLogin(String studentId, String password) {

        Student student = studentMapper.studentLogin(studentId,password);

        //如果学生用户名密码正确，则返回学生详细信息以及一个token
        if(student != null){
            String uuid = UUID.randomUUID().toString();
            student.setPassword("");
            return new StudentResult(uuid,student);
        }
        //没有找到则返回空
        return new StudentResult();

    }

    @Override
    public StudentCount searchStudents(String name, String studentId, String title,String stuClass,String teacherName, int startIndex, int pageSize) {
        //字符拼接方便模糊查询
        name = "%" + name + "%";
        studentId = "%" + studentId + "%";
        title = "%" + title + "%";
        stuClass = "%" + stuClass + "%";
        teacherName = "%" + teacherName + "%";
        //计算转换 ， 方便limit使用
        startIndex = pageSize * (startIndex - 1);
        List<Student> students =  studentMapper.searchStudents(name,studentId,title,stuClass,teacherName,startIndex,pageSize);

        for(Student student : students){
            student.setPassword("");
        }

        //统计所有的学生总数

        int count =  studentMapper.getStudentCount(name,studentId,title,stuClass,teacherName);
        StudentCount studentCount = new StudentCount(count,students);
        return  studentCount;
    }

    @Override
    public Boolean studentSelectTeacher(int teacherId, String studentId) {
        //同样，为了保险起见，学生选择老师也判断一次该老师名下的学生是否已满， 以及该学生是否已经有导师了
        //首先要先判断该导师是否已经选满了20人，如果已经选满20人，则不能再选学生
        Teacher teacher = teacherMapper.getATeacher(teacherId);
        if(teacher.getStudentNum() >= 25){
            return  false;
        }
        //再判断该学生是否已经有导师了，，严谨一点
        Student student = studentMapper.getAStudent(studentId);

        if(student == null ||student.getMentorId() > 0){
            return false;
        }
        //如果学生还未选择导师，则可以向导师发送请求
        //改变数据库的 MentorId， 表示学生想选择该老师，之后等待老师的确定
        studentMapper.updateMentorId(teacherId,studentId);

        return true;
    }

    @Override
    public Boolean editStudentInformation(Student student) {


        int result = studentMapper.editStudentInformation(student);
        if(result > 0 ){
            return  true;
        }
        return false;
    }

    @Override
    public String editPassWord(String id, String newPassword, String oldPassword) {
        Student student = studentMapper.studentLogin(id, oldPassword);
        if(student == null){
            return "旧密码不正确";
        }
        if(oldPassword.equals(newPassword)){
            return "新旧密码相同";
        }
        int result = studentMapper.editPassWord(id,newPassword);
        if(result > 0){
            return "修改成功";
        }
        return "密码修改失败";
    }
}
