package com.example.myapplication;

import android.app.Application;

import java.util.ArrayList;

public class GlobalClass extends Application {
    private String dbname;
    private ArrayList<String> arrMems = new ArrayList<String>();

    public ArrayList<String> getArrMems() {
        return arrMems;
    }
/*
    public void setArrMems(ArrayList<String> arrMems) {
        this.arrMems = arrMems;
    }
*/
    public  void addItem(String item) {
        arrMems.add(item);
    }



    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }
}
