package com.sd.xingong.mapper;

import com.sd.xingong.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> getStudents(int startIndex,int pageSize);

    Student studentLogin(String studentId, String password);

    List<Student> searchStudents(String name, String studentId, String title,int startIndex , int pageSize);

    Student getAStudent(String studentId);

    void updateMentorId(int teacherId,String studentId);

    int getStudentCount(String name, String studentId, String title);

    void updateSelected(String studentId);

    List<Student> getStudentsByTeacher(int teacherId, int isSelected);

    void uploadFiles(String studentId,String str, String type);

    int editStudentInformation(Student student);

    int editPassWord(String id, String newPassword);
}
