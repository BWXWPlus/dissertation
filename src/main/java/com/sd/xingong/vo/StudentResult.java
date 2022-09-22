package com.sd.xingong.vo;

import com.sd.xingong.pojo.Student;

public class StudentResult {
    private String token;
    private Student student;

    public StudentResult() {
    }

    public StudentResult(String token, Student student) {
        this.token = token;
        this.student = student;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentResult{" +
                "token='" + token + '\'' +
                ", student=" + student +
                '}';
    }
}
