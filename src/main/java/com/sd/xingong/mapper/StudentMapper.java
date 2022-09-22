package com.sd.xingong.mapper;

import com.sd.xingong.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> getStudents();
}
