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
            dataString = "Attempt"+attempt+"-"+correctAns+"-"+size+"\n";
            mode = Context.MODE_APPEND;
        }
        try {
            FileOutputStream fos = context.openFileOutput(fileName, mode);
            fos.write(dataString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<Integer> getAllCorrectAnswersFromFile(Context context){
        ArrayList<Integer> correctAnsList = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(context.getString(R.string.quiz_file_name));
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while(line!=null){
                String[] resultArray = line.split("-");
                correctAnsList.add(Integer.parseInt(resultArray[1]));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return correctAnsList;
    }
}
