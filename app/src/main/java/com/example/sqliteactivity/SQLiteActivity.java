package com.example.sqliteactivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SQLiteActivity extends AppCompatActivity {
    MyDatabase myDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        MyDatabase myDatabase = new MyDatabase(SQLiteActivity.this, "sosim.sqlite", null, 1);
//        String sql_create_table = "create table if not exists sosim(id integer primary key autoincrement, so text, gia text)";
//        myDatabase.executeSQL(sql_create_table);
//        String sql_insert1 = "insert into sosim values(null,'0968523456','100000')";
//        myDatabase.executeSQL(sql_insert1);
//        String sql_insert2 = "insert into sosim values(null,'0968523455','100000')";
//        myDatabase.executeSQL(sql_insert2);
//        String sql_insert3 = "insert into sosim values(null,'0968525836','100000')";
//        myDatabase.executeSQL(sql_insert3);
//        String sql_update = "update tbl_sosim set so = '09876545654', gia = '50000' where id =2";
//        myDatabase.executeSQL(sql_update);
//        String sql_delete = "delete from tbl_sosim where id = 1";
//        myDatabase.executeSQL(sql_delete);
        print_data();
        Toast.makeText(this, "OK nhớ", Toast.LENGTH_SHORT).show();
    }
    private void print_data(){
        Cursor cursor = myDatabase.selectData("Select * from tbl_sosim");
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String so = cursor.getString(1);
            String gia = cursor.getString(2);
            Sim soSim = new Sim(id, so, gia);
            Toast.makeText(this, ""+soSim, Toast.LENGTH_SHORT).show();
        }
    }
}
