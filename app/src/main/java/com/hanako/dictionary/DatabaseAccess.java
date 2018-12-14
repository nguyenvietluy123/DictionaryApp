package com.hanako.dictionary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    public DatabaseAccess (Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }
    public static DatabaseAccess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }
    public void open(){
        this.database = openHelper.getWritableDatabase();
    }
    public void close(){
        if (database != null){
            this.database.close();
        }
    }
    public ArrayList<String> getWords(){
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = database.rawQuery("select * from anh_viet limit 100", null);
        while (cursor.moveToNext()){
            list.add(cursor.getString(1));
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getWords(String filter){
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = database.rawQuery("select * from anh_viet where word like "+filter+"%", null);
        while (cursor.moveToNext()){
            list.add(cursor.getString(1));
        }
        cursor.close();
        return list;
    }

    public String getDefinition (String word){
        String definition = "";
        Cursor cursor = database.rawQuery("Select * from anh_viet where word='"+word+"'",null);
        if (cursor.moveToFirst()){
            definition=cursor.getString(2);
        }
        cursor.close();
        return definition;
    }
}
