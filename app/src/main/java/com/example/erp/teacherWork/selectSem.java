package com.example.erp.teacherWork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.erp.R;
import com.example.erp.adminWork.subModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class selectSem extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    
    private Spinner Sem,Sub;
    DatabaseReference databaseReference;
    String date;
    private TextView tv;
    ValueEventListener listener;
    ArrayAdapter<String> dataAdapter2;
    List<String> list2 = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sem);
        Sem=(Spinner)findViewById(R.id.spinner2Sem);
        Sub=(Spinner)findViewById(R.id.spinnerSub2);
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sem.setAdapter(dataAdapter);
          //adding artist to the list;

        databaseReference= FirebaseDatabase.getInstance().getReference("Subject");

        dataAdapter2 = new ArrayAdapter<String>(selectSem.this, android.R.layout.simple_spinner_item, list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sub.setAdapter(dataAdapter2);
        retrieveData();




       /* databaseReference= FirebaseDatabase.getInstance().getReference("Student");*/

        tv=(TextView)findViewById(R.id.textView4);
    }

   public void showDatePickerDialog(View v){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = "" + month + "-" + dayOfMonth + "-" + year;

    }
    public void work(View v){
        String sem=String.valueOf(Sem.getSelectedItem());
        String sub=String.valueOf(Sub.getSelectedItem());
        //Intent intent = new Intent(selectSem.this,AttendanceActivity.class);
        Intent intent = new Intent(selectSem.this,AttendanceActivity.class);
        intent.putExtra("EXTRA_MESSAGE",sem);
        intent.putExtra("DATE",date);
        intent.putExtra("SUB",sub);
        startActivity(intent);
        //startActivity(new Intent(selectSem.this, AttendanceActivity.class));



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



}
