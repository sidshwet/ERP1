package com.example.erp.adminWork;

public class studentModel {
    private String id;
    private String name;
    private String username;
    private String password;
    private String usn;
    private String semester;

    public studentModel(){

    }

    public studentModel(String id, String name, String username, String password, String usn, String semester) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.usn = usn;
        this.semester=semester;
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

    public String getUsn() {
        return usn;
    }

    public String getSemester() {
        return semester;
    }
}
