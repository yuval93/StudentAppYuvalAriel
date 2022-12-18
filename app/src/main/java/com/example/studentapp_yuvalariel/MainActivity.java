package com.example.studentapp_yuvalariel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import model.Model;
import model.Student;

public class MainActivity extends AppCompatActivity {

    EditText nameEt;
    EditText idEt;
    EditText phoneEt;
    EditText addressEt;
    CheckBox cb;
    Button saveBtn;
    Button cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Activity activity = this;
        activity.setTitle("New Student");

        nameEt = findViewById(R.id.main_name_et);
        idEt = findViewById(R.id.main_id_et);
        phoneEt = findViewById(R.id.main_phone_et);
        addressEt = findViewById(R.id.main_address_et);
        cb = findViewById(R.id.main_cb);
        saveBtn=findViewById(R.id.main_save_btn);
        cancelBtn=findViewById(R.id.main_cancel_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });


    }

    private void save()
    {
        String name = nameEt.getText().toString();
        String id = idEt.getText().toString();
        String phone = phoneEt.getText().toString();
        String address = addressEt.getText().toString();
        boolean isChecked = cb.isChecked();
        Student stud = new Student(name,id,phone,address,isChecked);

        Model.instance.addStudent(stud);


        Intent intent = new Intent(this,StudentListRvActivity.class);
        startActivity(intent);

    }

    private void cancel()
    {
        //go back to list view
        Intent intent = new Intent(this, StudentListRvActivity.class);
        startActivity(intent);

    }
}