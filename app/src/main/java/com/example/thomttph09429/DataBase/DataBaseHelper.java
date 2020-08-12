package com.example.thomttph09429.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.thomttph09429.DAO.LopDAO;
import com.example.thomttph09429.DAO.SinhVienDAO;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String Database_Name = "qlsv";
    public static final int Database_Version= 1;
    public DataBaseHelper(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LopDAO.SQL_TABLE_LOP);
        db.execSQL(SinhVienDAO.SQL_TABLE_SINHVIEN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + LopDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + SinhVienDAO.TABLE_NAME);

    }
}
