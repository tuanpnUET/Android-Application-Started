package com.itplus.json;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //readingStringJson();
        //readingObjectJson();
        readArrayJson();
    }
    private void readingStringJson(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url ="http://27.72.96.215/tailieu/lang.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,""+response,Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,""+error,Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(stringRequest);
    }
    private void readingObjectJson(){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url ="http://27.72.96.215/tailieu/khoahoc.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String khoahoc = response.getString("khoahoc");
                            Toast.makeText(MainActivity.this,""+khoahoc,Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception ex){}

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,""+error,Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
    private void readArrayJson(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url ="http://27.72.96.215/tailieu/hocphikhoahoc.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i=0; i < response.length(); i++){
                                JSONObject jsonObject = response.getJSONObject(i);
                                String khoahoc = jsonObject.getString("khoahoc");
                                Toast.makeText(MainActivity.this,""+khoahoc,Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception ex){}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

}