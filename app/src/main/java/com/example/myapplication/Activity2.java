package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static java.util.logging.Logger.global;

public class Activity2 extends AppCompatActivity {
    DatabaseHelper mydb;
    private Button add;
    private EditText names;
    private Button next;
    private String memes;
    private Button save_next;
    private String impo;
   // private ArrayList<String> globalnames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);



        mydb = new DatabaseHelper(this);
        final GlobalClass gc = (GlobalClass) getApplicationContext();


        impo=gc.getCurrTable();









        //globalnames= new ArrayList<String>();
       // globalnames = gc.getArrMems();
        save_next = (Button) findViewById(R.id.Button4);
        add = (Button) findViewById(R.id.Button2);
        names = (EditText) findViewById(R.id.editText);
        next = (Button) findViewById(R.id.Button3);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    memes = names.getText().toString();
                    mydb.insertData(impo,memes);
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "cannot Add", Toast.LENGTH_LONG).show();

                }
               // memes = names.getText().toString();  //  not in global arraylist
                //gc.addItem(memes);      //in db only //a3 we'll retrieve it from there

                //mydb.insertData(impo,memes);
                hideItAgain();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispIt();
            }
        });




        save_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity3();

            }
        });

    }

    public void dispIt()
    {
        names.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
    }

    public void hideItAgain()
    {
        names.setText(" ");
        names.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);
        //Toast.makeText(getApplicationContext(),"A D D E D", Toast.LENGTH_SHORT).show();
    }
/*
    public void savearlistfunc()
    {
        GlobalClass gc = null;
        gc.setArrMems(globalnames);
    }
    */


    public void gotoActivity3()
    {
        Intent intent = new Intent(this,ActivityThree.class);
        startActivity(intent);
    }

}





//   https://www.android-examples.com/insert-data-in-sqlite-database-in-android-using-edittext/



/*
 next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memes = names.getText().toString();  //  not in global arraylist
                //gc.addItem(memes);      //in db only //a3 we'll retrieve it from there
                //
                mydb.insertData(impo,memes);
                hideItAgain();
                //globalnames.add(memes);

                //savearlistfunc();
               // globalnames.add(meems);
                //Toast.makeText(getApplicationContext(),"Hello Javatpoint", Toast.LENGTH_SHORT).show();
                //for(int i=0;i<globalnames.size();i++){
                  //  Log.d("item", globalnames.get(i).toString());
                //}
            }
        });
 */



//      https://stackoverflow.com/questions/8503541/sqlite-insert-on-button-click