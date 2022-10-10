package com.sd.xingong.vo;

/**
 * 没用到
 */
public class FileNameVo {
    private int grade;
    private String specialities;
    private int stuClass;


    public FileNameVo() {
    }

    public FileNameVo(int grade, String specialities, int stuClass) {
        this.grade = grade;
        this.specialities = specialities;
        this.stuClass = stuClass;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    public int getStuClass() {
        return stuClass;
    }

    public void setStuClass(int stuClass) {
        this.stuClass = stuClass;
    }

    @Override
    public String toString() {
        return "FileNameVo{" +
                "grade=" + grade +
                ", specialities='" + specialities + '\'' +
                ", stuClass=" + stuClass +
                '}';
    }
}
