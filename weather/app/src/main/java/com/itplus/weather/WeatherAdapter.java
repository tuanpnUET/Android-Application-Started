package com.itplus.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Weather> list;

    public WeatherAdapter(Context context, int layout, List<Weather> list) {
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
        TextView txtTimeNextday,txtState,txtMin,txtMax;
        ImageView imgState;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);
            viewHolder.txtTimeNextday = convertView.findViewById(R.id.txtTimeNextday);
            viewHolder.txtState = convertView.findViewById(R.id.txtState);
            viewHolder.txtMin = convertView.findViewById(R.id.txtMin);
            viewHolder.txtMax = convertView.findViewById(R.id.txtMax);
            viewHolder.imgState = convertView.findViewById(R.id.imgState);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Weather weather = list.get(position);
        viewHolder.txtTimeNextday.setText(weather.getTime());
        viewHolder.txtState.setText(weather.getState());
        viewHolder.txtMin.setText(weather.getMin()+"ºC");
        viewHolder.txtMax.setText(weather.getMax()+"ºC");
        Picasso.get().load(weather.getUrlIcon()).into(viewHolder.imgState);
        return convertView;
    }
}
