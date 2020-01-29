package com.example.erp.teacherWork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import java.util.List;

public class marksUpdate extends AppCompatActivity {

   // private EditText Sem,Sub,Internal;
    private Spinner Sem,Sub,Internal;
    DatabaseReference databaseReference;
    ValueEventListener listener;
    List<String> list2 = new ArrayList<String>();

    ArrayAdapter<String> dataAdapter2;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_update);
        /*Sem=(EditText)findViewById(R.id.etSem);
        Sub=(EditText)findViewById(R.id.etSub);
        Internal=(EditText)findViewById(R.id.etInternal);*/
        tv=(TextView)findViewById(R.id.textView6);
        Sem=(Spinner)findViewById(R.id.spinnerSem);
        Sub=(Spinner)findViewById(R.id.spinner2Sub);
        Internal=(Spinner)findViewById(R.id.spinner3Internal);


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


        databaseReference= FirebaseDatabase.getInstance().getReference("Subject");

        dataAdapter2 = new ArrayAdapter<String>(marksUpdate.this, android.R.layout.simple_spinner_item, list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sub.setAdapter(dataAdapter2);
        retrieveData();

        List<String> list3 = new ArrayList<String>();
        list3.add("1st Internal");
        list3.add("2nd Internal");
        list3.add("3rd Internal");
        list3.add("Event 1");
        list3.add("Event 2");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Internal.setAdapter(dataAdapter3);


    }

    public void work(View v){
       /* sem=Sem.getText().toString().trim();
        sub=Sub.getText().toString().trim();
        internal=Internal.getText().toString().trim();*/
        String sem=String.valueOf(Sem.getSelectedItem());
        String sub=String.valueOf(Sub.getSelectedItem());
        String internal=String.valueOf(Internal.getSelectedItem());
        Intent intent=new Intent(marksUpdate.this, marksData.class);
        intent.putExtra("SEMESTER",sem);
        intent.putExtra("INTERNAL",internal);
        intent.putExtra("SUBJECT",sub);
        startActivity(intent);
        //startActivity(new Intent(marksUpdate.this, marksData.class));
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

                }

                dataAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
