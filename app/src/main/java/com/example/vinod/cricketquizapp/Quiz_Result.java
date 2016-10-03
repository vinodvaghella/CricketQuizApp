package com.example.vinod.cricketquizapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class Quiz_Result extends AppCompatActivity {

    TextView txtResultMsg;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_quiz__result);

            txtResultMsg = (TextView) findViewById(R.id.result_id);
            txtResultMsg.setText(ResultHelper.get_resultMessage());

        }

            @Override
            public void onBackPressed(){
                AlertDialog.Builder builder = new AlertDialog.Builder(Quiz_Result.this);
                builder.setMessage("Do you want to exit?");
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }


        }

