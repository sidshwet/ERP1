package com.example.erp.adminWork;

public class teacherModel {
    public String id;
    public String name;
    public String username;
    public String password;
    public String email;


    public teacherModel(){

    }

    public teacherModel(String id, String name, String username, String password, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}



