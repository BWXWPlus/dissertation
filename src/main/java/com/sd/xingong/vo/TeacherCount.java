package com.sd.xingong.vo;

import com.sd.xingong.pojo.Teacher;

import java.util.List;

public class TeacherCount {
    private int count;
    private List<Teacher> teacher;

    public TeacherCount(int count, List<Teacher> teacher) {
        this.count = count;
        this.teacher = teacher;
    }

    public TeacherCount() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "TeacherCount{" +
                "count=" + count +
                ", teacher=" + teacher +
                '}';
    }
}
