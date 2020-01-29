package com.example.erp.teacherWork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erp.AdminLogin;
import com.example.erp.MainActivity;
import com.example.erp.R;

public class teacherPage extends AppCompatActivity {
    private TextView attendance,Marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_page);
        attendance=(TextView)findViewById(R.id.tvAttendance);
        Marks=(TextView)findViewById(R.id.tvMarks);
    }

    public void work(View v){

        startActivity(new Intent(teacherPage.this, selectSem.class));
    }

    public void marks(View v){

        startActivity(new Intent(teacherPage.this, marksUpdate.class));
    }

    public void materials(View v){

        startActivity(new Intent(teacherPage.this, materialUpdate.class));
    }
}
