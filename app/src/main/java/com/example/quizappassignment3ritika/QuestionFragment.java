package com.example.quizappassignment3ritika;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuestionFragment extends Fragment {

    static String quesText;
    static int quesColor;
    public static QuestionFragment newInstance(String questionText, int questionColor){
        quesText=questionText;
        quesColor=questionColor;
        return new QuestionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View questionView = inflater.inflate(R.layout.fragment_question_layout,container,false);
        TextView quesTextView = questionView.findViewById(R.id.question_textview);
        quesTextView.setText(quesText);
        quesTextView.setBackgroundResource(quesColor);
        return questionView;
    }
}
