package com.example.quizappassignment3ritika;

import android.widget.Toast;

import java.util.ArrayList;

public class QuestionBank {

    private ArrayList<Question> questionsList;
    private ArrayList<Integer> questionsColorList;

    public ArrayList<Question> getQuestionsList() {
        if (questionsList==null){
            questionsList = new ArrayList<>();
            questionsList.add(new Question("The Azure Portal updates continuously and requires no downtime for maintenance activities",true));
            questionsList.add(new Question("Serverless computing enables developers to build applications faster by eliminating the need for them to manage infrastructure",true));
            questionsList.add(new Question("The Azure Marketplace is a service on Azure specific to Microsoft Products",false));
            questionsList.add(new Question("To create and use Azure services; you need an Azure subscription",true));
            questionsList.add(new Question("Horizontal scalability is when computing capacity can be increased by adding additional RAM or CPUs to a virtual machine",false));
            questionsList.add(new Question("An Azure account can only have a single subscription",false));
            questionsList.add(new Question("If you delete a resource group; all resources contained within that group are also deleted",true));
            questionsList.add(new Question("All Azure Regions support Availability Zones",false));
            questionsList.add(new Question("Azure Cosmos DB only supports proprietary(relating to an owner or ownership)APIs",false));
            questionsList.add(new Question("One major benefit of using Azure Cosmos DB is the flexibility it allows developers to use the APIs they are most familiar with",true));
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
