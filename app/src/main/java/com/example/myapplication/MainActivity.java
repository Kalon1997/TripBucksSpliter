package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String tname;
        button = (Button) findViewById(R.id.tripCreate);
        final EditText edit = (EditText)findViewById(R.id.tripName);
        final TextView text =  (TextView)findViewById(R.id.createdisp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("TRIP CREATED: "+edit.getText());
                edit.setText(" ");
                openActivity2();
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
}




        //setContentView(t);