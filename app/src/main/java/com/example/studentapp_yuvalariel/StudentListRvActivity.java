package com.example.studentapp_yuvalariel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import model.Model;
import model.Student;

public class StudentListRvActivity extends AppCompatActivity {

    List<Student> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_rv);

        final Activity activity = this;
        activity.setTitle("Students list");

        data = Model.instance.getAllStudents();

        RecyclerView list = findViewById(R.id.studentlist_rv);
        list.setHasFixedSize(true);

        list.setLayoutManager((new LinearLayoutManager(this)));

        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        adapter.setonItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(StudentListRvActivity.this,StudentDetails.class);
                intent.putExtra("pos",position);

                startActivity(intent);
                Log.d("TAG","row was clicked"+ position);
            }
        });

        Button addStudent = findViewById(R.id.studentlist_rv_addbtn);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentListRvActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameTv;
        TextView idTv;
        CheckBox cb;
        public MyViewHolder(@NonNull View itemView, onItemClickListener listener) {
            super(itemView);
            nameTv= itemView.findViewById(R.id.listrow_name_tv);
            idTv= itemView.findViewById(R.id.listrow_id_tv);
            cb= itemView.findViewById(R.id.listrow_cb);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.d("TAG","row was clicked"+ position);
                    listener.onItemClick(position);
                }
            });

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = getAdapterPosition();
                    Log.d("TAG","cb clicked!!!!");
                    Student s= Model.instance.getAllStudents().get(position);
                    if(cb.isChecked()==true)
                        s.setFlag(true);
                    else
                        s.setFlag(false);
                }
            });
        }
    }

    interface onItemClickListener{
        void onItemClick(int position);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        onItemClickListener listener;
        public void setonItemClickListener(onItemClickListener listener)
        {
            this.listener=listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.students_list_row,parent,false);
            MyViewHolder holder = new MyViewHolder(view,listener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            Student student = data.get(position);
            holder.nameTv.setText(student.getName());
            holder.idTv.setText(student.getId());
            holder.cb.setChecked(student.isFlag());

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
