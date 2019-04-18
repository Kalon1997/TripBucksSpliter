package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private Button del;
    private Button button;
    private String table_name;
    private ArrayList<String> allTables;       // <<<<-------------
    DatabaseHelper mydb;
    private ListView tlist;
    private String impp;
   // private String t = "NITTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button del = (Button) findViewById(R.id.btn0);

        tlist = (ListView) findViewById(R.id._tlist);


        mydb = new DatabaseHelper(this);

        final GlobalClass gc = (GlobalClass) getApplicationContext();

        table_name = gc.getDbname();
       // allTables=gc.getAll_the_table_names();
        //String tname;
        button = (Button) findViewById(R.id.tripCreate);
        final EditText edit = (EditText)findViewById(R.id.tripName);
        final TextView text =  (TextView)findViewById(R.id.createdisp);
















        //final ArrayList<String> dirArray = new ArrayList<String>();

        DatabaseHelper sqlHelper = new DatabaseHelper(this);
        SQLiteDatabase DB = sqlHelper.getWritableDatabase();
        Cursor c = DB.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        while(c.moveToNext()){
            String s = c.getString(0);
            if(s.equals("android_metadata"))
            {
                //System.out.println("Get Metadata");
                //allTables.add(" ");
                continue;
            }
            else
            {
                //allTables.add(s);
                gc.addTables(s);
            }
        }


        allTables=gc.getAll_the_table_names();















        allTables.remove("sqlite_sequence");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,allTables);

        tlist.setAdapter(arrayAdapter);

        tlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //enterPaid();   //activity3 kind of
                //Toast.makeText(MainActivity.this, allTables.get(position), Toast.LENGTH_SHORT).show();
                gc.setCurrTable(allTables.get(position));
                goToAct3();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                impp=edit.getText().toString();
                gc.setCurrTable(impp);

                text.setText("TRIP CREATED: "+edit.getText());
                table_name=edit.getText().toString();
                edit.setText(" ");
                allTables.add(table_name);
                mydb.createUniqueTable(table_name);

                //gc.setDbname(database_name);
                openActivity2();
            }
        });



        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = allTables.size() - 1;
                while(i>0)
                {
                    String s = allTables.get(i);
                    text.setText(s);
                    mydb.deleteAllTables(s);
                    i=i-1;
                }
                //for (String temp : allTables) {
                   // mydb.deleteAllTables(t);
                //}

            }
        });

        /*
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                //tname = edit.getText().toString();
                text.setText("TRIP CREATED: "+edit.getText());
                edit.setText(" ");
                //startActivity(new Intent(MainActivity.this, Main2Activity.class));
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

            }
        });

        */
    }

    public void openActivity2() {
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }


    public void goToAct3()
    {
        Intent intent = new Intent(this,ActivityThree.class);
        startActivity(intent);
    }

}




        //setContentView(t);
// DAMN GOOD           https://stackoverflow.com/questions/7430950/sqlite-how-to-get-all-table-names-in-database