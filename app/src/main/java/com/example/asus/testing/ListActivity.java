package com.example.asus.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus.testing.Adapter.StudentItemAdapter;
import com.example.asus.testing.Models.Student;

import java.util.List;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    List<Student> studentList;
    StudentDataSOurce studentDataSOurce=null;
    Student globalStudent;
    StudentItemAdapter studentItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listView=findViewById(R.id.lv_student);
        SearchView searchView=findViewById(R.id.sv_student);

        //panggil getAllStudent buat ambil data student di list
        studentDataSOurce=new StudentDataSOurce(this);
        studentDataSOurce.open();
        studentList=studentDataSOurce.getAllStudent();


        //masukin adapter ke list view
        studentItemAdapter=new StudentItemAdapter(this, studentList);
        listView.setAdapter(studentItemAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        //masukin contect menu ke list view
        registerForContextMenu(listView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //empty data pojo
                studentList.clear();

                //call function search from StudentDataSource
                List<Student> studentListOnSearch=studentDataSOurce.searchStudent(s);
                for (int i=0;i<studentListOnSearch.size();i++){
                    //input pojo
                    Student student=studentListOnSearch.get(i);
                    //update data list on studentList
                    studentList.add(student);
                }
                //studentDataSOurce.close();

                //refresh data student list
                studentItemAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId=item.getItemId();
        if (menuId==R.id.add_student){
            startActivity(new Intent(this,FormActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //get id data student
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //send data student to POJO
        Student student=studentList.get(i);
        Intent intent=new Intent(this,DetailActivity.class);
        intent.putExtra("id",student.getId());
        startActivity(intent);
        //startActivity(new Intent(this,DetailActivity.class));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int menuId=item.getItemId();
        if (menuId==R.id.edit_student){
            Intent intent=new Intent(this,FormActivity.class);
            intent.putExtra("id",globalStudent.getId());
            startActivity(intent);
        }else if (menuId==R.id.delete_student){
            studentDataSOurce.deleteStudent(String.valueOf(globalStudent.getId()));
            studentList.clear();
            List<Student> studentListOnDelete=studentDataSOurce.getAllStudent();

            for (int i=0;i<studentListOnDelete.size();i++){
                //input pojo
                Student student=studentListOnDelete.get(i);
                //update data list on studentList
                studentList.add(student);
            }
            studentItemAdapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
        globalStudent=studentList.get(i);
        openContextMenu(adapterView);
        return true;
    }
}
