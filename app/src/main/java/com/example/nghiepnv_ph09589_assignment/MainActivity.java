package com.example.nghiepnv_ph09589_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Course(View view) {
        intent = new Intent(MainActivity.this, CourseActivity.class);
        startActivity(intent);
    }

    public void Maps(View view) {
        intent = new Intent(MainActivity.this, Maps_Activity.class);
        startActivity(intent);
    }

    public void News(View view) {
        intent = new Intent(MainActivity.this, NewsActivity.class);
        startActivity(intent);
    }

    public void Social(View view) {
        intent = new Intent(MainActivity.this, SocialActivity.class);
        startActivity(intent);
    }
}