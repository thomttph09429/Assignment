package com.example.nghiepnv_ph09589_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nghiepnv_ph09589_assignment.DAO.LopDAO;
import com.example.nghiepnv_ph09589_assignment.DAO.SinhVienDAO;
import com.example.nghiepnv_ph09589_assignment.Model.Lop;
import com.example.nghiepnv_ph09589_assignment.Model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienActivity extends AppCompatActivity {
    Spinner spMaLop;
    EditText edtTenSV, edtNgaySinh;
    LopDAO lopDAO;
    String maLop = "";
    List<Lop> lopList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien);
        setTitle("Thêm sinh viên");
        spMaLop = findViewById(R.id.spMaLop);
        edtTenSV = findViewById(R.id.edtTenSV);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        lopDAO = new LopDAO(this);
        lopList = lopDAO.getAllLop();
        ArrayAdapter<Lop> lopArrayAdapter = new ArrayAdapter<Lop>(this, android.R.layout.simple_spinner_item,lopList);
        lopArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaLop.setAdapter(lopArrayAdapter);
        spMaLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLop = lopList.get(spMaLop.getSelectedItemPosition()).getMaLop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void Luu(View view) {
        SinhVien sinhVien = new SinhVien();
        SinhVienDAO sinhVienDAO = new SinhVienDAO(this);
        sinhVien.setMaLop(maLop);
        sinhVien.setTenSV(edtTenSV.getText().toString());
        sinhVien.setSn(edtNgaySinh.getText().toString());
        if (sinhVienDAO.insertSV(sinhVien)<0){
            Toast.makeText(SinhVienActivity.this,"Thêm thất bại",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(SinhVienActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();
        }

    }

    public void Huy(View view) {
            edtTenSV.setText("");
            edtNgaySinh.setText("");

    }
}