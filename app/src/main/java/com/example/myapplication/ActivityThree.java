package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityThree extends AppCompatActivity {
        private Button btn_6;
        private ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);



        final GlobalClass globalClass = (GlobalClass) getApplicationContext();
        ArrayList<String> a = globalClass.getArrMems();
        btn_6 = (Button) findViewById(R.id.btn_final);
        lst = (ListView) findViewById(R.id.lists);

        a = globalClass.getArrMems();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,a);

        lst.setAdapter(arrayAdapter);




    }
}
