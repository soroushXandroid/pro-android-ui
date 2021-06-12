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

    private static final String SQL_CREATE_PRODUCT =
            "CREATE TABLE IF NOT EXISTS " + App.PRODUCT_TABLE + " (" +
                    App.ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    App.ENTRY_IMAGE + " BLOB, " +
                    App.ENTRY_NAME + " TEXT, " +
                    App.ENTRY_TITLE + " TEXT, " +
                    App.ENTRY_DESC + " TEXT, " +
                    App.ENTRY_PRICE + " INTEGER, " +
                    App.ENTRY_LOCK + " INTEGER, " +
                    App.ENTRY_AMOUNT + " INTEGER);";

    private static final String SQL_DELETE_PRODUCT =
            "DROP TABLE IF EXISTS " + App.PRODUCT_TABLE;

    public AppDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRODUCT);
        onCreate(db);
    }

    public void insertItem(byte[] imgRes, String name, String title,
                           String desc, int price, int lock, int amount){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(App.ENTRY_IMAGE, imgRes);
        cv.put(App.ENTRY_NAME, name);
        cv.put(App.ENTRY_TITLE, title);
        cv.put(App.ENTRY_DESC, desc);
        cv.put(App.ENTRY_PRICE, price);
        cv.put(App.ENTRY_LOCK, lock);
        cv.put(App.ENTRY_AMOUNT, amount);
        db.insert(App.PRODUCT_TABLE, null, cv);

    }

    public void updateAmount(int id, boolean isAdd){

        int amount = 0;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + App.ENTRY_AMOUNT + " FROM " + App.PRODUCT_TABLE
                + " WHERE " + App.ENTRY_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToFirst())
            amount = cursor.getInt(0);
        cursor.close();

        ContentValues cv = new ContentValues();
        if (isAdd)
            ++amount;
        else
            amount = 0;
        cv.put(App.ENTRY_AMOUNT, amount);
        db.update(App.PRODUCT_TABLE, cv, App.ENTRY_ID + "=" + id, null);
        db.close();

    }

    public void setLock(int id, int lock){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(App.ENTRY_LOCK, lock);
        db.update(App.PRODUCT_TABLE, cv, App.ENTRY_ID + "=" + id, null);
        db.close();

    }

    public List<BrandModel> getAllData() {

        SQLiteDatabase db = getReadableDatabase();
        List<BrandModel> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + App.PRODUCT_TABLE, null);
        while (cursor.moveToNext()){

            BrandModel model = new BrandModel();
            model.setId(cursor.getInt(cursor.getColumnIndex(App.ENTRY_ID)));
            model.setLock(cursor.getInt(cursor.getColumnIndex(App.ENTRY_LOCK)));
            model.setTitle(cursor.getString(cursor.getColumnIndex(App.ENTRY_TITLE)));
            model.setName(cursor.getString(cursor.getColumnIndex(App.ENTRY_NAME)));
            model.setDesc(cursor.getString(cursor.getColumnIndex(App.ENTRY_DESC)));
            model.setImg(cursor.getBlob(cursor.getColumnIndex(App.ENTRY_IMAGE)));
            model.setPrice(cursor.getInt(cursor.getColumnIndex(App.ENTRY_PRICE)));
            model.setAmount(cursor.getInt(cursor.getColumnIndex(App.ENTRY_AMOUNT)));
            list.add(model);

        }
        cursor.close();
        db.close();
        return list;

    }

    public BrandModel getThisItem(int id){

        SQLiteDatabase db = getReadableDatabase();
        BrandModel model = new BrandModel();

        Cursor cursor = db.rawQuery("SELECT * FROM " +
                App.PRODUCT_TABLE + " WHERE " + App.ENTRY_ID + " = ?", new String[]{id + ""});
        if (cursor.moveToFirst()){
            model.setId(cursor.getInt(cursor.getColumnIndex(App.ENTRY_ID)));
            model.setLock(cursor.getInt(cursor.getColumnIndex(App.ENTRY_LOCK)));
            model.setTitle(cursor.getString(cursor.getColumnIndex(App.ENTRY_TITLE)));
            model.setName(cursor.getString(cursor.getColumnIndex(App.ENTRY_NAME)));
            model.setDesc(cursor.getString(cursor.getColumnIndex(App.ENTRY_DESC)));
            model.setImg(cursor.getBlob(cursor.getColumnIndex(App.ENTRY_IMAGE)));
            model.setPrice(cursor.getInt(cursor.getColumnIndex(App.ENTRY_PRICE)));
            model.setAmount(cursor.getInt(cursor.getColumnIndex(App.ENTRY_AMOUNT)));
        }

        cursor.close();
        db.close();
        return model;

    }

    public List<BrandModel> getAllBasket() {

        SQLiteDatabase db = getReadableDatabase();
        List<BrandModel> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + App.PRODUCT_TABLE +
                " WHERE " + App.ENTRY_AMOUNT + " > 0", null);
        while (cursor.moveToNext()){

            BrandModel model = new BrandModel();
            model.setId(cursor.getInt(cursor.getColumnIndex(App.ENTRY_ID)));
            model.setLock(cursor.getInt(cursor.getColumnIndex(App.ENTRY_LOCK)));
            model.setTitle(cursor.getString(cursor.getColumnIndex(App.ENTRY_TITLE)));
            model.setName(cursor.getString(cursor.getColumnIndex(App.ENTRY_NAME)));
            model.setDesc(cursor.getString(cursor.getColumnIndex(App.ENTRY_DESC)));
            model.setImg(cursor.getBlob(cursor.getColumnIndex(App.ENTRY_IMAGE)));
            model.setPrice(cursor.getInt(cursor.getColumnIndex(App.ENTRY_PRICE)));
            model.setAmount(cursor.getInt(cursor.getColumnIndex(App.ENTRY_AMOUNT)));
            list.add(model);

        }
        cursor.close();
        db.close();
        return list;

    }

}
