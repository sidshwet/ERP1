package com.example.erp.adminWork;


import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.example.erp.R;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class addStudent extends AppCompatActivity {
    private EditText Name,UserName,Usn,Password,Semester;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Name=(EditText)findViewById((R.id.etSubName));
        UserName=(EditText)findViewById(R.id.etSubTeacher);
        Usn=(EditText)findViewById(R.id.etSemester);
        Password=(EditText)findViewById(R.id.etPassword);
        Semester=(EditText)findViewById(R.id.etSem);
        databaseReference= FirebaseDatabase.getInstance().getReference("Student");
    }

    public void work(View v){
        String name=Name.getText().toString().trim();
        String username=UserName.getText().toString().trim();
        String usn=Usn.getText().toString().trim();
        String password=Password.getText().toString().trim();
        String semester=Semester.getText().toString().trim();
        if(name.isEmpty()||username.isEmpty()||usn.isEmpty()||password.isEmpty()||semester.isEmpty()){
            Toast.makeText(this, "Fill data correctly", Toast.LENGTH_LONG).show();
        }
        else{
            String id = databaseReference.push().getKey();

            studentModel addObj= new studentModel(id,name,username,password,usn,semester);

            databaseReference.child(id).setValue(addObj);

            Toast.makeText(this, "Student Added", Toast.LENGTH_LONG).show();

            Name.setText("");
            UserName.setText("");
            Usn.setText("");
            Password.setText("");

        }
    }
}
