package com.example.weatherapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

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
    public void getJsonWeather(){
        
//      RequestQueue requestQueue = Volley.newRequestQueue(this);
//      JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, );
    }

}