package com.example.quizappassignment3ritika;

import android.content.Context;
import java.util.ArrayList;

public class QuestionBank {
    private ArrayList<Question> questionsList;
    private ArrayList<Integer> questionsColorList;

    public ArrayList<Question> getQuestionsList(Context context) {
        if (questionsList==null){
            questionsList = new ArrayList<>();
            questionsList.add(new Question(context.getString(R.string.question1_text),true));
            questionsList.add(new Question(context.getString(R.string.question2_text),true));
            questionsList.add(new Question(context.getString(R.string.question3_text),false));
            questionsList.add(new Question(context.getString(R.string.question4_text),true));
            questionsList.add(new Question(context.getString(R.string.question5_text),false));
            questionsList.add(new Question(context.getString(R.string.question6_text),false));
            questionsList.add(new Question(context.getString(R.string.question7_text),true));
            questionsList.add(new Question(context.getString(R.string.question8_text),false));
            questionsList.add(new Question(context.getString(R.string.question9_text),false));
            questionsList.add(new Question(context.getString(R.string.question10_text),true));
        }
        return questionsList;
    }

    public ArrayList<Integer> getQuestionsColorList() {
        if (questionsColorList==null){
            questionsColorList = new ArrayList<>();
            questionsColorList.add(R.color.yellow);
            questionsColorList.add(R.color.lime);
            questionsColorList.add(R.color.warmpeach);
            questionsColorList.add(R.color.aqua);
            questionsColorList.add(R.color.teal);
            questionsColorList.add(R.color.fuchsia);
            questionsColorList.add(R.color.silver);
            questionsColorList.add(R.color.green);
            questionsColorList.add(R.color.Salmon);
            questionsColorList.add(R.color.pink);
        }
        return questionsColorList;
    }
}
