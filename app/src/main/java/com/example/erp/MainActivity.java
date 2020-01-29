package com.example.erp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView admin;
    private TextView student;
    private TextView teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin=(TextView)findViewById(R.id.admintv);
        student=(TextView)findViewById(R.id.studenttv);
        teacher=(TextView)findViewById(R.id.teachertv);

    }

    public void gotoAdmin(View v){
        startActivity(new Intent(MainActivity.this,AdminLogin.class));

    }
    public void gotoStudent(View v){
        startActivity(new Intent(MainActivity.this,StudentLogin.class));
    }
    public void gotoTeacher(View v){
        startActivity(new Intent(MainActivity.this,TeacherLogin.class));
    }
}
