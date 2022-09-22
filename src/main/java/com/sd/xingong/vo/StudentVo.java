package com.sd.xingong.vo;

public class StudentVo {
    private String password;
    private long studentId;

    public StudentVo() {
    }

    public StudentVo( String password, long studentId) {
        this.password = password;
        this.studentId = studentId;
    }




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                ", password='" + password + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
