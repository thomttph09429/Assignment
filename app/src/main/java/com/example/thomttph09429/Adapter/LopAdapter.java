package com.example.thomttph09429.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thomttph09429.Model.Lop;
import com.example.thomttph09429.R;

import java.util.List;

public class LopAdapter extends BaseAdapter {
    List<Lop> listLop;
    Context context;

    public LopAdapter(List<Lop> listLop, Context context) {
        this.listLop = listLop;
        this.context = context;
    }
    @Override
    public int getCount() {
        return listLop.size();
    }

    @Override
    public Object getItem(int position) {
        return listLop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lop,parent,false);
            holder = new viewHolder();
            holder.tvMaLop = convertView.findViewById(R.id.TvMaLop);
            holder.TvTenLop = convertView.findViewById(R.id.TvTenLop);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.TvTenLop.setText(listLop.get(position).getTenLop());
        holder.tvMaLop.setText(listLop.get(position).getMaLop());
        return convertView;
    }

    public static class viewHolder {
        TextView tvMaLop, TvTenLop;
    }
}
