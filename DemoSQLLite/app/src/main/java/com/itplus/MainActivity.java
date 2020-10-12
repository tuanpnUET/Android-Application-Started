package com.itplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    private static final String INTERNAL_PATH = Environment.getDataDirectory().getPath()+"/data/com.itplus/";
  //  private static final String EXTERNAL_PATH = Environment.getExternalStorageDirectory().getPath()+"/data/com.itplus";
    EditText edtUserName, edtPassWord;
    CheckBox ckSaveLogin;
    Button btnLogin, btnHuy;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
    }
    public void login(View view){
        String username = edtUserName.getText().toString();
        String password = edtPassWord.getText().toString();
        String checked = (ckSaveLogin.isChecked())?"true":"false";
        if (username != null & username.equals("admin") && password != null & password.equals("123456")){
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            //SharedPreferences.Editor editor = sharedPreferences.edit();
            if (ckSaveLogin.isChecked()){
                saveData(username, password, checked);
//                editor.putString("username", username);
//                editor.putString("password", password);
//                editor.putBoolean("checked", ckSaveLogin.isChecked());
//                editor.commit();
            }else {
                deleteData();
//                editor.remove("username");
//                editor.remove("password");
//                editor.remove("checked");
//                editor.commit();
            }
        }else {
            Toast.makeText(this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
        }
    }
    private void saveData(String username, String password , String checked){
        String path_cache = getCacheDir().getPath();
        try {
            String filename = "infor.txt";
            String path = INTERNAL_PATH + filename;
            File file = new File(path);
            if (!file.exists()){
                file.createNewFile();
            }
 //           FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            String data = username + ":" + password + ":" + checked + "\n";
            byte[] buff = data.getBytes();
//            fileOutputStream.write(buff, 0, buff.length);
//            fileOutputStream.close();
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(data);
            fileWriter.close();
            Toast.makeText(this, "Lưu file thành công", Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Log.d("error", ex.getMessage());
        }
    }

    private void deleteData() {
        try {
            String filename = "";
            String path = INTERNAL_PATH +filename;
            File file = new File(path);
            if (file.exists()){
                file.delete();
            }
        }catch (Exception ex){

        }
    }

    private void init(){
//         sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
//         String username = sharedPreferences.getString("username", "");
//         String password = sharedPreferences.getString("password", "");
//         boolean checked = sharedPreferences.getBoolean("checked", false);
        getData();
//        String username = "";
//        String password = "";
//        boolean checked = false;
//        edtPassWord.setText(password);
//        edtUserName.setText(username);
//        ckSaveLogin.setChecked(checked);
    }
    private void getData(){
        try {
            String filename = "infor.txt";
            String path = INTERNAL_PATH +filename;
            File file = new File(path);
            if (!file.exists()){
                Toast.makeText(this, "File "+file+" không tồn tại", Toast.LENGTH_SHORT).show();
            }
            FileInputStream fileInputStream = new FileInputStream(path);
            String data = "";
            int len;
            byte[] buff = new byte[1024];
            while ((len = fileInputStream.read(buff)) > 0 ){
                data += new String(buff, 0, len);

            }
            fileInputStream.close();
            String arr[] = data.split(":");
            edtUserName.setText(arr[0]);
            edtPassWord.setText(arr[1]);
            boolean checked = (arr[2].startsWith("true"))?true:false;
            ckSaveLogin.setChecked(checked);
        }catch (Exception ex){
            Log.d("error", ex.getMessage());
        }
    }
    private void mapping(){
        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        ckSaveLogin = findViewById(R.id.ckSaveLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnHuy = findViewById(R.id.btnHuy);
    }
}