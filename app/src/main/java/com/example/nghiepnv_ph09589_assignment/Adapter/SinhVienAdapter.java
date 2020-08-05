package com.example.nghiepnv_ph09589_assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nghiepnv_ph09589_assignment.Model.Lop;
import com.example.nghiepnv_ph09589_assignment.Model.SinhVien;
import com.example.nghiepnv_ph09589_assignment.R;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    List<SinhVien> sinhVienList;
    Context context;

    public SinhVienAdapter(List<SinhVien> sinhVienList , Context context) {
        this.sinhVienList = sinhVienList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return sinhVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sv,parent,false);
            holder = new viewHolder();
            holder.TvMaLop = convertView.findViewById(R.id.TvMaLop);
            holder.TvTenSV = convertView.findViewById(R.id.TvTen);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.TvMaLop.setText(sinhVienList.get(position).getMaLop());
        holder.TvTenSV.setText(sinhVienList.get(position).getTenSV());
        return convertView;
    }

    public static class viewHolder {
        TextView TvMaLop, TvTenSV;
    }
}
