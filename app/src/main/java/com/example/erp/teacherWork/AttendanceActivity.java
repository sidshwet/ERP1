package com.example.erp.teacherWork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.erp.R;
import com.example.erp.adminWork.studentModel;
import com.example.erp.adminWork.subModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {

    String sem,date,sub;
    DatabaseReference databaseReference,databaseReference2,databaseReference3;
    ListView lv;
    //String[] str;
    ArrayList<String> arr = new ArrayList<String>();
    ArrayList<String>present = new ArrayList<String>();
    ArrayList<String>stuID = new ArrayList<String>();
    String subId;
    //int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        Intent intent = getIntent();
        String sem1= intent.getStringExtra("EXTRA_MESSAGE");
        String date1=intent.getStringExtra("DATE");
        String sub1=intent.getStringExtra("SUB");

        /*Bundle mBundle=getIntent().getExtras();
        assert mBundle != null;
        String sem1=mBundle.getString("EXTRA_MESSAGE");
        String date1=mBundle.getString("DATE");
        String sub1=mBundle.getString("SUB");*/


        databaseReference= FirebaseDatabase.getInstance().getReference("Student");
        databaseReference2=FirebaseDatabase.getInstance().getReference("Subject");
        databaseReference3=FirebaseDatabase.getInstance().getReference("Attendance");
        lv=(ListView)findViewById(R.id.Activities);
        sem=sem1;
        date=date1;
        sub=sub1;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    studentModel stu = postSnapshot.getValue(studentModel.class);
                    //adding artist to the list;


                    String sem2 = stu.getSemester();
                    if(sem2.equals(sem)){
                        String name = stu.getName();
                        arr.add(name);
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //subject id fetching

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    subModel subject = postSnapshot.getValue(subModel.class);
                    //adding artist to the list;

                    assert subject != null;
                    String subName=subject.getSubName();

                    if(sub.equals(subName)){
                        subId = subject.getId();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AttendanceActivity.this,android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(arrayAdapter);

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                present.add(arr.get(position));
                arr.add(position,arr.get(position) +"  Present");
                arr.remove(position+1);


                Toast.makeText(AttendanceActivity.this, "Present" + arr.get(position) , Toast.LENGTH_LONG).show();

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AttendanceActivity.this,android.R.layout.simple_list_item_1,arr);
                lv.setAdapter(arrayAdapter);

                lv.setSelection(position);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            //getting artist
                            studentModel student = postSnapshot.getValue(studentModel.class);
                            //adding artist to the list;


                            assert student != null;
                            String sem1;
                            sem1 = student.getSemester();
                            if(sem1.equals(sem)){
                                String name = student.getName();
                                if(present.contains(name)){
                                    String id= student.getId();
                                    attendanceModel atd= new attendanceModel(date,id,name,subId);
                                    databaseReference3.child(sub).child(date).child(id).setValue(atd);
                                }
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
    public void work(View v){
       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    studentModel student = postSnapshot.getValue(studentModel.class);
                    //adding artist to the list;


                    String sem1 = student.getSemester();
                    if(sem1.equals(sem)){
                        String name = student.getName();
                        if(present.contains(name)){
                            String id= student.getId();
                            attendanceModel atd= new attendanceModel(date,id);
                            databaseReference3.child(sub).child(date).setValue(atd);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        startActivity(new Intent(AttendanceActivity.this, teacherPage.class));

    }
}
