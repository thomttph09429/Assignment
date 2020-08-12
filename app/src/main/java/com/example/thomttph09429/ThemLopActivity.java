package com.example.thomttph09429;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thomttph09429.DAO.LopDAO;
import com.example.thomttph09429.Model.Lop;
import com.example.thomttph09429.R;

public class ThemLopActivity extends AppCompatActivity {
    EditText MaLop, TenLop;
    LopDAO lopDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);
        setTitle("Thêm lớp");
        MaLop = findViewById(R.id.MaLop);
        TenLop = findViewById(R.id.TenLop);
    }

    public void Huy(View view) {
        MaLop.setText("");
        TenLop.setText("");
    }

    public void Luu(View view) {
        lopDAO = new LopDAO(this);
        Lop lop = new Lop();
        lop.setMaLop(MaLop.getText().toString());
        lop.setTenLop(TenLop.getText().toString());
        if (lopDAO.insertLop(lop)<0){
            Toast.makeText(ThemLopActivity.this,"Thêm thất bại",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(ThemLopActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();
        }
    }
}