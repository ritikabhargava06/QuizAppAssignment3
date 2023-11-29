package com.example.quizappassignment3ritika;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AverageAlertDialogFragment extends DialogFragment {

    static int averageResult;
    static int total;
    public static AverageAlertDialogFragment newInstance(int average, int totalQues){
        averageResult = average;
        total = totalQues;
        return new AverageAlertDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Your average score is: "+averageResult+" in "+total+" attempts");
        builder.setPositiveButton(R.string.average_alert_ok_button_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
}
