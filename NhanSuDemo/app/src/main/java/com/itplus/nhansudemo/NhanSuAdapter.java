package com.itplus.nhansudemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NhanSuAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<NhanSu> list;

    public NhanSuAdapter(MainActivity context, int layout, List<NhanSu> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView txtvHoTen, txtvNgaySinh, txtvDiaChi;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            holder.txtvHoTen = view.findViewById(R.id.txtvHoTen);
            holder.txtvNgaySinh = view.findViewById(R.id.txtvNgaySinh);
            holder.txtvDiaChi = view.findViewById(R.id.txtvDiaChi);
            holder.imgEdit = view.findViewById(R.id.imgEdit);
            holder.imgDelete = view.findViewById(R.id.imgDelete);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        final NhanSu nhanSu = list.get(i);
        holder.txtvHoTen.setText(nhanSu.getHoten());
        holder.txtvNgaySinh.setText(nhanSu.getNgaysinh());
        holder.txtvDiaChi.setText(nhanSu.getDiachi());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "edit:"+nhanSu.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, EditNhanSuActivity.class);
                intent.putExtra("nhanSuEdit", nhanSu);
                context.startActivity(intent);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "del:"+nhanSu.getId(), Toast.LENGTH_SHORT).show();
                confirmDelete(nhanSu.getHoten(), nhanSu.getId());
            }
        });
        return view;
    }

    private void confirmDelete(String hoten, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cảnh báo!");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Bạn thật sự muốn xóa nhân viên "+hoten+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.deleteNhanSu(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
