package com.example.quizappassignment3ritika;

public class CorrectAnswerAttempt {

    private String AttemptNo;
    private int score;
    private int totalQues;
    private float scorePercentage;

    @Override
    public String toString() {
        return "Attempt"+AttemptNo+"-"+ score +"-"+totalQues +"-"+scorePercentage+"\n";
    }

    public float getScorePercentage() {
        return scorePercentage;
    }

    public CorrectAnswerAttempt(String attemptNo, int score, int totalQues, float scorePercentage) {
        AttemptNo = attemptNo;
        this.score = score;
        this.totalQues = totalQues;
        this.scorePercentage = scorePercentage;

    }

}
