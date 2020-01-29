package com.example.erp.studentWork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.erp.R;
import com.example.erp.adminWork.studentModel;
import com.example.erp.teacherWork.attendanceModel;
import com.example.erp.teacherWork.marksData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class attendancePage2 extends AppCompatActivity {

    private TextView tv1,tv2;
    ArrayList<String> arr1 = new ArrayList<String>();
    DatabaseReference databaseReference;
    private ListView lv;
    String student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_page2);
       /* tv1=(TextView)findViewById(R.id.textView8);
        tv2=(TextView)findViewById(R.id.textView9);*/

        Intent intent=getIntent();
         student= intent.getStringExtra("Name");
        String sub= intent.getStringExtra("SUB");
       /* tv1.setText(student);
        tv2.setText(sub);*/
        lv=(ListView)findViewById(R.id.Activitie);

       databaseReference= FirebaseDatabase.getInstance().getReference("Attendance/"+sub+"/");

       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                   for (DataSnapshot snapshot : postSnapshot.getChildren()){
                       //getting artist
                       attendanceModel stud = snapshot.getValue(attendanceModel.class);
                   //adding artist to the list;

                   String name = stud.getName();
                   if (name.equals(student)) {
                       String date = stud.getDate();
                       arr1.add(date);
                   }
               }

               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

        ArrayAdapter arrayAdapter = new ArrayAdapter(attendancePage2.this,android.R.layout.simple_list_item_1,arr1);
        lv.setAdapter(arrayAdapter);

    }
}
