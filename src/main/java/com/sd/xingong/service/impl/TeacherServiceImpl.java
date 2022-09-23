package com.sd.xingong.service.impl;

import com.sd.xingong.mapper.StudentMapper;
import com.sd.xingong.mapper.TeacherMapper;
import com.sd.xingong.pojo.Student;
import com.sd.xingong.pojo.Teacher;
import com.sd.xingong.service.TeacherService;
import com.sd.xingong.vo.TeacherCount;
import com.sd.xingong.vo.TeacherResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public TeacherCount getTeachers(String name , int startIndex, int pageSize) {
        //计算转换 ， 方便limit使用
        startIndex = pageSize * (startIndex - 1);
        name = "%" + name + "%";
        List<Teacher> teachers = teacherMapper.getTeachers(name,startIndex,pageSize);

        for(Teacher teacher : teachers){
            teacher.setPassword("");
        }

        int count = teacherMapper.getTeachersCount(name);

        return new TeacherCount(count,teachers);
    }

    @Override
    public Boolean teacherSelectStudents(int teacherId, String[] studentIds) {
        //首先要先判断该导师是否已经选满了20人，如果已经选满20人，则不能再选学生
        Teacher teacher = teacherMapper.getATeacher(teacherId);
        if(teacher.getStudentNum() >= 20){
            return  false;
        }
        for(String studentId : studentIds){
            //再判断该学生是否已经有导师了，，严谨一点
            Student student = studentMapper.getAStudent(studentId);

            if(student == null ||(student.getMentorId() > 0 && student.getMentorId() != teacherId)){
                return false;
            }
            //两点都符合，导师即可选学生, 即导师的studentNum +1  学生的MentorId有值
            //改变数据库的stuNum
            teacherMapper.updateStudentNum(teacherId);
            //改变数据库的 MentorId
            studentMapper.updateMentorId(teacherId,studentId);
            //改变学生的被选择状态
            studentMapper.updateSelected(studentId);
        }

        //选成功后返回true即可
        return true;
    }

    @Override
    public TeacherResult teacherLogin(String id, String passWord) {
        Teacher teacher = teacherMapper.teacherLogin(id,passWord);
        if(teacher != null){
            String uuid = UUID.randomUUID().toString();
            teacher.setPassword("");
            return  new TeacherResult(uuid,teacher);
        }

        return new TeacherResult();
    }
}
