package com.example.quizappassignment3ritika;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class SetQuestionsDialogFragment extends DialogFragment {

    interface SetQuestionsListener{
        void setUserSelectedInput(int userInput);
    }
    static int totalQuestions;
    int userSelectedInput;
    SetQuestionsListener questionsListener;

    public static SetQuestionsDialogFragment newInstance(int totalNo){
        totalQuestions = totalNo;
        return new SetQuestionsDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.fragment_set_questions_alert_layout,container,false);
        Spinner spinner = fragmentView.findViewById(R.id.numberOfQuestionsSpinner);
        Button doneButton = fragmentView.findViewById(R.id.setQuestionsDoneButton);
        Button cancelButton = fragmentView.findViewById(R.id.setQuestionCancelButton);
        ArrayList<Integer> questionNoList = new ArrayList<>();
        int q = 1;
        while(q<=totalQuestions){
            questionNoList.add(q);
            q++;
        }
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(requireContext(),R.layout.spinner_row,R.id.spinnerRowText,questionNoList);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userSelectedInput = questionNoList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionsListener.setUserSelectedInput(userSelectedInput);
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return fragmentView;

    }
}
