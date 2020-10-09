package com.itplus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_CALL_PHONE = 123;
    private static final int REQ_CODE_CAM = 124;
    private static final int REQ_CAM = 200;
    ImageView imgChorme;
//    ImageView imgMes;
//    ImageView imgCall;
//    ImageView imgCamera;
    ImageView imgNoPreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
    }
    public void openChrome(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/watch?v=329d75wpmW8"));
        startActivity(intent);
    }
    public void openMessage(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.putExtra("sms_body","HELLO GÁI");
        intent.setData(Uri.parse("sms:0963685229"));
        startActivity(intent);
    }
    public void openGallery(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        startActivityForResult(intent, REQ_CODE_CAM);
    }
    public void openCalling(View view){
        configUser();
    }
    private void calling(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:0945673592"));
        startActivity(intent);
    }
    private void configUser(){
        String permission_call_phone = Manifest.permission.CALL_PHONE;
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission_call_phone) != PackageManager.PERMISSION_GRANTED){
            //CHưa có quyền cần xin quyền người dùng
            String[] permission = new String[]{Manifest.permission.CALL_PHONE};
            ActivityCompat.requestPermissions(MainActivity.this, permission, REQ_CODE_CALL_PHONE);
        }else {
           //Có quyền mở thẳng luôn
            calling();

        }
        
    }

    public void openCamera(View view){
        confirmCamera();
    }
    private void camera(){
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQ_CAM);
    }
    private void confirmCamera(){
        String permission_call_phone = Manifest.permission.CAMERA;
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission_call_phone) != PackageManager.PERMISSION_GRANTED){
            //CHưa có quyền cần xin quyền người dùng
            String[] permission = new String[]{Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(MainActivity.this, permission, REQ_CODE_CAM);
        }else {
            //Có quyền mở thẳng luôn
            camera();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CAM){
            if (resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                imgNoPreview.setImageBitmap(bitmap);
            }
        }
    }
    protected  void openGallery(){

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Log.d("grantResults", grantResults[0]+"");
        if (requestCode == REQ_CODE_CALL_PHONE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                calling();
            }
        }else if (requestCode == REQ_CODE_CAM) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                camera();
            }
        }

    }


    private void mapping(){
        imgChorme = findViewById(R.id.imgChorme);
        //imgMes = findViewById(R.id.imgMes);
        //imgCall = findViewById(R.id.imgCall);
        //imgCamera = findViewById(R.id.imgCamera);
        imgNoPreview = findViewById(R.id.imgNoPreview);
    }
}