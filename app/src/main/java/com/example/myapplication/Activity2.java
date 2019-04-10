package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {
    private Button add;
    private EditText names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        add = (Button) findViewById(R.id.Button2);
        names = (EditText) findViewById(R.id.editText);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispIt();
            }
        });

    }

    public void dispIt()
    {
        names.setVisibility(View.VISIBLE);
    }
}
