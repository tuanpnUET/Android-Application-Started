package com.itplus.qlbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.itplus.qlbanhang.Adapter.CategoryAdapter;
import com.itplus.qlbanhang.Adapter.ProductAdapter;
import com.itplus.qlbanhang.Model.Category;
import com.itplus.qlbanhang.Model.Product;
import com.itplus.qlbanhang.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    TextView txtvLastistProduct;
    RecyclerView rvLastistProduct;
    NavigationView navigationView;
    ListView lvNavigation;
    List<Product> productList;
    ProductAdapter adapter = null;
    List<Category> categoryList;
    CategoryAdapter categoryAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();


        productList = new ArrayList<>();
        adapter = new ProductAdapter(MainActivity.this, R.layout.actitvity_product, productList);
        getLastestProduct();
        rvLastistProduct = findViewById(R.id.rvLastestProduct);
        rvLastistProduct.setAdapter(adapter);
        rvLastistProduct.setLayoutManager( new GridLayoutManager(MainActivity.this,2));

        categoryList = new ArrayList<>();
        getAllCategory();
        categoryAdapter = new CategoryAdapter(MainActivity.this, R.layout.actitvity_line_category, categoryList);
        lvNavigation = findViewById(R.id.lvNavigation);
        lvNavigation.setAdapter(categoryAdapter);

    }

    private void getAllCategory() {
        String url = "http://172.168.4.106:81/QLTB/getAllCategory.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        categoryList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Category category = new Category(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("avatar"));
                                Toast.makeText(MainActivity.this, ""+category.toString(), Toast.LENGTH_SHORT).show();
                                categoryList.add(category);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        categoryAdapter.notifyDataSetChanged();
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

    private void getAllSlider() {
        String url = "http://172.168.4.106:81/QLTB/getAllSlider.php";
    }

    private void getLastestProduct() {
        String url = "http://172.168.4.106:81/QLTB/getLastestProduct.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        productList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Product product = new Product(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getInt("price"), jsonObject.getString("avatar"), jsonObject.getString("description"), jsonObject.getInt("categoryid"));
//                                Toast.makeText(MainActivity.this, ""+product.toString(), Toast.LENGTH_SHORT).show();
                                productList.add(product);
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

    private void mapping() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        viewFlipper = findViewById(R.id.viewFlipper);
        txtvLastistProduct = findViewById(R.id.txtvLastestProduct);
        rvLastistProduct = findViewById(R.id.rvLastestProduct);
        navigationView = findViewById(R.id.navigationView);
        lvNavigation = findViewById(R.id.lvNavigation);
    }
    private void init(){
        initActionBar();
        initViewFlipper();
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
    private void initViewFlipper() {
        List<String> imageUrlList = new ArrayList<>();
        imageUrlList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/Banner-chung-595x100.png");
        imageUrlList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/595x100iPhone-12-v2.png");
        imageUrlList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/oppo_normal_sale_24.08.2020.png");
        imageUrlList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/Cate_595x100_2.png");
        imageUrlList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/normal_sale_nokia_24.08.2020.png");

        ImageView imageView;
        for (int i = 0; i < imageUrlList.size(); i++) {
            imageView = new ImageView(this);
            Picasso.get().load(imageUrlList.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
            viewFlipper.setFlipInterval(3000);
            viewFlipper.setAutoStart(true);
        }
        Animation animationIn = AnimationUtils.loadAnimation(this, R.anim.anim_flipper_in);
        Animation animationOut =AnimationUtils.loadAnimation(this, R.anim.anim_filpper_out);
        viewFlipper.setInAnimation(animationIn);
        viewFlipper.setInAnimation(animationOut);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}