package com.sd.xingong.vo;

import com.sd.xingong.pojo.Teacher;

public class TeacherResult {
    private String token;
    private Teacher teacher;

    public TeacherResult() {
    }

    public TeacherResult(String token, Teacher teacher) {
        this.token = token;
        this.teacher = teacher;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "TeacherVo{" +
                "token='" + token + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
