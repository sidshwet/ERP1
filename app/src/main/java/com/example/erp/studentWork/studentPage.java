package com.example.erp.studentWork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erp.R;
import com.example.erp.StudentLogin;

public class studentPage extends AppCompatActivity {

    private TextView attendance,marks,materials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);

        attendance=(TextView)findViewById(R.id.tvattend);
        marks=(TextView)findViewById(R.id.tvmarks);
        materials=(TextView)findViewById(R.id.tvmaterials);

    }

    public void work1(View v){

        String student;
        Intent intent = getIntent();
        student= intent.getStringExtra("Name");
        Intent intent2=new Intent(studentPage.this, attendancePage1.class);
        intent2.putExtra("Name1",student);
        startActivity(intent2);
    }
}
