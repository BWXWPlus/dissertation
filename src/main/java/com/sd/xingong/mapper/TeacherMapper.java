package com.sd.xingong.mapper;

import com.sd.xingong.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
    List<Teacher> getTeachers(int startIndex, int pageSize);
}
