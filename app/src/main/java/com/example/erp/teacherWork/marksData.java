package com.example.erp.teacherWork;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.erp.AdminLogin;
        import com.example.erp.R;
        import com.example.erp.adminModel;
        import com.example.erp.adminWork.adminPage;
        import com.example.erp.adminWork.studentModel;
        import com.example.erp.adminWork.subModel;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.Arrays;

public class marksData extends AppCompatActivity {


    private String sem,sub,result,internal;
    DatabaseReference databaseReference,databaseReference2,databaseReference3,databaseReference4;
    private ListView lv;
    final Context context = this;
    //String[] str;
    ArrayList<String> arr1 = new ArrayList<String>();
    ArrayList<String>present = new ArrayList<String>();
    ArrayList<String>stuID = new ArrayList<String>();
    String subId;
    //int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_data);





        Intent intent = getIntent();
        sem= intent.getStringExtra("SEMESTER");
        internal=intent.getStringExtra("INTERNAL");
        sub=intent.getStringExtra("SUBJECT");

        databaseReference= FirebaseDatabase.getInstance().getReference("Student");
        databaseReference2=FirebaseDatabase.getInstance().getReference("Subject");
        databaseReference3=FirebaseDatabase.getInstance().getReference("Attendance");
        databaseReference4=FirebaseDatabase.getInstance().getReference("Marks");
        lv=(ListView)findViewById(R.id.Activities1);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    studentModel student = postSnapshot.getValue(studentModel.class);
                    //adding artist to the list;

                    String sem1 = student.getSemester();
                    if(sem1.equals(sem)){
                        String name = student.getName();
                        arr1.add(name);
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
        ArrayAdapter arrayAdapter = new ArrayAdapter(marksData.this,android.R.layout.simple_list_item_1,arr1);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);
                final TextView msg=(TextView)promptsView.findViewById(R.id.textView1);
                msg.setText("Enter  "+arr1.get(position)+" Marks");

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        databaseReference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                                    //getting artist
                                                    studentModel student = postSnapshot.getValue(studentModel.class);
                                                    //adding artist to the list;


                                                    String sem1 = student.getSemester();
                                                    if(sem1.equals(sem)){
                                                        String name = student.getName();
                                                        if(name.equals(arr1.get(position))){
                                                            String id= student.getId();
                                                            result=userInput.getText().toString().trim();
                                                            marksModel marks= new marksModel(arr1.get(position),result,id,subId);
                                                            databaseReference4.child(sub).child(internal).child(id).setValue(marks);
                                                        }
                                                    }

                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });



                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


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
        startActivity(new Intent(marksData.this, teacherPage.class));

    }
}
