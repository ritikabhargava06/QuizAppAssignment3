package com.example.quizappassignment3ritika;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileManager {
    void updateQuizResultInFile(Context context, int attempt, int size, int correctAns,String task){
        String fileName = context.getString(R.string.quiz_file_name);
        String dataString = "";
        int mode = Context.MODE_PRIVATE;
        if (task.equals(context.getString(R.string.file_append_task_name))){
            CorrectAnswerAttempt correctAnsObj = new CorrectAnswerAttempt(String.valueOf(attempt),correctAns,size, (float)correctAns/(float)size);
            dataString = correctAnsObj.toString();
            mode = Context.MODE_APPEND;
        }
        try {
            FileOutputStream fos = context.openFileOutput(fileName, mode);
            fos.write(dataString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<CorrectAnswerAttempt> getAllCorrectAnswersFromFile(Context context){
        ArrayList<CorrectAnswerAttempt> correctAnsList = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(context.getString(R.string.quiz_file_name));
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while(line!=null){
                String[] resultArray = line.split("-");
                correctAnsList.add(new CorrectAnswerAttempt(resultArray[0],
                        Integer.parseInt(resultArray[1])
                        ,Integer.parseInt(resultArray[2]),
                        Float.parseFloat(resultArray[3])));
                //correctAnsList.add(Integer.parseInt(resultArray[1]));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return correctAnsList;
    }
}
