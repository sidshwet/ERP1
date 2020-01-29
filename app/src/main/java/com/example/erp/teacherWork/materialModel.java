package com.example.erp.teacherWork;

public class materialModel {

    public String subject;
    public String semester;
    public String topic;
    public String url;

    public materialModel(){

    }

    public materialModel(String subject, String semester, String topic, String url) {
        this.subject = subject;
        this.semester = semester;
        this.topic = topic;
        this.url = url;
    }

    public String getSubject() {
        return subject;
    }

    public String getSemester() {
        return semester;
    }

    public String getTopic() {
        return topic;
    }

    public String getUrl() {
        return url;
    }
}
