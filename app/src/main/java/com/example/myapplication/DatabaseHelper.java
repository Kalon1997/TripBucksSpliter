package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Kalon.db";
    //public static final String TABLE_NAME = "Trip";    //<----
    private String tn;
    public static final String COL_1 = "ID";
    public static final String COL_2 = "MEMBERS";
    public static final String COL_3 = "PAID";
    public static final String COL_4 = "DIFFERENCES";


    //final GlobalClass gc = (GlobalClass) getApplicationContext();

   // public static final String TABLE_NAME = "Trip";

   // public DatabaseHelper(@androidx.annotation.Nullable Context context, @androidx.annotation.Nullable String name, @androidx.annotation.Nullable SQLiteDatabase.CursorFactory factory, int version) {
   public DatabaseHelper(Context context) {
       super(context, DATABASE_NAME,null,1);
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

    public long insertData(String tname, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
       return db.insert(tname, null, contentValues);
        //db.execSQL("INSERT INTO"+tname+"(MEMBERS) VALUES("+name+")");
    }

    public void insertBucks(String tname, String name, int bks, int idd)
    {
       // SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues contentValues = new ContentValues();
        //int x = contentValues.getAsInteger(name);
        //bks+=x;
        //contentValues.put(name,bks);
        //return db.update(tname,contentValues,"COL_2="+name,null);
            //    String tname, String name, int bks



        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put(COL_3,bks);
        //dataToInsert.put("PAID",2);
                                                        // String where = COL_2 + "=" + name;
        //String where = "MEMBERS = A";
        //String tname = "Trip";
      //  try{
           // return db.update(tname, dataToInsert, where, null);
       // int idd = dataToInsert.getAsInteger(name);
        db.update(tname, dataToInsert,  "ID=" + idd, null);
        //DB.update(Tablename, data, "_id=" + id, null);
        //}
        //catch (Exception e){
          //  String error =  e.getMessage().toString();
        //}









       // db.update(tname, contentValues, "COL_2="+name, null);


        //contentValues.put(COL_3,bks);
        //return db.insert(tname,null,contentValues);
        //db.execSQL("INSERT INTO Hey (COL_3) VALUES (22)");
        //         UPDATE table1 SET  value = IF('$_POST[value]'='', value,value + '$value')
        //         UPDATE value =  value + $newvalue


        /*

        ContentValues cv = new ContentValues();
cv.put("Field1","Bob"); //These Fields should be your String values of actual column names
cv.put("Field2","19");
cv.put("Field2","Male");
Then use the update method, it should work now:
myDB.update(TableName, cv, "_id="+id, null);

         */






         // db.execSQL("UPDATE"+tname+"SET  value = value +"+bks+"WHERE MEMBERS ="+name);
        //db.execSQL("UPDATE"+tname+"SET  value = IF('$_POST[value]'='', value,value + '$value')");


      //  String strSQL = "UPDATE"+tname+" SET COL_3 ="+bks+" WHERE COL_2 = "+name;

       // db.execSQL(strSQL);


    }




    public void createUniqueTable(String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table IF NOT EXISTS " + table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, MEMBERS TEXT,PAID INTEGER DEFAULT 0,DIFFERENCES INTEGER)");

    }

    public void deleteAllTables(String str_t)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ str_t );
        onCreate(db);
    }


    public Cursor getAllData(String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


  /*
    public Cursor getID(String table_name, String memToGetID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //String s = "SELECT ID FROM"+table_name+"WHERE MEMBERS ="+memToGetID;
        String s = "SELECT MEMBERS FROM"+table_name;
        //int res;



        //int res = DatabaseUtils.stringForQuery(db,s,null);

        Cursor res = db.rawQuery(s,null);
        //db.execSQL(s);
        //onCreate(db);
        return res;




       // String result = DatebaseUtils.stringForQuery(db,
         //       "SELECT NAME FROM "+TABLE_NAME+" ORDER BY ID DESC LIMIT 1", null);

    }

*/




    public String getid(String tname, String  heading) throws SQLException
    {
        //System.out.println("ddbpos="+heading);
        SQLiteDatabase db = this.getWritableDatabase();
        long recc=0;
        String rec=null;
        Cursor mCursor = db.rawQuery(
                "SELECT ID  FROM '"+tname+"' WHERE MEMBERS= '"+heading+"'" , null);
        if (mCursor != null)
        {
            mCursor.moveToFirst();
            recc=mCursor.getLong(0);
            rec=String.valueOf(recc);
        }
        return rec;
    }



    public int getOldBks(String tname, String  heading) throws SQLException
    {
        //System.out.println("ddbpos="+heading);
      /*  SQLiteDatabase db = this.getWritableDatabase();
        int reccc=0;
        String recc=null;
        Cursor mCursor = db.rawQuery(
                "SELECT PAID  FROM '"+tname+"' WHERE MEMBERS= '"+heading+"'" , null);
        if (mCursor != null)
        {
            mCursor.moveToFirst();
            reccc=mCursor.getInt(2);
            recc=String.valueOf(reccc);
        }
        return recc;

     */

      SQLiteDatabase db = this.getWritableDatabase();
      int rec=0;
      Cursor cursor = db.rawQuery("SELECT PAID  FROM '"+tname+"' WHERE MEMBERS= '"+heading+"'" , null);
      if(cursor != null)
      {
          cursor.moveToFirst();
          rec =  cursor.getInt(cursor.getColumnIndex("PAID"));

      }
        return rec;
    }


    public double getAvg(String tname) throws SQLException
    {
        //  select sum(col), avg(col) from table;
        SQLiteDatabase db = this.getWritableDatabase();
        int summ=0;
        int cnt;
        double avg = 0.0;
        //String findCount = "SELECT * FROM"+tname;
        //Cursor cursor = db.rawQuery(findCount,null);
        //cnt = cursor.getCount();



//        SELECT AVG( advance_amount )
//            FROM orders;


        return avg;
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