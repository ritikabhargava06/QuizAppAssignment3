package com.example.quizappassignment3ritika;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ResultAlertDialogFragment.alertButtonClickListener, SetQuestionsDialogFragment.SetQuestionsListener {

    QuestionBank qb = new QuestionBank();
    ArrayList<Question> currentQuesList = new ArrayList<>();
    ArrayList<Integer> currentColorsList = new ArrayList<>();
    Button bTrue;
    Button bFalse;
    ProgressBar quizProgressBar;
    int currIndex;
    String currQues;
    boolean currQuesAns;
    int currColor;
    int correctAns;
    FileManager fm;
    boolean disableSelectQuestionsMenu=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //code to save the listener for Result Alert Fragment when if the activity is recreated
        if(savedInstanceState!=null){
            ResultAlertDialogFragment resultAlert = (ResultAlertDialogFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.resultalert_tag_name));
            if(resultAlert!=null){
                resultAlert.listener = this;
            }
            SetQuestionsDialogFragment fragment = (SetQuestionsDialogFragment) getSupportFragmentManager().findFragmentByTag("SetQuestionsFragment");
            if(fragment!=null){
                fragment.questionsListener = this;
            }
        }
        bTrue = findViewById(R.id.true_button);
        bFalse = findViewById(R.id.false_button);
        quizProgressBar = findViewById(R.id.determinate_progress_bar);

        bTrue.setOnClickListener(this);
        bFalse.setOnClickListener(this);
        currIndex = ((MyApp)getApplication()).getCurrentIndex();
        correctAns = ((MyApp)getApplication()).getCorrectAnswers();
        fm = ((MyApp)getApplication()).getFileManager();

        if(((MyApp)getApplication()).isNewAttempt()){
            shuffleListsIfRequired();
        }

        currentQuesList = ((MyApp)getApplication()).getAppLevelQuestionsList();
        currentColorsList = ((MyApp)getApplication()).getAppLevelColorsList();
        getNextQuesDetails();
        addNewQuestionFragment();
    }

    private void shuffleListsIfRequired() {
            if(currIndex==0){
                Collections.shuffle(qb.getQuestionsList(MainActivity.this));
                Collections.shuffle(qb.getQuestionsColorList());
                ((MyApp)getApplication()).setNewAttempt(false);
                ((MyApp)getApplication()).setAppLevelQuestionsList(qb.getQuestionsList(MainActivity.this));
                ((MyApp)getApplication()).setAppLevelColorsList(qb.getQuestionsColorList());
            }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.true_button:
                if(currQuesAns){
                    Toast.makeText(MainActivity.this, R.string.correct_answer_msg, Toast.LENGTH_SHORT).show();
                    ((MyApp)getApplication()).setCorrectAnswers(correctAns+1);
                    correctAns = ((MyApp)getApplication()).getCorrectAnswers();
                }else{
                    Toast.makeText(MainActivity.this, R.string.Incorrect_answer_msg, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.false_button:
                if(!currQuesAns){
                    Toast.makeText(MainActivity.this, R.string.correct_answer_msg, Toast.LENGTH_SHORT).show();
                    ((MyApp)getApplication()).setCorrectAnswers(correctAns+1);
                    correctAns = ((MyApp)getApplication()).getCorrectAnswers();
                }else{
                    Toast.makeText(MainActivity.this, R.string.Incorrect_answer_msg, Toast.LENGTH_SHORT).show();
                }
                break;
        }

        //disable the menu option to restrict user from setting the total questions in middle of the quiz
        disableSelectQuestionsMenu = true;
        invalidateOptionsMenu();
        quizProgressBar.incrementProgressBy(100/currentQuesList.size());
        int newIndex = currIndex+1;
        if(newIndex==currentQuesList.size()) {
            //create an alert to show the result
            int oldAttemptsCountInFile = fm.getAllCorrectAnswersFromFile(MainActivity.this).size();
            ResultAlertDialogFragment alert = ResultAlertDialogFragment.newInstance(currentQuesList.size(), correctAns,oldAttemptsCountInFile);
            alert.listener = MainActivity.this;
            alert.show(getSupportFragmentManager(), getString(R.string.resultalert_tag_name));
            newIndex = 0;
            quizProgressBar.setProgress(0);
            //enable the menu option to let user select total question for new attempt
            disableSelectQuestionsMenu = false;
            invalidateOptionsMenu();
            ((MyApp) getApplication()).setNewAttempt(true);

        }
        ((MyApp)getApplication()).setCurrentIndex(newIndex);
        currIndex = ((MyApp)getApplication()).getCurrentIndex();
        if(((MyApp)getApplication()).isNewAttempt()){
            shuffleListsIfRequired();
            currentQuesList = ((MyApp)getApplication()).getAppLevelQuestionsList();
            currentColorsList = ((MyApp)getApplication()).getAppLevelColorsList();
        }
        getNextQuesDetails();
        addNewQuestionFragment();
    }

    private void getNextQuesDetails() {
        currQues = currentQuesList.get(currIndex).getQuestionText();
        currQuesAns = currentQuesList.get(currIndex).getAnswer();
        ((MyApp)getApplication()).setCurrentColor(currentColorsList.get(currIndex));
        currColor = ((MyApp)getApplication()).getCurrentColor();
    }

    private void addNewQuestionFragment() {
        QuestionFragment newQuesFragment =QuestionFragment.newInstance(currQues,currColor);
        QuestionFragment oldQuesFragment = (QuestionFragment) getSupportFragmentManager().findFragmentById(R.id.question_frame_layout);
        if(oldQuesFragment!=null){
            getSupportFragmentManager().beginTransaction().remove(oldQuesFragment).commit();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.question_frame_layout, newQuesFragment).commit();
    }

    @Override
    public void saveData(int attempt, int totalQ) {
        fm.updateQuizResultInFile(MainActivity.this,attempt,totalQ,correctAns,getString(R.string.file_append_task_name));
        Toast.makeText(MainActivity.this, R.string.data_saved_msg,Toast.LENGTH_LONG).show();
        ((MyApp) getApplication()).setCorrectAnswers(0);
        correctAns = ((MyApp) getApplication()).getCorrectAnswers();
    }

    @Override
    public void resetForNewAttempt() {
        ((MyApp) getApplication()).setCorrectAnswers(0);
        correctAns = ((MyApp) getApplication()).getCorrectAnswers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quiz_menu, menu);
        if (disableSelectQuestionsMenu){
            menu.getItem(1).setEnabled(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.getAverage:
                displayAverageResult();
                return true;
            case R.id.selectTotalQuestions:
                //set the number of questions
                SetQuestionsDialogFragment sqdf = SetQuestionsDialogFragment.newInstance(currentQuesList.size());
                sqdf.questionsListener = this;
                sqdf.show(getSupportFragmentManager(),"SetQuestionsFragment");
                return true;
            case R.id.resetSavedResult:
                fm.updateQuizResultInFile(MainActivity.this,0,0,0,getString(R.string.file_clear_task_name));
                Toast.makeText(MainActivity.this, R.string.file_data_erased_msg,Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void displayAverageResult(){
        ArrayList<Integer> ansList = fm.getAllCorrectAnswersFromFile(MainActivity.this);
        int sum = 0;
        for (Integer i:ansList) {
            sum=sum+i;
        }
        int average = sum/ansList.size();
        AverageAlertDialogFragment.newInstance(average,ansList.size()).show(getSupportFragmentManager(),null);
    }

    @Override
    public void setUserSelectedInput(int userInput) {
        int index = 0;
        ArrayList<Question> subList = new ArrayList<>();
        while(index<userInput){
            subList.add(((MyApp)getApplication()).getAppLevelQuestionsList().get(index));
            index++;
        }
        currentQuesList = subList;
    }
}