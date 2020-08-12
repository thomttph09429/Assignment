package com.example.thomttph09429;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.thomttph09429.R;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        setTitle("khóa học");
    }

    public void ListLop(View view) {
        Intent intent = new Intent(CourseActivity.this, ListLopActivity.class);
        startActivity(intent);
    }

    public void ListSinhVien(View view) {
        Intent intent = new Intent(CourseActivity.this, ListSinhVienActivity.class);
        startActivity(intent);
    }
}