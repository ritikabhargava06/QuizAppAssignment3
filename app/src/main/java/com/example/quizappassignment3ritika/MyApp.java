package com.example.quizappassignment3ritika;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

    private ArrayList<Question> appLevelQuestionsList = new ArrayList<>();
    private ArrayList<Integer> appLevelColorsList =  new ArrayList<>();

    private FileManager fileManager;

    public FileManager getFileManager() {
        if(fileManager==null){
            fileManager = new FileManager();
        }
        return fileManager;
    }

    private int currentIndex=0;
    private int currentColor;
    private boolean newAttempt=true;
    private int correctAnswers=0;

   // private int attemptNo=1;

//    public int getAttemptNo() {
//        return attemptNo;
//    }
//
//    public void setAttemptNo(int attemptNo) {
//        this.attemptNo = attemptNo;
//    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public boolean isNewAttempt() {
        return newAttempt;
    }

    public void setNewAttempt(boolean newAttempt) {
        this.newAttempt = newAttempt;
    }

    public int getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

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
