package com.weatherpredictedapp;


import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    final String API_KEY = "acbae9c57a24663635f3918fd4e8f0c7";
    EditText editText;
    Button btnOK;
    TextView txtCity;
    TextView txtCountry;
    ImageView imgIcon;
    TextView txtTemp;
    TextView txtStatus;
    TextView txtValue1;
    TextView txtValue2;
    TextView txtValue3;
    Button btnNextDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        getJsonWeather("hanoi");
    }

    private void mapping(){
        editText = findViewById(R.id.editText);
        btnOK = findViewById(R.id.btnOk);
        txtCity = findViewById(R.id.txtCity);
        txtCountry = findViewById(R.id.txtCountry);
        imgIcon = findViewById(R.id.imgIcon);
        txtTemp = findViewById(R.id.txtTemp);
        txtStatus = findViewById(R.id.txtStatus);
        txtValue1 = findViewById(R.id.txtValue1);
        txtValue2 = findViewById(R.id.txtValue2);
        txtValue3 = findViewById(R.id.txtValue3);
        btnNextDay = findViewById(R.id.btnNextDay);
    }
    public void getJsonWeather(String city){
        String url = "http://api.openweathermap.org/data/2.5/forecast?q="+city+"appid="+API_KEY+"&units=metric";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,""+error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("MyError:", error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

}