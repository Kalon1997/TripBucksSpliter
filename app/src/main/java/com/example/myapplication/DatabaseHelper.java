package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Kalon.db";
    //public static final String TABLE_NAME = "Trip";    //<----
    private String tn;
    public static final String COL_1 = "ID";
    public static final String COL_2 = "MEMBERS";
    public static final String COL_3 = "PAID";
    public static final String COL_4 = "DIFFERENCES";


    //final GlobalClass gc = (GlobalClass) getApplicationContext();

    public static final String TABLE_NAME = "Trip";

   // public DatabaseHelper(@androidx.annotation.Nullable Context context, @androidx.annotation.Nullable String name, @androidx.annotation.Nullable SQLiteDatabase.CursorFactory factory, int version) {
   public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, MEMBERS TEXT,PAID INTEGER,DIFFERENCES INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //onCreate(db);
    }

    public boolean insertData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        final long insert = db.insert(TABLE_NAME, null, contentValues);
        if(insert == -1)
            return false;
        else
            return true;
    }


    public void createUniqueTable(String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table IF NOT EXISTS " + table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, MEMBERS TEXT,PAID INTEGER,DIFFERENCES INTEGER)");

    }

    public void deleteAllTables(String str_t)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ str_t );
        onCreate(db);

    }

}


//    https://stackoverflow.com/questions/6406903/how-to-add-data-in-the-database-on-button-click

/*

List<String> list = new ArrayList<String>();
		list.add("Text 1");
		list.add("Text 2");
		list.add("Text 3");

		System.out.println("#1 normal for loop");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

 */