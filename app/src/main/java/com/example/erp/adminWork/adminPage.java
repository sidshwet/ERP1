package com.example.erp.adminWork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erp.AdminLogin;
import com.example.erp.MainActivity;
import com.example.erp.R;
import com.example.erp.addAdmin;

public class adminPage extends AppCompatActivity {

    private TextView addTeacher,addStudent,addAdmin,addSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        addTeacher=(TextView)findViewById(R.id.tvAddTeacher);
        addStudent=(TextView)findViewById(R.id.tvAddStudent);
        addAdmin=(TextView)findViewById(R.id.tvAddAdmin);
        addSubject=(TextView)findViewById(R.id.tvAddSub);
    }

    public void addTeacher(View v){
        startActivity(new Intent(adminPage.this, addTeacher.class));
    }
    public void addStudent(View v){
        startActivity(new Intent(adminPage.this, addStudent.class));
    }
    public void addAdmin(View v){
        startActivity(new Intent(adminPage.this,addAdmin.class));
    }
    public void addSubject(View v){
        startActivity(new Intent(adminPage.this,addSubject.class));
    }
}
