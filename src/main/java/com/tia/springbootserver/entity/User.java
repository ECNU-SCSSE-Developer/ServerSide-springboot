package com.tia.springbootserver.entity;

public class User {
    private String studentId;

    private String studentName;

    private Integer grade;

    private String major;

    private String specialty;

    private String contacts;

    private String openId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    @Override
    public String toString() {
        return "User{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", grade=" + grade +
                ", major='" + major + '\'' +
                ", specialty='" + specialty + '\'' +
                ", contacts='" + contacts + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}