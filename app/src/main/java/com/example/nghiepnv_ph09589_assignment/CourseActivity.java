package com.example.nghiepnv_ph09589_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nghiepnv_ph09589_assignment.R;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
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