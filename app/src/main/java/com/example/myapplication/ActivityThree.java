package com.example.myapplication;

import android.arch.core.executor.DefaultTaskExecutor;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.myapplication.DatabaseHelper.COL_2;

public class ActivityThree extends AppCompatActivity implements BoxDialog.ExampleDialogListener {

    private int taka;
    private String member_name;
    private Button split;
    private ListView listOfMembers;
    private String imppp;
    private TextView disp_curr_table;
    private Button _addThis;
    private int cnt=0;
    private String key;
    private int updatedTaka;
   // private DatabaseHelper dbh;
   // private dataAdapter data;
    private int oldTaka;
    private String oldTaka_s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        final DatabaseHelper sqlHelper = new DatabaseHelper(this);
        SQLiteDatabase DB = sqlHelper.getWritableDatabase();

        split = (Button)findViewById(R.id.btn_final);
        listOfMembers = (ListView)findViewById(R.id.lists);
        disp_curr_table = (TextView)findViewById(R.id._checking);
        _addThis = (Button)findViewById(R.id.addThis);

        final GlobalClass globalClass = (GlobalClass)getApplicationContext();
        imppp=globalClass.getCurrTable();

        //disp_curr_table.setText(sv);

        final ArrayList<String> naams = new ArrayList<>();   //local arraylist for memebers





        Cursor cursor = DB.rawQuery("SELECT MEMBERS FROM "+imppp+" ", null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                naams.add(cursor.getString(cursor.getColumnIndex(COL_2)));

                cursor.moveToNext();
            }
        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,naams);

        listOfMembers.setAdapter(arrayAdapter);

        listOfMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDialog();

                //taka = Integer.parseInt(disp_curr_table.getText().toString());
                member_name = naams.get(position);
                //sqlHelper.insertBucks(imppp,member_name,taka);    ---->  BETTER CREATE A SEPARATE BUTTON TO ADD


            }
        });


        _addThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taka = Integer.parseInt(disp_curr_table.getText().toString());
                //member_name = naams.get(position);
               // member_name="A";

               // sqlHelper.insertBucks(imppp,"A",taka);


                //   H  E  R  E  !!!!!!!!!!!!!!  MEMBER_NAME

                //String number = "10";
                //int result = Integer.parseInt(number);
                //System.out.println(result);

               // Cursor cur = sqlHelper.getID(imppp,member_name);


/*
                if(cur.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }

                String kalon=cur.getString((cur.getColumnIndex("MEMBERS")));

                while(kalon.equals(member_name))
                {
                    cnt++;
                    cur.moveToNext();
                }

*/

               // updatedTaka = 0;
                //oldTaka=0;

                key = sqlHelper.getid(imppp,member_name);
                cnt = Integer.parseInt(key);




                //oldTaka_s = sqlHelper.getOldBks(imppp,member_name);
                //oldTaka = Integer.parseInt(oldTaka_s);
               //     oldTaka=100;
                oldTaka = sqlHelper.getOldBks(imppp,member_name);
                updatedTaka = oldTaka + taka;




/*

                cur.moveToFirst();
                while (cur.isAfterLast() == false) {
                    if (cur.getString(cur.columnIndex("MEMBERS") == member_name) )
                    {
                         key = cur.getInt(cur.columnIndex("ID"));
                    }
                    cur.moveToNext();
                }

*/







                // if Cursor is contains results
               // if (cur != null) {
                    // move cursor to first row
                  //  if (cur.moveToFirst()) {
                      //  do {
                            // Get version from Cursor
                         //   String bookName = cur.getString(cur.getColumnIndex("MEMBERS"));

                         //  if(bookName.equals(member_name))
//                               cnt++;


                            // move to next row
                       // } while (cur.moveToNext());
                  //  }
              //  }













               // String iddd = res2.getString(1);
                //int idddd_int = Integer.parseInt(iddd);

                try
                {
                    sqlHelper.insertBucks(imppp,member_name,updatedTaka,cnt);

                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "COULDN'T UPDATE", Toast.LENGTH_LONG).show();

                }





            }
        });



        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goToTableDisplay();

                Cursor res = sqlHelper.getAllData(imppp);
                if(res.getCount() == 0) {
                    // show message
                   showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :"+ res.getString(0)+"\n");
                    buffer.append("MEMBERS :"+ res.getString(1)+"\n");
                    buffer.append("PAID :"+ res.getString(2)+"\n");
                    buffer.append("DIFFERENCES :"+ res.getString(3)+"\n\n");
                }

                // Show all data
                showMessage("Data",buffer.toString());
            }
        });



/*
        public void viewAll() {
            split.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Cursor res = myDb.getAllData();
                            if(res.getCount() == 0) {
                                // show message
                                showMessage("Error","Nothing found");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("Id :"+ res.getString(0)+"\n");
                                buffer.append("Name :"+ res.getString(1)+"\n");
                                buffer.append("Surname :"+ res.getString(2)+"\n");
                                buffer.append("Marks :"+ res.getString(3)+"\n\n");
                            }

                            // Show all data
                            showMessage("Data",buffer.toString());
                        }
                    }
            );
        }
*/

    }


    public void openDialog() {
        BoxDialog exampleDialog = new BoxDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String svd) {
        //textViewUsername.setText(username);
        //textViewPassword.setText(password);
        //sv=svd;


        disp_curr_table.setText(svd);


    }




    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}


//android activity to list all the tables
//   https://stackoverflow.com/questions/9345939/simply-populate-database-table-names-in-android-listview
//listview of table names of a database android
