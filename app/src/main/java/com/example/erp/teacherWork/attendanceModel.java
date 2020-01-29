package com.example.erp.teacherWork;

public class attendanceModel {

    public String date;
    public String studentId;
    public String name;
    public String subjectId;

    public  attendanceModel(){

    }

    public attendanceModel(String date, String studentId, String name, String subjectId) {
        this.date = date;
        this.studentId = studentId;
        this.name = name;
        this.subjectId = subjectId;
    }

    public String getDate() {
        return date;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getSubjectId() {
        return subjectId;
    }
}
