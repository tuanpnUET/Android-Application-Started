package org.o7planning.demosqliteclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.shapes.OvalShape;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "sosim.sqlite";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "tbl_sosim";

    public MyDatabase(@Nullable Context context){
        super(context, DB_NAME, null, 1);
    }
    public MyDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void executeSQL( String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor selectData(String selectSQL){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(selectSQL, null);
        return cursor;
    }
    public long insert(String table, String nullColumnHack, ContentValues values){
        SQLiteDatabase database = getWritableDatabase();
        return database.insert(table, nullColumnHack, values);
    }
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs){
        SQLiteDatabase database = getWritableDatabase();
        return database.update(table, values, whereClause, whereArgs);
    }
    public int delete(String table, String whereClause, String[] whereArgs){
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(table, whereClause, whereArgs);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
