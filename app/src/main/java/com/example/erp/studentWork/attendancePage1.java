package com.example.erp.studentWork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.erp.R;
import com.example.erp.adminWork.subModel;
import com.example.erp.teacherWork.AttendanceActivity;
import com.example.erp.teacherWork.selectSem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class attendancePage1 extends AppCompatActivity {

    private Spinner Sub;
    DatabaseReference databaseReference;
    ValueEventListener listener;
    ArrayAdapter<String> dataAdapter2;
    List<String> list2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_page1);
        Sub=(Spinner)findViewById(R.id.spinnerSub2);

        databaseReference= FirebaseDatabase.getInstance().getReference("Subject");

        dataAdapter2 = new ArrayAdapter<String>(attendancePage1.this, android.R.layout.simple_spinner_item, list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sub.setAdapter(dataAdapter2);
        retrieveData();
    }

    public void retrieveData(){

        listener= databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    subModel sub = postSnapshot.getValue(subModel.class);
                    //adding artist to the list;
                    String abc=sub.getSubName();
                    list2.add(abc);

                    /*String sem1 = student.getSemester();
                    if(sem1.equals(sem)){
                        String name = student.getName();
                        arr1.add(name);
                    }*/


                }

                dataAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void work1(View v){

        String student;
        Intent intent2 = getIntent();
        student= intent2.getStringExtra("Name1");
        String sub=String.valueOf(Sub.getSelectedItem());
        //Intent intent = new Intent(selectSem.this,AttendanceActivity.class);
        Intent intent = new Intent(attendancePage1.this, attendancePage2.class);
        intent.putExtra("Name",student);
        intent.putExtra("SUB",sub);
        startActivity(intent);

    }
}
