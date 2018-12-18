package com.acashikar.kanaguess;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ResultsActivity extends AppCompatActivity {

    private ArrayList<String> correct = new ArrayList<>();
    private ArrayList<String> incorrect = new ArrayList<>();
    private TextView score;
    private TextView message;
    private double percent;
    private RecyclerView missedKanaDisplay;
    private RecyclerView.Adapter kanaAdapter;
    private RecyclerView.LayoutManager kanaLayoutManager;
    private Button tryAgain;
    private Button backToStart;
    private String pString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        correct = getIntent().getStringArrayListExtra(GuessingActivity.EXTRA_CORRECT);
        incorrect = getIntent().getStringArrayListExtra(GuessingActivity.EXTRA_INCORRECT);

        missedKanaDisplay = findViewById(R.id.missedKanaDisplay);
        missedKanaDisplay.setHasFixedSize(true);
        kanaLayoutManager = new GridLayoutManager(this, 5);
        missedKanaDisplay.setLayoutManager(kanaLayoutManager);
        kanaAdapter = new kanaAdapter(incorrect);
        missedKanaDisplay.setAdapter(kanaAdapter);

        score = findViewById(R.id.score);
        message = findViewById(R.id.message);
        missedKanaDisplay = findViewById(R.id.missedKanaDisplay);
        tryAgain = findViewById(R.id.tryAgainButton);
        backToStart = findViewById(R.id.backButton);
        percent = correct.size()*100.00/(correct.size()+incorrect.size());
        pString = String.format(Locale.US,"%.2f", percent);
        score.setText("Final Score: "+pString+"%");
        if(percent==100.00) {
            message.setText("Wow! A perfect score!");
        }
        else if(percent>=60.00){
            message.setText("Way to go! Keep practicing to get even better!");
        }
        else{
            message.setText("Don't worry too much, you'll get better with more practice!");
        }
        /** put RecyclerView code here **/
        backToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultsActivity.this, StartupActivity.class));
            }
        });
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultsActivity.this, StartupActivity.class));
    }
}
