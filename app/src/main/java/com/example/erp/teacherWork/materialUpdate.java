package com.example.erp.teacherWork;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.erp.R;
import com.example.erp.adminWork.subModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class materialUpdate extends AppCompatActivity {

    private Spinner Sub,Sem;
    private EditText Topic;
    ValueEventListener listener;
    ArrayAdapter<String> dataAdapter2;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    DatabaseReference databaseReference2;
    List<String> list2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_update);

        Sub=(Spinner)findViewById(R.id.spinnerSub2);
        Sem=(Spinner)findViewById(R.id.spinner2Sem);
        Topic=(EditText)findViewById(R.id.materialName);

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

        dataAdapter2 = new ArrayAdapter<String>(materialUpdate.this, android.R.layout.simple_spinner_item, list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sub.setAdapter(dataAdapter2);
        retrieveData();

        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference2=FirebaseDatabase.getInstance().getReference("Materials");




    }

    public void retrieveData() {

        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    subModel sub = postSnapshot.getValue(subModel.class);
                    //adding artist to the list;
                    String abc = sub.getSubName();
                    list2.add(abc);
                }
                dataAdapter2.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void work(View v){

        selectPDFfile();
    }

    private void selectPDFfile() {

        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select PDF file"),1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData() != null){

            uploadPDFfile(data.getData());
        }
    }

    private void uploadPDFfile(Uri data) {

        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Uploading....");
        progressDialog.show();

        StorageReference reference= storageReference.child("Material/"+System.currentTimeMillis()+".pdf");

        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uri= taskSnapshot.getStorage().getDownloadUrl();
                while(!uri.isComplete());
                Uri url= uri.getResult();
                materialModel materials= new materialModel(String.valueOf(Sub.getSelectedItem()),String.valueOf(Sem.getSelectedItem()),
                        Topic.getText().toString(),url.toString());

                databaseReference2.child(String.valueOf(Sem.getSelectedItem())).child(String.valueOf(Sub.getSelectedItem()))
                        .child(Topic.getText().toString()).setValue(materials);
                Toast.makeText(materialUpdate.this, "File Uploaded", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                double progress=(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();

                progressDialog.setMessage("Uploaded: "+(int)progress+"%");

            }
        });
    }
}
