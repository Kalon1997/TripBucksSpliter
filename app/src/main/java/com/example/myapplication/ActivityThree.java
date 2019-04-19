package com.example.myapplication;

import android.arch.core.executor.DefaultTaskExecutor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.myapplication.DatabaseHelper.COL_2;

public class ActivityThree extends AppCompatActivity implements BoxDialog.ExampleDialogListener {

    private String sv;
    private Button split;
    private ListView listOfMembers;
    private String imppp;
    private TextView disp_curr_table;
   // private DatabaseHelper dbh;
   // private dataAdapter data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        DatabaseHelper sqlHelper = new DatabaseHelper(this);
        SQLiteDatabase DB = sqlHelper.getWritableDatabase();

        split = (Button)findViewById(R.id.btn_final);
        listOfMembers = (ListView)findViewById(R.id.lists);
        disp_curr_table = (TextView)findViewById(R.id._checking);

        final GlobalClass globalClass = (GlobalClass)getApplicationContext();
        imppp=globalClass.getCurrTable();

        //disp_curr_table.setText(sv);

        ArrayList<String> naams = new ArrayList<>();   //local arraylist for memebers


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

            }
        });

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
}


//android activity to list all the tables
//   https://stackoverflow.com/questions/9345939/simply-populate-database-table-names-in-android-listview
//listview of table names of a database android
