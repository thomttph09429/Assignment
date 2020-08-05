package com.example.nghiepnv_ph09589_assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nghiepnv_ph09589_assignment.DataBase.DataBaseHelper;
import com.example.nghiepnv_ph09589_assignment.Model.Lop;

import java.util.ArrayList;
import java.util.List;

public class LopDAO {
    public static final String TABLE_NAME = "Lop";
    public DataBaseHelper dbHelper;
    public SQLiteDatabase db;
    private Context context;
    public static final String SQL_TABLE_LOP = "CREATE TABLE Lop ( maLop text primary key, tenLop Text)";

    public LopDAO(Context context){
        this.context=context;
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }
    public int insertLop(Lop lop){
        ContentValues values = new ContentValues();
        values.put("MaLop",lop.getMaLop());
        values.put("TenLop",lop.getTenLop());
        if (db.insert(TABLE_NAME,null,values)<0){
            return -1;
        }
        return 1;

    }
    public List<Lop> getAllLop(){
        List<Lop> listLop = new ArrayList<>();
        String Select = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(Select,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Lop lop = new Lop();
            lop.setMaLop(cursor.getString(0));
            lop.setTenLop(cursor.getString(1));
            listLop.add(lop);
            cursor.moveToNext();
        }
        cursor.close();
        return listLop;
    }
//    public List<String> laylop(){
//        List<String> lops = new ArrayList<>();
//        String Select = "SELECT * FROM "+TABLE_NAME;
//        Cursor cursor = db.rawQuery(Select, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            Lop lop = new Lop();
//            lop.setMaLop(cursor.getString(cursor.getColumnIndex("maLop")));
//            lop.setTenLop(cursor.getString(cursor.getColumnIndex("tenLop")));
//            lops.add(lop.getMaLop()+"-"+lop.getTenLop());
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return lops;
//    }
    public int delete(String malop){
        if (db.delete(TABLE_NAME,"malop=?",new String[]{malop})<0){
            return -1;
        }
        return 1;
    }
    public int updateLop(Lop lop){
        ContentValues values = new ContentValues();
        values.put("maLop",lop.getMaLop());
        values.put("tenLop",lop.getTenLop());
        if (db.update(TABLE_NAME, values, "maLop=?", new String[]{lop.getMaLop().toString()}) <0){
            return -1;//update k thành công
        }
        return 1;//thanh công
    }
}
