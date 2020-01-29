package com.example.erp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addAdmin extends AppCompatActivity {

    private EditText name,UserName,password;
    // private  EditText UserName;
    // private EditText password;
    private Button btn;
    String username,id;
    String password1;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);


        UserName=(EditText)findViewById(R.id.adminUserName);
        password=(EditText)findViewById(R.id.adminPassword);
        btn=(Button)findViewById(R.id.button);
        databaseReference= FirebaseDatabase.getInstance().getReference("admin");

    }
    public void work(View v){
        addAdmin();
    }
    public void addAdmin() {
        //getting the values to save

        username =UserName.getText().toString().trim();
        password1 =password.getText().toString().trim();

        //checking if the value is provided
        if (!username.isEmpty()&&!password1.isEmpty()) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            id = databaseReference.push().getKey();

            //creating an Artist Object
            adminModel adminM = new adminModel(id, username, password1);

            //Saving the Artist
            databaseReference.child(id).setValue(adminM);

            //setting edittext to blank again


            //displaying a success toast
            Toast.makeText(this, "Admin added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
