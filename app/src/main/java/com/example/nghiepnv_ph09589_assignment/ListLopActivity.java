package com.example.nghiepnv_ph09589_assignment;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nghiepnv_ph09589_assignment.Adapter.LopAdapter;
import com.example.nghiepnv_ph09589_assignment.DAO.LopDAO;
import com.example.nghiepnv_ph09589_assignment.Model.Lop;

import org.w3c.dom.Text;

import java.util.List;

public class ListLopActivity extends AppCompatActivity {
    ListView listView;
    TextView tvSuaMaLop;
    EditText edtSuaTenLop;
    LopDAO lopDAO;
    List<Lop> lopList;
    LopAdapter lopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lop);
        listView = findViewById(R.id.lv);
        lopDAO = new LopDAO(this);
        lopList = lopDAO.getAllLop();
        lopAdapter = new LopAdapter(lopList,ListLopActivity.this);
        listView.setAdapter(lopAdapter);
        lopAdapter.notifyDataSetChanged();
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
            Intent intent = new Intent(ListLopActivity.this,ThemLopActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final int pos = menuInfo.position;
        final String position = lopList.get(pos).getMaLop();
        int id=item.getItemId();
        if (id==R.id.menu_ctx_del){
            AlertDialog.Builder builder = new AlertDialog.Builder(ListLopActivity.this);
            builder.setTitle("Xác nhận xóa thông tin?");
            builder.setMessage("Bạn muốn xóa danh sách này");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    long xoalop = lopDAO.delete(position);
                    if (xoalop>0){
                        Toast.makeText(ListLopActivity.this, "Xóa thể loại thành công", Toast.LENGTH_SHORT).show();
                        lopDAO = new LopDAO(ListLopActivity.this);
                        lopList = lopDAO.getAllLop();
                        lopAdapter = new LopAdapter(lopList,ListLopActivity.this);
                        listView.setAdapter(lopAdapter);
                        lopAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(ListLopActivity.this, "Xóa thể loại không thành công!", Toast.LENGTH_SHORT).show();
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
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListLopActivity.this);
            View mview = getLayoutInflater().inflate(R.layout.suathongtinlop,null);
            tvSuaMaLop = mview.findViewById(R.id.edtMaLop);
            edtSuaTenLop = mview.findViewById(R.id.edtsuaTenLop);
            builder.setView(mview);

            tvSuaMaLop.setText(lopList.get(pos).getMaLop());
            edtSuaTenLop.setText(lopList.get(pos).getTenLop());

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Lop lop = new Lop();
                    lop.setMaLop(tvSuaMaLop.getText().toString());
                    lop.setTenLop(edtSuaTenLop.getText().toString());
                    if (lopDAO.updateLop(lop)<0){
                        Toast.makeText(ListLopActivity.this,"update thất bại",Toast.LENGTH_LONG).show();
                    }else {

                        lopDAO = new LopDAO(ListLopActivity.this);
                        lopList = lopDAO.getAllLop();
                        lopAdapter = new LopAdapter(lopList,ListLopActivity.this);
                        listView.setAdapter(lopAdapter);
                        lopAdapter.notifyDataSetChanged();
                        Toast.makeText(ListLopActivity.this,"update thành công",Toast.LENGTH_LONG).show();
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
        }

        return super.onContextItemSelected(item);
    }
}