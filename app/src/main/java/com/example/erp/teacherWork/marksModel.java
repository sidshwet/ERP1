package com.example.erp.teacherWork;

public class marksModel {
    private String studentName;
    private String marks;
    private String studentId;
    private String subId;

    public marksModel(){

    }

    public marksModel(String studentName, String marks, String studentId, String subId) {
        this.studentName = studentName;
        this.marks = marks;
        this.studentId = studentId;
        this.subId = subId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getMarks() {
        return marks;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubId() {
        return subId;
    }
}
