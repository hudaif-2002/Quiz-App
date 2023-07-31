package com.example.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class login extends AppCompatActivity implements View.OnClickListener {


    AlertDialog.Builder builder;

    // ProgressBar timerProgress;
    TextView timerText,ca,score1;
    //  Timer t;
    // int second=0;

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn,nxtbtn;

    int score=0,i=1;
    int totalQuestion=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder=new AlertDialog.Builder(this);

        totalQuestionsTextView=findViewById(R.id.total_question);
        questionTextView=findViewById(R.id.question);
        ansA=findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        submitBtn=findViewById(R.id.submit_btn);
        nxtbtn=findViewById(R.id.nxtbtn);
        ca=(TextView) findViewById(R.id.ca);
        score1=(TextView) findViewById(R.id.score);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);




        loadNewQuestion();

    }

//    public void startTimer()
//    {
//        t.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(second==15) {
//                            timerText.setText("timeup,attempt next question");
//
//                            second=0;
//                            currentQuestionIndex++;
//                            loadNewQuestion();
////                            t.cancel();
////
////                             return;
//
//
//
//                        }
//                        timerText.setText(("Seconds "+(15.0-second)));
//                        int  prog=(int)((second/15.0)*100.0);
//                        timerProgress.setProgress(prog);
//                        second++;
//
//                    }
//                });
//
//            }
//        },0,1000);
//    }





    @Override
    public void onClick(View view) {
        //second=0;
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);



        Button clickedButton=(Button)view;
        if (clickedButton.getId() == R.id.submit_btn)
        {
            StringBuilder cad= new StringBuilder();
            cad.append(QuestionAnswer.correctAnswers[currentQuestionIndex]);



            // Toast.makeText(getApplicationContext(), ca.toString(), Toast.LENGTH_SHORT).show();
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex]))
            {
                score++;
                ca.setVisibility(View.VISIBLE);

                ca.setBackgroundColor(Color.WHITE);
                ca.setTextColor(Color.GREEN);
                ca.setText("Excellent ! You got it right !");



            }
            else {
                ca.setVisibility(View.VISIBLE);
                ca.setTextColor(Color.RED);
                ca.setText("Correct Answer is:"+cad.toString());

            }


            currentQuestionIndex++;

            // loadNewQuestion();


        }
        else
        {
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }


    }



    void loadNewQuestion()
    {
        // startTimer();


        totalQuestionsTextView.setText("Total Question : "+ i+"/"+totalQuestion);
        i++;

        StringBuilder s= new StringBuilder();
        score1.setText("Score : " +Integer.toString(score));

        if(currentQuestionIndex==totalQuestion)
        {
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ca.setVisibility(View.INVISIBLE);
                loadNewQuestion();
            }
        });

    }

    void finishQuiz()
    {
        String passStatus="";
        if(score >totalQuestion*0.60)
        {
            passStatus="Passed";
        }
        else
        {
            passStatus="Failed";
        }


//        new AlertDialog.Builder(this)
//                .setTitle(passStatus)
//                .setMessage("Score is "+score +"out of "+ totalQuestion )
//
//                .setPositiveButton("Restart",(DialogInterface,i) ->restartQuiz())
//
//                .setCancelable(false)
//                .setNegativeButton("EXit", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(login.this, "thank you,", Toast.LENGTH_SHORT).show();
//                    }
//
//                });
//         AlertDialog.show();


        builder.setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                restartQuiz();

            }

        });

        builder.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Toast.makeText(getApplicationContext(), "THANK YOU, PLAY AGAIN!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

        AlertDialog alert=builder.create();
        alert.setTitle(passStatus);
        alert.setMessage("Score is "+score +" out of "+ totalQuestion );
        alert.show();





    }

    void restartQuiz()
    {
        i=1;
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }

}