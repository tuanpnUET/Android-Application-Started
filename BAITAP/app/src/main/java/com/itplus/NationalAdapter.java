package com.itplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NationalAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<National> list;
    public NationalAdapter(Context context, int layout, List<National> list){
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, null);
        ImageView imgFlag = convertView.findViewById(R.id.flag);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtPop = convertView.findViewById(R.id.txtPop);
        TextView txtArea = convertView.findViewById(R.id.txtArea);
        National national = list.get(position);
        imgFlag.setImageResource(national.getFlag());
        txtName.setText(national.getName());
        txtPop.setText("Populations: "+national.getPopulation());
        txtArea.setText("Area: "+national.getArea()+" km2");
        return convertView;
    }
}
