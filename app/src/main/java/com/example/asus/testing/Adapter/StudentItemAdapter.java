package com.example.asus.testing.Adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asus.testing.Models.Student;
import com.example.asus.testing.R;

import java.util.List;

public class StudentItemAdapter extends ArrayAdapter<Student>{

    private List<Student> studentList;
    private Context context;
    private LayoutInflater layoutInflater;

    public StudentItemAdapter(@NonNull Context context, List<Student>students) {
        super(context,R.layout.student_item,students);
        this.context=context;
        studentList=students;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Nullable
    @Override
    public Student getItem(int position) {
        return studentList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;

        if(view==null){
            view=layoutInflater.inflate(R.layout.student_item,null);
        }

        TextView fname=view.findViewById(R.id.tv_fname);
        TextView gender=view.findViewById(R.id.tv_gender);
        TextView grade=view.findViewById(R.id.tv_grade);
        TextView phone=view.findViewById(R.id.tv_phone);
        TextView email = view.findViewById(R.id.tv_email);
        TextView date=view.findViewById(R.id.tv_date);
        Student student=getItem(position);

        //kasih nilai ke layout student_item
        fname.setText(student.getFname());
        gender.setText(student.getGender());
        grade.setText((student.getGrade()));
        phone.setText(student.getPhone());
        email.setText(student.getEmail());
        date.setText(student.getDate());

        return view;
    }
}