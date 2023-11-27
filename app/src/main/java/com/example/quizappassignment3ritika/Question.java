package com.example.quizappassignment3ritika;

public class Question {

    private String questionText;
    private boolean answer;
    public Question(String questionText, boolean answer) {
        this.questionText = questionText;
        this.answer = answer;
    }
    public String getQuestionText() {
        return questionText;
    }
    public boolean getAnswer() {
        return answer;
    }
}
