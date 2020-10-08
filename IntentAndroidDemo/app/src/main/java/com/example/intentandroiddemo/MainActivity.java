package com.example.intentandroiddemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
        private static final int REQ_CODE_CALL_PHONE = 123 ;
        private static final int REQ_CAM = 124;
        ImageView imgChorme;
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

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                //Log.d("grantResults", grantResults[0]+"");
                if (requestCode == REQ_CODE_CALL_PHONE){
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                                calling();
                        }
                }
//                }else if (requestCode == REQ_CODE_CAM){
//
//                }

        }
        private void onAcitivityResult(int requestCode, int resultCode, @Nullable Intent data){
                super.onActivityResult(requestCode, resultCode, data);
                if(requestCode == REQ_CAM){
                        if(resultCode == RESULT_OK){

                        }
                }

        }
        private void mapping(){
                imgChorme = findViewById(R.id.imgChorme);
        }
}