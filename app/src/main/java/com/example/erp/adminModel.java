package com.example.erp;

public class adminModel {
    public String adminId;
    public String adminUserName;
    public String adminPassword;

    public adminModel(){

    }

    public adminModel(String adminId, String adminUserName, String adminPassword) {
        this.adminId = adminId;
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}
