package com.example.vinod.cricketquizapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;
import java.util.Objects;

import org.w3c.dom.Text;

import static android.R.attr.button;
import static android.R.attr.id;
import static android.R.attr.name;


/**
 * This app displays an order form to Cricket Quiz App.
 */

public class OneQuestion extends AppCompatActivity implements View.OnClickListener{

    TextView txtView,y;
    Button btnOpt;


    int scores;
    String[] questions, answers;

    int index;
    boolean isEnded;

    /**
     * This method is called for load questions.
     */

    public int getResourceId(int i) {
        int rtnValue = 0;
        switch(i) {
            case 1:
                rtnValue = R.array.Questions;
                break;
            default:
                break;
        }

        return rtnValue;
    }

    public void LoadQuestions(){
        int resourceId = getResourceId(1);
        questions = getResources().getStringArray(resourceId);
    }

    /**
     * This method is called for show answer options with selected questions.
     */


    public void showQuestion(int indexPosition) {
        String selectedQuestion = questions[indexPosition].split("~")[0];
        txtView = (TextView)findViewById(R.id.quest_One);
        txtView.setText(selectedQuestion);


        String[] selectedQuestionOptions = questions[indexPosition].split("~")[1].trim().split("-");


        for (int i=0;i < selectedQuestionOptions.length;i++) {
            switch (i) {
                case 0:
                    btnOpt = (Button)findViewById(R.id.AnsOne_opt1);
                    break;
                case 1:
                    btnOpt = (Button)findViewById(R.id.AnsOne_opt2);
                    break;
                case 2:
                    btnOpt = (Button)findViewById(R.id.AnsOne_opt3);
                    break;
                case 3:
                    btnOpt = (Button)findViewById(R.id.AnsOne_opt4);
                    break;
                default:
                    break;
            }
            btnOpt.setOnClickListener(this);
            btnOpt.setText(selectedQuestionOptions[i]);
        }
    }


    /**
     * This method is called for load answers.
     */

    public void LoadAnswers() {
        int answersId = R.array.QuestionOptions;

        answers = getResources().getStringArray(answersId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_question);
        index = 0;
        isEnded = false;
        LoadQuestions();
        LoadAnswers();

        showQuestion(index);
    }

    /**
     * This method is called when the back Press is clicked for exit.
     */

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(OneQuestion.this);
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



    /**
     * This method is called for clicked event.
     */

    public void onClick(View view){




        int id = view.getId();
        String answerText;
        String answerToMatch = "";

        /**
         * This method is called when the Option button is clicked.
         */

        if (id != View.NO_ID) {

            switch (id)
            {
                case R.id.AnsOne_opt1:
                    btnOpt = (Button)findViewById(R.id.AnsOne_opt1);
                    break;
                case R.id.AnsOne_opt2:
                    btnOpt = (Button)findViewById(R.id.AnsOne_opt2);
                    break;
                case R.id.AnsOne_opt3:
                    btnOpt = (Button)findViewById(R.id.AnsOne_opt3);
                    break;
                case R.id.AnsOne_opt4:
                    btnOpt = (Button)findViewById(R.id.AnsOne_opt4);
                    break;
                default:
                    break;
            }
            answerToMatch = answers[index];
            answerText = btnOpt.getText().toString();


            /**
             * This method is called for count scores.
             */
            boolean answerMatched = answerText.trim().equals(answerToMatch.trim());

            if (answerMatched) {
                scores++ ;
            }
        }

        if (isEnded) {

            if (scores >= 6){
                ResultHelper.set_resultMessage(String.format("You Won  ( %d out of %d )", scores, answers.length));
              }else {
                ResultHelper.set_resultMessage(String.format("You Lost  ( %d out of %d )", scores, answers.length));

            }

            /**
             * This method is called for show result in Quiz Result.
             */
           Intent i = new Intent(this, Quiz_Result.class);
           {
                startActivity(i);

            }
            return;
        }

        index++;

        if (index < questions.length) {
            showQuestion(index);
        }

        if (index == questions.length - 1) {
            isEnded = true;
        }
    }
}
