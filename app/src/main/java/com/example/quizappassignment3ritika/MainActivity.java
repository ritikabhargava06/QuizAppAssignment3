package com.example.quizappassignment3ritika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AlertFragment.alertButtonClickListener {

    QuestionBank qb = new QuestionBank();
    ArrayList<Question> currentQuesList = new ArrayList<>();
    ArrayList<Integer> currentColorsList = new ArrayList<>();

    Button bTrue;
    Button bFalse;
    ProgressBar quizProgressBar;

    int currIndex;
    String currQues;
    boolean currQuesAns;
    int currColor;
    int correctAns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bTrue = findViewById(R.id.true_button);
        bFalse = findViewById(R.id.false_button);
        quizProgressBar = findViewById(R.id.determinate_progress_bar);

        bTrue.setOnClickListener(this);
        bFalse.setOnClickListener(this);
        currIndex = ((MyApp)getApplication()).getCurrentIndex();
        correctAns = ((MyApp)getApplication()).getCorrectAnswers();

        if(((MyApp)getApplication()).isNewAttempt()){
            shuffleListsIfRequired();
        }

        currentQuesList = ((MyApp)getApplication()).getAppLevelQuestionsList();
        currentColorsList = ((MyApp)getApplication()).getAppLevelColorsList();
        getNextQuesDetails();
        addNewQuestionFragment();
    }

    private void shuffleListsIfRequired() {
            if(currIndex==0){
                Collections.shuffle(qb.getQuestionsList());
                Collections.shuffle(qb.getQuestionsColorList());
                ((MyApp)getApplication()).setNewAttempt(false);
                ((MyApp)getApplication()).setAppLevelQuestionsList(qb.getQuestionsList());
                ((MyApp)getApplication()).setAppLevelColorsList(qb.getQuestionsColorList());
            }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.true_button:
                if(currQuesAns){
                    Toast.makeText(MainActivity.this, R.string.correct_answer_msg, Toast.LENGTH_SHORT).show();
                    ((MyApp)getApplication()).setCorrectAnswers(correctAns+1);
                    correctAns = ((MyApp)getApplication()).getCorrectAnswers();
                }else{
                    Toast.makeText(MainActivity.this, R.string.Incorrect_answer_msg, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.false_button:
                if(!currQuesAns){
                    Toast.makeText(MainActivity.this, R.string.correct_answer_msg, Toast.LENGTH_SHORT).show();
                    ((MyApp)getApplication()).setCorrectAnswers(correctAns+1);
                    correctAns = ((MyApp)getApplication()).getCorrectAnswers();
                }else{
                    Toast.makeText(MainActivity.this, R.string.Incorrect_answer_msg, Toast.LENGTH_SHORT).show();
                }
                break;
        }

        quizProgressBar.incrementProgressBy(10);
        int newIndex = currIndex+1;
        if(newIndex==currentQuesList.size()) {
            //create an alert to show the result
            AlertFragment alert = AlertFragment.newInstance(currentQuesList.size(), correctAns);
            alert.listener = MainActivity.this;
            alert.show(getSupportFragmentManager(), null);
            newIndex = 0;
            quizProgressBar.setProgress(0);
            ((MyApp) getApplication()).setNewAttempt(true);

        }
        ((MyApp)getApplication()).setCurrentIndex(newIndex);
        currIndex = ((MyApp)getApplication()).getCurrentIndex();
        if(((MyApp)getApplication()).isNewAttempt()){
            shuffleListsIfRequired();
            currentQuesList = ((MyApp)getApplication()).getAppLevelQuestionsList();
            currentColorsList = ((MyApp)getApplication()).getAppLevelColorsList();
        }
        getNextQuesDetails();
        addNewQuestionFragment();
    }

    private void getNextQuesDetails() {
        currQues = currentQuesList.get(currIndex).getQuestionText();
        currQuesAns = currentQuesList.get(currIndex).getAnswer();
        ((MyApp)getApplication()).setCurrentColor(currentColorsList.get(currIndex));
        currColor = ((MyApp)getApplication()).getCurrentColor();
    }

    private void addNewQuestionFragment() {
        QuestionFragment newQuesFragment =QuestionFragment.newInstance(currQues,currColor);
        QuestionFragment oldQuesFragment = (QuestionFragment) getSupportFragmentManager().findFragmentById(R.id.question_frame_layout);
        if(oldQuesFragment!=null){
            getSupportFragmentManager().beginTransaction().remove(oldQuesFragment).commit();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.question_frame_layout, newQuesFragment).commit();
    }

    @Override
    public void saveData() {
        Toast.makeText(MainActivity.this,"Ready to save the data",Toast.LENGTH_LONG).show();
        //save data to file storage
        //set correct answers to 0 to reset for new attempt
        ((MyApp) getApplication()).setCorrectAnswers(0);
        correctAns = ((MyApp) getApplication()).getCorrectAnswers();
    }

    @Override
    public void resetForNewAttempt() {
        ((MyApp) getApplication()).setCorrectAnswers(0);
        correctAns = ((MyApp) getApplication()).getCorrectAnswers();
    }
}