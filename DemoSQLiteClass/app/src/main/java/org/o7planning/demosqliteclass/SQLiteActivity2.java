package org.o7planning.demosqliteclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SQLiteActivity2 extends AppCompatActivity {
    //                myDatabase = new MyDatabase(SQLiteActivity2.this, "sosim.sqlite", null,1);
    TextView txtvId, txtvSo, txtvGia;
    Button btnFirst,btnNext,btnPre, btnLast;
    final String TABLE_NAME = "tbl_sosim";
    Cursor cursor= null ;
    SQLiteDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite2);
//        init();
        myDatabase = openOrCreateDatabase("sosim.sqlite", MODE_PRIVATE, null);
        String sql_select = "select * from " + TABLE_NAME;
        cursor = myDatabase.rawQuery("Select * from tbl_sosim", null);
        mapping();
//        printData(TABLE_NAME);
        cursor.moveToNext();
        loadData();

//  test hàm insert
//        ContentValues cv = new ContentValues();
//        cv.put("so", "0966666666");
//        cv.put("gia", "3000000");
//        long id = myDatabase.insert(MyDatabase.TABLE_NAME, null, cv);
//        Toast.makeText(this, "id="+id, Toast.LENGTH_SHORT).show();
//  test ham update
//        String[] whereArgs = {"1"};
//        ContentValues updValues = new ContentValues();
//        updValues.put("so", "0123456789");
//        updValues.put("gia","1000000");
//        int recAffected = myDatabase.update("tbl_sosim", updValues,"id=?", whereArgs );
//        Toast.makeText(this, "result="+recAffected, Toast.LENGTH_SHORT).show();
//   Test ham delete
//          String[] whereArgs = {"1"};
//          int recAffected = myDatabase.delete("tbl_sosim", "id = ?", whereArgs);
//          Toast.makeText(this, "result"+recAffected, Toast.LENGTH_SHORT).show();

//        String insert1 = "insert into tbl_sosim values(null,'000000999','100000')";
//        myDatabase.executeSQL(insert1);
//        String insert2 = "insert into tbl_sosim values(null,'000333999','123456')";
//        myDatabase.executeSQL(insert2);
//        String insert3 = "insert into tbl_sosim values(null,'666600999','234515')";
//        myDatabase.executeSQL(insert3);
//        String insert4 = "insert into tbl_sosim values(null,'000222299','224353')";
//        myDatabase.executeSQL(insert4);
//        String insert5 = "insert into tbl_sosim values(null,'111000999','344656')";
//        myDatabase.executeSQL(insert5);


//        String sql_update = "update tbl_sosim set so = '111055599' , gia = '894375' where id = 3";
//        myDatabase.executeSQL(sql_update);
//        String sql_delete = "delete from tbl_sosim where id = 2";
//        myDatabase.executeSQL(sql_delete);
//        printData("tbl_sosim");
//        mapping();
//        loadData();
        //loadFirst();
        //Toast.makeText(this, "OK",Toast.LENGTH_SHORT).show();
    }
    private void init(){
//        myDatabase = new MyDatabase(SQLiteActivity2.this);
//        String sql_create_table = "create table if not exists tbl_sosim (id integer primary key autoincrement, so text, gia text) ";
//        myDatabase.executeSQL(sql_create_table);
    }
    public void first(View view){
        cursor.moveToFirst();
        loadData();
    }
    public void previous(View view){
        cursor.moveToPrevious();
        loadData();
    }
    public void next(View view){
        cursor.moveToNext();
        loadData();
    }
    public void last(View view){
        cursor.moveToLast();
        loadData();
    }
    private void loadFirst(){
        String sql_select = "select * from "+ TABLE_NAME;
        Log.d("aaa", cursor+"is ok");
        cursor.moveToNext();
        cursor = myDatabase.rawQuery(sql_select, null);
        Log.d("aaa", cursor+"is ok");
    }
    private void printData(String tableName) {
            String sql_select = "select * from " + TABLE_NAME;
            Cursor cursor = myDatabase.rawQuery(sql_select, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String so = cursor.getString(1);
                String gia = cursor.getString(2);
                Sosim soSim = new Sosim(id, so, gia);
                Toast.makeText(this, "" + soSim, Toast.LENGTH_SHORT).show();
            }

    }
    private void mapping(){
        txtvId = (TextView) findViewById(R.id.txtvId);
        txtvSo = (TextView) findViewById(R.id.txtvSo);
        txtvGia = (TextView) findViewById(R.id.txtvGia);
        btnFirst = (Button)  findViewById(R.id.btnFirst);
        btnPre = (Button) findViewById(R.id.btnPre);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnLast = (Button) findViewById(R.id.btnLast);

    }
    private void loadData() {
//        Log.d("aaaaaa", cursor.getString(0));
        txtvId.setText("Id : "+cursor.getString(0));
        txtvSo.setText("Số sim: "+cursor.getString(1));
        txtvGia.setText("Giá sim: "+cursor.getString(2));
        btnFirst.setEnabled(!cursor.isFirst());
        btnPre.setEnabled(!cursor.isFirst());
        btnNext.setEnabled(!cursor.isLast());
        btnLast.setEnabled(!cursor.isLast());
    }
    public List<Sosim> getAll() {
        List<Sosim> list = new ArrayList<>();
        String sql_select = "select * from " + TABLE_NAME;
        Cursor cursor = myDatabase.rawQuery(sql_select, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String so = cursor.getString(1);
            String gia = cursor.getString(2);
            Sosim soSim = new Sosim(id, so, gia);
            list.add(soSim);
        }
        return list;
    }

}