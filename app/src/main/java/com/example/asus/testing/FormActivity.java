package com.example.asus.testing;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asus.testing.Models.Student;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity {

     EditText et_name1, et_name2, et_phone, et_address, etEmail, date;
     RadioGroup rg_gender;
     Spinner spGrade;
     CheckBox drawing,writing;
     DatePickerDialog datePickerDialog;
     long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityform);

        id=getIntent().getLongExtra("id",0);



        //call id from xml file
        et_name1=findViewById(R.id.et_name1);
        et_name2=findViewById(R.id.et_name2);
        et_phone=findViewById(R.id.et_phone);
        etEmail=findViewById(R.id.et_email);
        date=findViewById(R.id.date);
        rg_gender=findViewById(R.id.rg_gender1);
        spGrade=findViewById(R.id.spGrade);
        drawing=findViewById(R.id.cb_1);
        writing=findViewById(R.id.cb_2);
        et_address=findViewById(R.id.et_address);
        Button btnSave=findViewById(R.id.btn_2);

        if (id>0){
            StudentDataSOurce studentDataSOurce=new StudentDataSOurce(this);
            studentDataSOurce.open();
            Student student=studentDataSOurce.getStudent(id);
            studentDataSOurce.close();
            et_name1.setText(student.getFname());
            et_name2.setText(student.getLname());
        }

        date.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(FormActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
                    }
                });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void submit() {
        String fname = et_name1.getText().toString();
        String lname = et_name2.getText().toString();
        String phone = et_phone.getText().toString();
        String datevalue = date.getText().toString();
        String email = etEmail.getText().toString();
        int checkId = rg_gender.getCheckedRadioButtonId();
        String gender = "";
        String grade = spGrade.getSelectedItem().toString();
        String hobby = "";
        String address = et_address.getText().toString();

        boolean statusCheck = false;

        //cek isi form
        if (phone.equals("")) {
            et_phone.setError("Input Phone Number");
        } else {
            statusCheck = true;
        }

        if (email.equals("")) {
            etEmail.setError("Invalid email");
        } else {
            statusCheck = true;
        }

        //check id radio button
        if (checkId == R.id.rb_male) {
            gender = "Male";
        } else if (checkId == R.id.rb_female) {
            gender = "Female";
        }

        if (drawing.isChecked() && writing.isChecked()) {
            hobby = "Drawing and Writing";
        } else if (drawing.isChecked()) {
            hobby = "Drawing";
        } else if (writing.isChecked()) {
            hobby = "Writing";
        } else {
            statusCheck = true;
        }

        String etEmail = "try@email.com";
        String splitEmail[] = email.split("@");

        if (splitEmail.length > 1) {
            //valid
        }else {
            //invalid
        }

        //masukkin POJO
        if (statusCheck == true) {
            Student student = new Student();
            student.setFname(fname);
            student.setLname(lname);
            student.setPhone(phone);
            student.setDate(datevalue);
            student.setEmail(email);
            student.setGender(gender);
            student.setGrade(grade);
            student.setHobby(hobby);
            student.setAddress(address);

            //insert image
            try {
                File folder= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"/qqq.jpg");
                //FileInputStream fileInputStream = new FileInputStream("/storage/emulated/legacy/Pictures/Screenshots/scc.png");
                FileInputStream fileInputStream = new FileInputStream(folder);
                byte[] image=new byte[fileInputStream.available()];
                fileInputStream.read(image);
                student.setImage(image);
                fileInputStream.close();
                Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_SHORT).show();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }



            //call addStudent function from StudentDataSOurce.java
            StudentDataSOurce studentDataSOurce = new StudentDataSOurce(this);
            studentDataSOurce.open();

            if (id>0){
                student.setId(id);
                studentDataSOurce.editStudent(student);
            }else{
                studentDataSOurce.addStudent(student);
            }
            studentDataSOurce.close();
        }

        System.out.println("Name" + fname + lname);
        System.out.println("Phone" + phone);
        //Toast.makeText(getApplicationContext(),"Hello "+fname+lname+"\n"+"Phone: "+phone+
        //"\n"+"Gender: "+gender+"\n"+"Grade: "+grade+"\n"+"Hobby: "+hobby, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
                int menuId = item.getItemId();
                if (menuId == R.id.menu_submit) {
                    submit();
                    finish(); //biar kalo udah selesai isi, langsung balik ke list
                }
                return super.onOptionsItemSelected(item);
            }
    }

