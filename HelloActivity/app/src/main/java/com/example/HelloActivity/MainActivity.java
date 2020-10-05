package com.example.HelloActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnExit, btnHello;
    EditText editName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExit = findViewById(R.id.btnExit);
        btnHello = findViewById(R.id.btnHello);
        editName = findViewById(R.id.edtName);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
                //finish
            }
        });
        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                Toast.makeText(MainActivity.this,"Hello: "+name,Toast.LENGTH_LONG).show();
            }
        });
    }
}