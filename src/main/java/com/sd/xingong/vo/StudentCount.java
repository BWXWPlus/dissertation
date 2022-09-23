package com.sd.xingong.vo;

import com.sd.xingong.pojo.Student;

import java.util.List;

//学生总数统计
public class StudentCount {
    private Integer count; //用来统计学生的总数，方便分页
    private List<Student> student;

    public StudentCount() {
    }

    public StudentCount(Integer count, List<Student> student) {
        this.count = count;
        this.student = student;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentCount{" +
                "count=" + count +
                ", student=" + student +
                '}';
    }
}
