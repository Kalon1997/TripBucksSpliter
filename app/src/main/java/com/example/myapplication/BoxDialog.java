package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class BoxDialog extends AppCompatDialogFragment {
    private EditText spent;
    private ExampleDialogListener  listener;


    //public DatabaseHelper mydb = new DatabaseHelper(this);
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_box,null);

        //final GlobalClass gc = (GlobalClass) getApplicationContext();
        //spent = view.findViewById(R.id.amount);

        builder.setView(view)
                .setTitle("Currently paid")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //sv = spent.getText().toString();

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // view.getContext();
                        //sv = (EditText)findViewById(R.id.amount)).getText().toString();
                        //return sv;
                        String svd = spent.getText().toString();
                        listener.applyTexts(svd);

                    }
                });
        spent = view.findViewById(R.id.amount);

        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener {
        void applyTexts(String sv);
    }

}


















//AppCompatDialogFragent       <------ superclass