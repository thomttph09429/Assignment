package com.example.nghiepnv_ph09589_assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nghiepnv_ph09589_assignment.DataBase.DataBaseHelper;
import com.example.nghiepnv_ph09589_assignment.Model.Lop;
import com.example.nghiepnv_ph09589_assignment.Model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {
    public static final String TABLE_NAME = "SinhVien";
    public DataBaseHelper dbHelper;
    public SQLiteDatabase db;
    private Context context;
    public static final String SQL_TABLE_SINHVIEN = "CREATE TABLE SinhVien ( tensinhVien text primary key, maLop Text, sn text)";

    public SinhVienDAO(Context context){
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }
    public int insertSV(SinhVien sv){
        ContentValues values = new ContentValues();
        values.put("maLop",sv.getMaLop());
        values.put("tensinhVien",sv.getTenSV());
        values.put("sn",sv.getSn());
        if (db.insert(TABLE_NAME,null,values)<0){
            return -1;
        }
        return 1;

    }
    public List<SinhVien> getAllSinhVien(){
        List<SinhVien> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToNext();
        while (cursor.isAfterLast()==false){
            SinhVien sv = new SinhVien();
            sv.setMaLop(cursor.getString(1));
            sv.setTenSV(cursor.getString(0));
            sv.setSn(cursor.getString(2));
            list.add(sv);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public int delete(String malop){
        if (db.delete(TABLE_NAME,"tensinhVien=?",new String[]{malop})<0){
            return -1;
        }
        return 1;
    }
}
