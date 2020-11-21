package com.itplus.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String city ="";
    static final String API_KEY = "b09cc1914bf25bfda458f787cfd2dcf9";
    TextView txtCity,txtCountry,txtWeather,txtHumudity,txtCloud,txtWind,txtTime,txtTemp;
    EditText edtCity;
    Button btnFindCity, btnNextday;
    ImageView imgWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        btnFindCity.setOnClickListener(this);
        btnNextday.setOnClickListener(this);
        if (city.equals("")){
            getJsonWeather("hanoi");
        }else {
            getJsonWeather(city);
        }


    }



    private void mapping() {
        edtCity = findViewById(R.id.edtCity);
        btnFindCity = findViewById(R.id.btnFindCity);
        btnNextday = findViewById(R.id.btnNextday);
        txtCity = findViewById(R.id.txtCity);
        txtCountry = findViewById(R.id.txtCountry);
        txtWeather = findViewById(R.id.txtWeather);
        txtCloud = findViewById(R.id.txtCloud);
        txtHumudity = findViewById(R.id.txtHumudity);
        txtTime = findViewById(R.id.txtTime);
        txtWind = findViewById(R.id.txtWind);
        txtTemp = findViewById(R.id.txtTemp);
        imgWeather = findViewById(R.id.imgWeather);
        btnFindCity = findViewById(R.id.btnFindCity);
    }
    public void getJsonWeather(final String city){
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+API_KEY+
                "&units=metric";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("weather");
                            JSONObject weatherObj = jsonArray.getJSONObject(0);
                            //Lấy icon
                            String icon = weatherObj.getString("icon");
                            String urlICON = "http://openweathermap.org/img/wn/"+icon+".png";
                            Picasso.get().load(urlICON).into(imgWeather);
                            // Lấy trạng thái
                            String tempState = weatherObj.getString("main");
                            txtWeather.setText(tempState);
                            //nhiệt độ
                            JSONObject main = response.getJSONObject("main");
                            String temp = main.getString("temp");
                            txtTemp.setText(temp+"ºC");
                            //độ ẩm
                            String humidity = main.getString("humidity");
                            txtHumudity.setText(humidity+"%");
                            //Tốc độ gió
                            JSONObject wind = response.getJSONObject("wind");
                            String speed = wind.getString("speed");
                            txtWind.setText(speed+"m/s");
                            //Lấy % mây
                            JSONObject clouds = response.getJSONObject("clouds");
                            String all = clouds.getString("all");
                            txtCloud.setText(all+"%");
                            //Lấy thời gian
                            String sNgay = response.getString("dt");
                            long lNgay = Long.parseLong(sNgay);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE,yyyy-MM-dd " +
                                    "HH:mm:ss");
                            Date date = new Date(lNgay*1000);
                            String currentTime = dateFormat.format(date);//ngày giờ hiện tại
                            txtTime.setText(currentTime);
                            //Lấy tên thành phố
                            String name = response.getString("name");
                            txtCity.setText("City: "+name);
                            //Lấy tên đất nước
                            JSONObject sys = response.getJSONObject("sys");
                            String country = sys.getString("country");
                            txtCountry.setText("Country: "+country);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Không có dữ liệu"+city,
                                Toast.LENGTH_LONG).show();
                        Log.d("MyError", error.toString());
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFindCity:
                city = edtCity.getText().toString().trim();
                getJsonWeather(city);
                break;
            case R.id.btnNextday:
                Intent intent = new Intent(MainActivity.this, NextdayActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);
                break;
        }

    }
}