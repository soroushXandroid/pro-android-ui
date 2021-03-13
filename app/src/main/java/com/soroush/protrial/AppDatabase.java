package com.soroush.protrial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "digital";
    private static final String SQL_CREATE_IMAGES =
            "CREATE TABLE " + App.IMAGE_TABLE + " (" +
                    App.ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    App.ENTRY_IMAGE + " INTEGER)";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + App.SAMSUNG_TABLE + " (" +
                    App.ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    App.ENTRY_TITLE + " TEXT," +
                    App.ENTRY_DESC + " TEXT," +
                    App.ENTRY_LOCK + " INTEGER)";

    private static final String SQL_CREATE_APPLE =
            "CREATE TABLE " + App.APPLE_TABLE + " (" +
                    App.ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    App.ENTRY_TITLE + " TEXT," +
                    App.ENTRY_DESC + " TEXT," +
                    App.ENTRY_LOCK + " INTEGER)";

    private static final String SQL_CREATE_LG =
            "CREATE TABLE " + App.LG_TABLE + " (" +
                    App.ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    App.ENTRY_TITLE + " TEXT," +
                    App.ENTRY_DESC + " TEXT," +
                    App.ENTRY_LOCK + " INTEGER)";

    private static final String SQL_DELETE_APPLE =
            "DROP TABLE IF EXISTS " + App.APPLE_TABLE;

    private static final String SQL_DELETE_LG =
            "DROP TABLE IF EXISTS " + App.LG_TABLE;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + App.SAMSUNG_TABLE;

    private static final String SQL_DELETE_IMAGE =
            "DROP TABLE IF EXISTS " + App.IMAGE_TABLE;

    public AppDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_APPLE);
        db.execSQL(SQL_CREATE_LG);
        db.execSQL(SQL_CREATE_IMAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_APPLE);
        db.execSQL(SQL_DELETE_LG);
        db.execSQL(SQL_DELETE_IMAGE);
        onCreate(db);
    }

    public long insertItem(String tableName, String title, String desc, int lock){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(App.ENTRY_TITLE, title);
        cv.put(App.ENTRY_DESC, desc);
        cv.put(App.ENTRY_LOCK, lock);
        return db.insert(tableName, null, cv);
    }

    public long insertImage(int resource){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(App.ENTRY_IMAGE, resource);
        return db.insert(App.IMAGE_TABLE, null, cv);
    }

    public void updateBasket(String table, int id, int lock){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(App.ENTRY_LOCK, lock);
        db.update(table, cv, App.ENTRY_ID + "=" + id, null);
        db.close();
    }

    public void updateall(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(App.ENTRY_LOCK, 0);
        db.update(App.SAMSUNG_TABLE, cv,null, null);
        db.close();
    }

    public List<Integer> getAllImages(){
        SQLiteDatabase db = getReadableDatabase();
        List<Integer> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + App.IMAGE_TABLE, null);
        while (cursor.moveToNext()){

            int resourceID;
            resourceID = cursor.getInt(cursor.getColumnIndex(App.ENTRY_ID));
            list.add(resourceID);

        }
        cursor.close();
        db.close();
        return list;
    }

    public List<BrandModel> getAllData(String tableName) {

        SQLiteDatabase db = getReadableDatabase();
        List<BrandModel> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        while (cursor.moveToNext()){

            BrandModel model = new BrandModel();
            model.setId(cursor.getInt(cursor.getColumnIndex(App.ENTRY_ID)));
            model.setLock(cursor.getInt(cursor.getColumnIndex(App.ENTRY_LOCK)));
            model.setTitle(cursor.getString(cursor.getColumnIndex(App.ENTRY_TITLE)));
            model.setDesc(cursor.getString(cursor.getColumnIndex(App.ENTRY_DESC)));
            list.add(model);

        }
        cursor.close();
        db.close();
        return list;

    }

    public List<BrandModel> getAllBasket(String tableName) {

        SQLiteDatabase db = getReadableDatabase();
        List<BrandModel> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName +
                " WHERE " + App.ENTRY_LOCK + "=1", null);
        while (cursor.moveToNext()){

            BrandModel model = new BrandModel();
            model.setId(cursor.getInt(cursor.getColumnIndex(App.ENTRY_ID)));
            model.setLock(cursor.getInt(cursor.getColumnIndex(App.ENTRY_LOCK)));
            model.setTitle(cursor.getString(cursor.getColumnIndex(App.ENTRY_TITLE)));
            model.setDesc(cursor.getString(cursor.getColumnIndex(App.ENTRY_DESC)));
            list.add(model);

        }
        cursor.close();
        db.close();
        return list;

    }

}
