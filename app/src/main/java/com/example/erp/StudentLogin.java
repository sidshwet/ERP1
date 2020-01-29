package com.example.erp;



        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.erp.adminWork.adminPage;
        import com.example.erp.adminWork.studentModel;
import com.example.erp.adminWork.teacherModel;
import com.example.erp.studentWork.studentPage;
import com.example.erp.teacherWork.teacherPage;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;


public class StudentLogin extends AppCompatActivity {
    String userName,password;
    private EditText Name;
    private EditText Password;
    DatabaseReference databaseReference;
    private TextView sample;

    private Button Login;

    private ProgressDialog progressDialog;

    public void work(View v) {
        validate(Name.getText().toString(), Password.getText().toString());
    }


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        getSupportActionBar().setTitle("Login");
        Name=(EditText)findViewById(R.id.etSubTeacher);
        Password=(EditText)findViewById((R.id.etPassword));

        Login=(Button)findViewById(R.id.btLogin);
        progressDialog = new ProgressDialog(this);
        userName=Name.getText().toString();
        password=Password.getText().toString();


        databaseReference= FirebaseDatabase.getInstance().getReference("Student");
        /*Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });*/



    }

    private void validate(final String userName, final String userPassword) {

        if (userName.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
           /* progressDialog.setMessage("Please Wait");
            progressDialog.show();*/

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        //getting artist
                        studentModel stu = postSnapshot.getValue(studentModel.class);
                        //adding artist to the list;
                        String username = stu.getUsername();
                        String name= stu.getName();
                        String password1 = stu.getPassword();
                        if(userName.equals(username)&& userPassword.equals((password1))){
                            Intent intent=new Intent(StudentLogin.this, studentPage.class);
                            intent.putExtra("Name",name);
                            startActivity(intent);
                        }


                    }

                    progressDialog.dismiss();
                    /*if(userName.equals(username)&&userPassword.equals(userpass)){
                        progressDialog.dismiss();
                        startActivity(new Intent(AdminLogin.this, adminPage.class));
                    }*/


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }
    }

}


