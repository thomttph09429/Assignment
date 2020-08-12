package com.example.thomttph09429;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thomttph09429.Adapter.SinhVienAdapter;
import com.example.thomttph09429.DAO.SinhVienDAO;
import com.example.thomttph09429.Model.SinhVien;
import com.example.thomttph09429.R;

import java.util.List;

public class ListSinhVienActivity extends AppCompatActivity {
    ListView listView;
    List<SinhVien> sinhVienList;
    SinhVienAdapter sinhVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sinh_vien);
        setTitle("Danh Sách Sinh Viên");
        listView = findViewById(R.id.lv);
        SinhVienDAO sinhVienDAO = new SinhVienDAO(this);
        sinhVienList = sinhVienDAO.getAllSinhVien();
        sinhVienAdapter = new SinhVienAdapter(sinhVienList,ListSinhVienActivity.this);
        listView.setAdapter(sinhVienAdapter);
        sinhVienAdapter.notifyDataSetChanged();

        registerForContextMenu(listView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.add){
            Intent intent = new Intent(ListSinhVienActivity.this,SinhVienActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_xoa,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final SinhVienDAO sinhVienDAO = new SinhVienDAO(this);
        final int pos = menuInfo.position;
        final String position = sinhVienList.get(pos).getMaLop();
        int id = item.getItemId();
        if (id == R.id.menu_ctx_del) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListSinhVienActivity.this);
            builder.setTitle("Xác nhận xóa thông tin?");
            builder.setMessage("Bạn muốn xóa danh sách này");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    long xoasv = sinhVienDAO.delete(position);
                    if (xoasv > 0) {
                        Toast.makeText(ListSinhVienActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        sinhVienList = sinhVienDAO.getAllSinhVien();
                        sinhVienAdapter = new SinhVienAdapter(sinhVienList, ListSinhVienActivity.this);
                        listView.setAdapter(sinhVienAdapter);
                        sinhVienAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(ListSinhVienActivity.this, "Xóa thể loại không thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
//            lopDAO = new LopDAO(this);
//            lopList = lopDAO.getAllLop();
//            lopAdapter = new LopAdapter(lopList,ListLopActivity.this);
//            listView.setAdapter(lopAdapter);
//            lopAdapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }
}