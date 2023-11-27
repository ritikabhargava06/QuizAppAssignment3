package com.example.quizappassignment3ritika;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

    private ArrayList<Question> appLevelQuestionsList = new ArrayList<>();
    private ArrayList<Integer> appLevelColorsList =  new ArrayList<>();

    public void setAppLevelQuestionsList(ArrayList<Question> appLevelQuestionsList) {
        this.appLevelQuestionsList = appLevelQuestionsList;
    }

    public void setAppLevelColorsList(ArrayList<Integer> appLevelColorsList) {
        this.appLevelColorsList = appLevelColorsList;
    }

    public ArrayList<Question> getAppLevelQuestionsList() {
        return appLevelQuestionsList;
    }

    public ArrayList<Integer> getAppLevelColorsList() {
        return appLevelColorsList;
    }
}
