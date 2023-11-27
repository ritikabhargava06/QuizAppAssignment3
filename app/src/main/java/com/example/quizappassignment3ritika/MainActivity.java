package com.example.quizappassignment3ritika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    QuestionBank qb = new QuestionBank();
    ArrayList<Question> currentQuesList = new ArrayList<>();
    ArrayList<Integer> currentColorsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Collections.shuffle(qb.getQuestionsList());
        Collections.shuffle(qb.getQuestionsColorList());
        ((MyApp)getApplication()).setAppLevelQuestionsList(qb.getQuestionsList());
        ((MyApp)getApplication()).setAppLevelColorsList(qb.getQuestionsColorList());
        currentQuesList = ((MyApp)getApplication()).getAppLevelQuestionsList();
        currentColorsList = ((MyApp)getApplication()).getAppLevelColorsList();

        QuestionFragment qf =QuestionFragment.newInstance(currentQuesList.get(0).getQuestionText(),currentColorsList.get(0));
        getSupportFragmentManager().beginTransaction().add(R.id.question_frame_layout, qf).commit();

    }
}