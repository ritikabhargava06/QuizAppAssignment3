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
        void saveData(int attempNo);
        void resetForNewAttempt();
    }

    static int totalQues;
    static int totalCorrectAns;

    static int attempt=0;

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
        builder.setTitle(R.string.result_alert_title);
        builder.setMessage("Your score is: "+totalCorrectAns+" out of "+totalQues);
        builder.setPositiveButton(R.string.result_alert_save_button_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                attempt++;
                listener.saveData(attempt);
                dismiss();
            }
        });
        builder.setNegativeButton(R.string.result_alert_ignore_button_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.resetForNewAttempt();
                dismiss();
            }
        });
        return builder.create();
    }
}
