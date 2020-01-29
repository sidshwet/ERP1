package com.example.erp.adminWork;

public class subModel {
    public String id;
    public String subName;
    public String semester;
    public String teacher;
    public String teacherId;

    public subModel(){

    }

    public subModel(String id, String subName, String semester, String teacher, String teacherId) {
        this.id = id;
        this.subName = subName;
        this.semester = semester;
        this.teacher = teacher;
        this.teacherId = teacherId;
    }

    public String getId() {
        return id;
    }

    public String getSubName() {
        return subName;
    }

    public String getSemester() {
        return semester;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTeacherId() {
        return teacherId;
    }
}
