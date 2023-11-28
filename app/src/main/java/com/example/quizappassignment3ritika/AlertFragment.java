package com.example.quizappassignment3ritika;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AlertFragment extends DialogFragment {

    interface alertButtonClickListener{
        void saveData();
        void resetForNewAttempt();
    }

    static int totalQues;
    static int totalCorrectAns;

    alertButtonClickListener listener;
    public static AlertFragment newInstance(int total, int correctAns){
        totalQues = total;
        totalCorrectAns = correctAns;
        return new AlertFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Result");
        builder.setMessage("Your score is: "+totalCorrectAns+" out of "+totalQues);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.saveData();
                dismiss();
            }
        });
        builder.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.resetForNewAttempt();
                dismiss();
            }
        });
        return builder.create();
    }
}
