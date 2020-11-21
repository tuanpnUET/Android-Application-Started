package com.itplus.nhansudemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddNhanSuActivity extends AppCompatActivity {
    EditText edtHoTen, edtNgaySinh, edtDiaChi;
    Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nhan_su);
        mapping();
    }

    public void addNhanSu(View view){
        String hoten = edtHoTen.getText().toString().trim();
        String ngaysinh = edtNgaySinh.getText().toString().trim();
        String diachi = edtDiaChi.getText().toString().trim();
        if (hoten.equals("")||ngaysinh.matches("")||diachi.isEmpty()){
            if (hoten.equals("")){
                Toast.makeText(this, "Bạn phải nhập họ tên", Toast.LENGTH_LONG).show();
                edtHoTen.requestFocus();
            }
            else if (ngaysinh.equals("")){
                Toast.makeText(this, "Bạn phải nhập ngày sinh", Toast.LENGTH_LONG).show();
                edtNgaySinh.requestFocus();
            }
            else if (diachi.equals("")){
                Toast.makeText(this, "Bạn phải nhập địa chỉ", Toast.LENGTH_LONG).show();
                edtDiaChi.requestFocus();
            }
        }
        else{
            insertNhanSu("http://172.168.4.106:81/QLNS/insertdata.php");
        }
    }

    private void insertNhanSu(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){
                            Toast.makeText(AddNhanSuActivity.this, response+", thêm mới thành công!!!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(AddNhanSuActivity.this, response+", thêm mới không thành công!!!", Toast.LENGTH_LONG).show();
                        }
                        startActivity(new Intent(AddNhanSuActivity.this, MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddNhanSuActivity.this, error.toString()+", có lỗi xảy ra trong quá trình thêm mới", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("hoten", edtHoTen.getText().toString());
                map.put("ngaysinh", edtNgaySinh.getText().toString());
                map.put("diachi", edtDiaChi.getText().toString());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void cancel(View view){
        finish();
    }

    private void mapping() {
        edtHoTen = findViewById(R.id.edtHoTen);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }
}