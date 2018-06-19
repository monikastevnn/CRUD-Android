package com.example.asus.testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asus.testing.Models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDataSOurce {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public StudentDataSOurce(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    public boolean addStudent(Student student) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        //kirim data ke database
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", student.getFname());
        contentValues.put("lname", student.getLname());
        contentValues.put("phone", student.getPhone());
        contentValues.put("email", student.getEmail());
        contentValues.put("date", student.getDate());
        contentValues.put("gender", student.getGender());
        contentValues.put("grade", student.getGrade());
        contentValues.put("hobby", student.getHobby());
        contentValues.put("address", student.getAddress());
        contentValues.put("image", student.getImage());

        //execute insert data
        db.insert("student", null, contentValues);

        return true;
    }

    public boolean editStudent(Student student) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        //kirim data ke database
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", student.getFname());
        contentValues.put("lname", student.getLname());
        contentValues.put("phone", student.getPhone());
        contentValues.put("email", student.getEmail());
        contentValues.put("date", student.getDate());
        contentValues.put("gender", student.getGender());
        contentValues.put("grade", student.getGrade());
        contentValues.put("hobby", student.getHobby());
        contentValues.put("address", student.getAddress());
        contentValues.put("image", student.getImage());

        //execute insert data
        long id=database.update("student",contentValues,"id="+student.getId(),null);
        return true;
    }

    private Student fetchToPojo(Cursor cursor) {
        Student student = new Student();
        student.setId(cursor.getLong(0));
        student.setFname(cursor.getString(1));
        student.setLname(cursor.getString(2));
        student.setPhone(cursor.getString(3));
        student.setEmail(cursor.getString(4));
        student.setDate(cursor.getString(5));
        student.setGender(cursor.getString(6));
        student.setGrade(cursor.getString(7));
        student.setHobby(cursor.getString(8));
        student.setAddress(cursor.getString(9));
        student.setImage(cursor.getBlob(10));
        return student;

    }

    //ambil data student
    public List<Student> getAllStudent() {
        String query = "SELECT * FROM student";
        //ambil hasil query database ke cursor
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        List<Student> studentListPojo = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            Student student = fetchToPojo(cursor); //masuk ke pojo
            studentListPojo.add(student); //masuk ke array list
            cursor.moveToNext();
        }
        return studentListPojo;
    }

    public Student getStudent(long id) {
        String query = "SELECT * FROM student WHERE id=" + id;
        //ambil hasil query database ke cursor
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

            Student student = fetchToPojo(cursor); //masuk ke pojo
            cursor.close();

            return student;
    }

    public void deleteStudent(String student_id){
        long id=database.delete("student","id="+student_id,null);
    }



    public List<Student> searchStudent(String keyword) {
        String query = "SELECT * FROM student WHERE fname LIKE ? or lname LIKE ?";
        //ambil hasil query database ke cursor
        Cursor cursor = database.rawQuery(query, new String[]{"%"+keyword+"%","%"+keyword+"%"});
        cursor.moveToFirst();
        List<Student> studentListPojo = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            Student student = fetchToPojo(cursor); //masuk ke pojo
            studentListPojo.add(student); //masuk ke array list
            cursor.moveToNext();
        }
        return studentListPojo;
    }
}