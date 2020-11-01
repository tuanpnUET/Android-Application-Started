package com.itplus.nhansudemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView lvNhanSu;
    Menu mnuAddNhanSu;
    List<NhanSu> nhanSuList;
    NhanSuAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nhanSuList = new ArrayList<>();
        adapter = new NhanSuAdapter(MainActivity.this, R.layout.activity_line_nhansu, nhanSuList);
        lvNhanSu = findViewById(R.id.lvNhanSu);
        getAllData();
        lvNhanSu.setAdapter(adapter);
    }

    private void getAllData() {
        String url = "http://192.168.1.3/android/getAllData.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        nhanSuList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                nhanSuList.add(new NhanSu(jsonObject.getInt("id"), jsonObject.getString("hoten"), jsonObject.getString("ngaysinh"), jsonObject.getString("diachi")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        requestQueue.add(arrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuAddNhanSu){
            startActivity(new Intent(MainActivity.this, AddNhanSuActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    public void deleteNhanSu(final int id){
        String url = "http://192.168.1.3/android/deletedata.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){
                            Toast.makeText(MainActivity.this, response+", Xóa thành công!", Toast.LENGTH_LONG).show();
                            getAllData();
                        }
                        else {
                            Toast.makeText(MainActivity.this, response+", Xóa không thành công!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString()+", Có lỗi xảy ra", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(id));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}