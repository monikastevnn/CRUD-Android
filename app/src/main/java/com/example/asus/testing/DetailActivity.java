package com.example.asus.testing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.testing.Models.Student;

public class DetailActivity extends AppCompatActivity {

    TextView tv_name,tv_phone;
    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_name=findViewById(R.id.tv_name);
        tv_phone=findViewById(R.id.tv_phone);
        iv_image=findViewById(R.id.iv_image);

        long id=getIntent().getLongExtra("id",0);

        StudentDataSOurce studentDataSOurce=new StudentDataSOurce(this);
        studentDataSOurce.open();
        Student student=studentDataSOurce.getStudent(id);
        studentDataSOurce.close();

        tv_name.setText(student.getFname()+" "+student.getLname());
        tv_phone.setText(student.getPhone());

        //get image
        byte[] image=student.getImage();

       Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
       iv_image.setImageBitmap(bitmap);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int menuId = item.getItemId();
        if (menuId == android.R.id.home) {
            finish(); //biar kalo udah selesai isi, langsung balik ke list
        }
        return super.onOptionsItemSelected(item);
    }
}
