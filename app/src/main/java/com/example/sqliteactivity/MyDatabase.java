package com.example.sqliteactivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void executeSQL(String sql){
        SQLiteDatabase database = getReadableDatabase();
        database.execSQL(sql);
    }
    public Cursor selectData(String selectSQL){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(selectSQL, null);
        return cursor;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
