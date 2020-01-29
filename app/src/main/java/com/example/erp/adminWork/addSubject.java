package com.example.erp.adminWork;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

import com.example.erp.AdminLogin;
import com.example.erp.R;
import com.example.erp.adminModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addSubject extends AppCompatActivity {
    private EditText SubName,SubTeacher,Semester
            ,Password;
    DatabaseReference databaseReference,databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        SubName=(EditText)findViewById((R.id.etSubName));
        SubTeacher=(EditText)findViewById(R.id.etSubTeacher);
        Semester=(EditText)findViewById(R.id.etSemester);
        databaseReference= FirebaseDatabase.getInstance().getReference("Subject");
        databaseReference2=FirebaseDatabase.getInstance().getReference("Teacher");
    }

    public void work(View v){
        final String subname=SubName.getText().toString().trim();
        final String teacher=SubTeacher.getText().toString().trim();
        final String semester=Semester.getText().toString().trim();

        if(subname.isEmpty()||teacher.isEmpty()||semester.isEmpty()){
            Toast.makeText(this, "Fill data correctly", Toast.LENGTH_LONG).show();
        }
        else{

            databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String id = databaseReference.push().getKey();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        //getting artist
                        teacherModel sir = postSnapshot.getValue(teacherModel.class);
                        //adding artist to the list;
                        String idTeacher = sir.getId();
                        String nameTeacher= sir.getName();
                        if(nameTeacher.equals(teacher)){
                            subModel sub= new subModel(id,subname,semester,teacher,idTeacher);
                            databaseReference.child(id).setValue(sub);

                        }


                    }
                    Toast.makeText(addSubject.this, "Student Added", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            SubName.setText("");
            SubTeacher.setText("");
            Semester.setText("");


        }
    }
}
