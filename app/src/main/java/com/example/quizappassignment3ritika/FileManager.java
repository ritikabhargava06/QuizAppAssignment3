package com.example.quizappassignment3ritika;

import android.content.Context;
import java.io.FileOutputStream;
import java.io.IOException;

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
}
