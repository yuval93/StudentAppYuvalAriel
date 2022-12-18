package com.example.studentapp_yuvalariel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import model.Model;
import model.Student;

public class StudentEdit extends AppCompatActivity {

    TextView name;
    TextView id;
    TextView phone;
    TextView address;
    CheckBox cb;
    Button save;
    Button delete;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        final Activity activity = this;
        activity.setTitle("Edit students");

        Bundle pos = getIntent().getExtras();
        Student s = Model.instance.getAllStudents().get(pos.getInt("pos"));


        name = findViewById(R.id.edit_name_pt);
        name.setText(s.getName());
        name.setTextSize(24);

        id = findViewById(R.id.edit_id_pt);
        id.setText(s.getId());
        id.setTextSize(24);

        phone = findViewById(R.id.edit_phone_pt);
        phone.setText(s.getPhone());
        phone.setTextSize(24);

        address = findViewById(R.id.edit_address_pt);
        address.setText(s.getAddress());
        address.setTextSize(24);

        cb=findViewById(R.id.edit_cb);
        cb.setChecked(s.isFlag());

        save= findViewById(R.id.edit_save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save the changed data

                Student changedStudent = Model.instance.getAllStudents().get(pos.getInt("pos"));
                name = findViewById(R.id.edit_name_pt);
                changedStudent.setName(name.getText().toString());

                id = findViewById(R.id.edit_id_pt);
                changedStudent.setId(id.getText().toString());

                phone = findViewById(R.id.edit_phone_pt);
                changedStudent.setPhone(phone.getText().toString());

                address=findViewById(R.id.edit_address_pt);
                changedStudent.setAddress(address.getText().toString());

                cb = findViewById(R.id.edit_cb);
                changedStudent.setFlag(cb.isChecked());

                Intent intent = new Intent(StudentEdit.this,StudentListRvActivity.class);
                startActivity(intent);

            }
        });

        delete= findViewById(R.id.edit_delete_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete the certain student
                Student deleteStudent = Model.instance.getAllStudents().get(pos.getInt("pos"));
                Model.instance.getAllStudents().remove(deleteStudent);
                Intent intent = new Intent(StudentEdit.this,StudentListRvActivity.class);
                startActivity(intent);

            }
        });

        cancel= findViewById(R.id.edit_cancel_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StudentEdit.this,StudentListRvActivity.class);
                startActivity(intent);

            }
        });


    }


}
