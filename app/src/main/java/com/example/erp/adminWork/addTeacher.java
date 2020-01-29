package com.example.erp.adminWork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.erp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addTeacher extends AppCompatActivity {
    private EditText Name,UserName,Email,Password;
    private Button btn;
    DatabaseReference databaseReference;
    String name,username,id,password,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        Name=(EditText)findViewById((R.id.etSubName));
        UserName=(EditText)findViewById(R.id.etSubTeacher);
        Email=(EditText)findViewById(R.id.etSemester);
        Password=(EditText)findViewById(R.id.etPassword);
        btn=(Button)findViewById(R.id.button2);
        databaseReference= FirebaseDatabase.getInstance().getReference("Teacher");
    }

    public void work(View v){
        name=Name.getText().toString().trim();
        username=UserName.getText().toString().trim();
        email=Email.getText().toString().trim();
         password=Password.getText().toString().trim();
        if(name.isEmpty()||username.isEmpty()||email.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "Fill data correctly", Toast.LENGTH_LONG).show();
        }
        else{

            id = databaseReference.push().getKey();

            teacherModel addObj1= new teacherModel(id,name,username,password,email);

            databaseReference.child(id).setValue(addObj1);

            Toast.makeText(this, "Teacher Added", Toast.LENGTH_LONG).show();

            Name.setText("");
            UserName.setText("");
            Email.setText("");
            Password.setText("");

        }
    }
}
