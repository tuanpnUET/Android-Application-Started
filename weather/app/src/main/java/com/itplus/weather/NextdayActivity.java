package com.itplus.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NextdayActivity extends AppCompatActivity {
    List<Weather> listWeather;
    WeatherAdapter adapter;
    ListView lvWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextday);
        lvWeather = findViewById(R.id.lvWeather);
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        listWeather = new ArrayList<>();
        adapter = new WeatherAdapter(NextdayActivity.this, R.layout.row_weather, listWeather);
        lvWeather.setAdapter(adapter);
        getJsonNextday();
    }

    private void getJsonNextday() {
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=hanoi&appid=b09cc1914bf25bfda458f787cfd2dcf9&units=metric";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray list = response.getJSONArray("list");
                            for (int i = 0; i < list.length(); i++){
                                JSONObject item = list.getJSONObject(i);
                                String sNgay = item.getString("dt");//Xử lí chuyển ngày
                                long lNgay = Long.parseLong(sNgay);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE,yyyy-MM-dd " +
                                        "HH:mm:ss");
                                Date date = new Date(lNgay*1000);
                                String time = dateFormat.format(date);//Thời gian
                                //Lấy nhiệt độ min - max
                                JSONObject main = item.getJSONObject("main");
                                String min = main.getString("temp_min");//nhiệt độ nhỏ nhất
                                String max = main.getString("temp_max");//Nhiệt độ lớn nhất
                                //Lấy thời tiết
                                JSONArray weather = item.getJSONArray("weather");
                                JSONObject weatherItem = weather.getJSONObject(0);
                                String description = weatherItem.getString("description");
                                String icon = weatherItem.getString("icon");
                                String urlICON = "http://openweathermap.org/img/wn/"+icon+".png";
                                listWeather.add(new Weather(time,description,urlICON,min,max));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("MyError1", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NextdayActivity.this, ""+error.toString(),
                                Toast.LENGTH_LONG).show();
                        Log.d("MyError", error.toString());
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}