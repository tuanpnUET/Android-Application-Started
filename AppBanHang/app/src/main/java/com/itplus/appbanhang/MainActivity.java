package com.itplus.appbanhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    TextView txtvLastistProduct;
    RecyclerView rvLastistProduct;
    NavigationView navigationView;
    ListView lvNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
    }

    private void mapping() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        viewFlipper = findViewById(R.id.viewFlipper);
        txtvLastistProduct = findViewById(R.id.txtvLastistProduct);
        rvLastistProduct = findViewById(R.id.rvLastistProduct);
        navigationView = findViewById(R.id.navigationView);
       // lvNavigation = findViewById(R.id.lvNavigation);
    }
    private void init(){
        initActionBar();
    }
    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}