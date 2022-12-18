package com.example.studentapp_yuvalariel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import model.Model;
import model.Student;

public class StudentDetails extends AppCompatActivity {

    TextView name;
    TextView id;
    TextView phone;
    TextView address;
    CheckBox cb;
    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details1);

        final Activity activity = this;
        activity.setTitle("Students details");

        Bundle pos = getIntent().getExtras();
        Student s = Model.instance.getAllStudents().get(pos.getInt("pos"));

        name = findViewById(R.id.details_name_show);
        name.setText(s.getName());
        name.setTextSize(24);

        id=findViewById(R.id.details_id_show);
        id.setText(s.getId());
        id.setTextSize(24);

        phone=findViewById(R.id.details_phone_show);
        phone.setText(s.getPhone());
        phone.setTextSize(24);

        address=findViewById(R.id.details_address_show);
        address.setText(s.getAddress());
        address.setTextSize(24);

        cb=findViewById(R.id.details_cb);
        cb.setChecked(s.isFlag());

        edit= findViewById(R.id.details_edit_btn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StudentDetails.this,StudentEdit.class);
                Bundle pos = getIntent().getExtras();
                intent.putExtra("pos",pos.getInt("pos"));
                startActivity(intent);
            }
        });




    }
}
