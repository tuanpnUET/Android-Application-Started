package com.itplus.nhansudemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditNhanSuActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtHoTen, edtNgaySinh, edtDiaChi;
    Button btnEdit, btnCancel;
    int idEdit = -1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nhan_su);
        mapping();
        btnEdit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        Intent intent = getIntent();
        NhanSu nhanSu = (NhanSu) intent.getSerializableExtra("nhanSuEdit");
        edtHoTen.setText(nhanSu.getHoten());
        edtNgaySinh.setText(nhanSu.getNgaysinh());
        edtDiaChi.setText(nhanSu.getDiachi());
        idEdit = nhanSu.getId();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnEdit:
                updateNhanSu("http://172.168.4.106:81/QLNS/updatedata.php");
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }

    private void updateNhanSu(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){
                            Toast.makeText(EditNhanSuActivity.this, response+", Sửa thành công!!!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(EditNhanSuActivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(EditNhanSuActivity.this, response.toString()+", Sửa không thành công!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditNhanSuActivity.this, error.toString()+", Có lỗi xảy ra", Toast.LENGTH_LONG).show();
                    }
                }
            ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(idEdit));
                map.put("hoten", edtHoTen.getText().toString().trim());
                map.put("ngaysinh", edtNgaySinh.getText().toString().trim());
                map.put("diachi", edtDiaChi.getText().toString().trim());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void mapping() {
        edtHoTen = findViewById(R.id.edtHoTen);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnEdit = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancel);
    }
}
