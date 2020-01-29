package com.example.erp.teacherWork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.erp.R;

import org.w3c.dom.Text;

public class sample extends AppCompatActivity {

    private TextView one,two,three;
    String sem,sub,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        one=(TextView)findViewById(R.id.textView);
        two=(TextView)findViewById(R.id.textView2);
        three=(TextView)findViewById(R.id.textView3);
        Intent intent = getIntent();
        sem= intent.getStringExtra("EXTRA_MESSAGE");
        date=intent.getStringExtra("DATE");
        sub=intent.getStringExtra("SUB");
        one.setText(sub);
        two.setText(sem);
        three.setText(date);
    }
}
