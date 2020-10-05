package com.itplus;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvNational;
    NationalAdapter adapter;
    List<National> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainnational);
        mapping();
        init();
        lvNational.setAdapter(adapter);
    }
    private void init(){
        list = new ArrayList<>();
        list.add(new National(R.drawable.cambodia, "Cambodia", "31 133 123", "2123 312 123"));
        list.add(new National(R.drawable.covietnam, "Dong Lao", "143 133 123", "2133 312 123"));
        list.add(new National(R.drawable.lao, "Lao", "31 133 123", "123 312 123"));
        list.add(new National(R.drawable.miama, "Mianma", "31 133 123", "73 312 123"));
        list.add(new National(R.drawable.trieutien, "Trieu Tien", "31 133 123", "23 312 123"));
        list.add(new National(R.drawable.uc, "Australia", "122 213 123","423 123 213"));
        adapter = new NationalAdapter(MainActivity.this, R.layout.activitynaional, list);
    }
    private void mapping(){
        lvNational = findViewById(R.id.lvNAtional);
    }
}