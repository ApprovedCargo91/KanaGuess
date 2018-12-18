package com.acashikar.kanaguess;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GuessingActivity extends AppCompatActivity {

    private ArrayList<Integer> indexes = new ArrayList<>();
    private ArrayList<String> selKana = new ArrayList<>();
    private ArrayList<String> selKanaPron = new ArrayList<>();
    private ArrayList<String> correct = new ArrayList<>();
    private ArrayList<String> incorrect = new ArrayList<>();
    private ArrayList<String> aPron = new ArrayList<>(Arrays.asList("a","i","u","e","o"));
    private ArrayList<String> kaPron = new ArrayList<>(Arrays.asList("ka", "ki","ku","ke","ko"));
    private ArrayList<String> saPron = new ArrayList<>(Arrays.asList("sa","shi","su","se","so"));
    private ArrayList<String> taPron = new ArrayList<>(Arrays.asList("ta", "chi","tsu","te","to"));
    private ArrayList<String> naPron = new ArrayList<>(Arrays.asList("na","ni","nu","ne","no"));
    private ArrayList<String> haPron = new ArrayList<>(Arrays.asList("ha","hi","fu","he","ho"));
    private ArrayList<String> maPron = new ArrayList<>(Arrays.asList("ma","mi","mu","me","mo"));
    private ArrayList<String> yaPron = new ArrayList<>(Arrays.asList("ya","yu","yo"));
    private ArrayList<String> raPron = new ArrayList<>(Arrays.asList("ra","ri","ru","re","ro"));
    private ArrayList<String> waPron = new ArrayList<>(Arrays.asList("wa","wo","n"));
    private ArrayList<String> kanaBank = new ArrayList<>(Arrays.asList("あいうえお","かきくけこ",
            "さしすせそ","たちつてと","なにぬねの","はひふへほ",
            "まみむめも","やゆよ","らりるれろ","わをん","アイウエオ",
            "カキクケコ","サシスセソ","タチツテト","ナニヌネノ",
            "ハヒフヘホ","マミムメモ","ヤユヨ","ラリルレロ","ワヲン"));
    private ArrayList<ArrayList<String>> kanaPronBank = new ArrayList<>(Arrays.asList(aPron, kaPron, saPron,
            taPron, naPron, haPron, maPron, yaPron, raPron,
            waPron, aPron, kaPron, saPron, taPron, naPron,
            haPron, maPron, yaPron, raPron, waPron));
    private TextView kanaBox;
    private Button guessButton;
    private Button endButton;
    private EditText guessBox;
    private Random random = new Random();
    private int randTemp;
    private TextView correctCounter;
    private TextView incorrectCounter;
    private int correctCount;
    private int incorrectCount;
    public static final String EXTRA_CORRECT = "com.acashikar.kanaguess.CORRECT";
    public static final String EXTRA_INCORRECT = "com.acashikar.kanaguess.INCORRECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing);

        kanaBox = findViewById(R.id.kanaBox);
        guessButton = findViewById(R.id.guessButton);
        endButton = findViewById(R.id.endButton);
        guessBox = findViewById(R.id.guessBox);
        correctCounter = findViewById(R.id.correctCounter);
        incorrectCounter = findViewById(R.id.incorrectCounter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume()");

        correctCounter.setText(R.string.correct_0);
        incorrectCounter.setText(R.string.incorrect_0);
        correct.clear();
        incorrect.clear();
        kanaBox.setTextSize(260);
        indexes = getIntent().getIntegerArrayListExtra(StartupActivity.EXTRA_INDEXES);
        selKana.clear();
        setKana();
        System.out.println("selKana = "+selKana.toString());
        System.out.println("selKanaPron = "+selKanaPron.toString());
        randTemp = randomIntSet();
        System.out.println("randTemp = "+randTemp);
        correctCount = 0;
        incorrectCount = 0;
        buttonActions();
    }

    /** Explodes a string into its individual characters **/
    public ArrayList<String> explodeString(String kanas){
        ArrayList<String> explodedString = new ArrayList<>();
        for(int i=0; i<kanas.length(); i++){
            explodedString.add(kanas.substring(i,i+1));
        }
        return explodedString;
    }

    /** Returns a random int based on the size of selKana **/
    public int randomIntSet(){
        int rand = random.nextInt(selKana.size());
        kanaBox.setText(selKana.get(rand));
        return rand;
    }

    /** Ends this activity if the Back button is pressed **/
    @Override
    public void onBackPressed() {
        finish();
    }

    /** Show results **/
    public void showResults() {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra(EXTRA_CORRECT,correct);
        intent.putExtra(EXTRA_INCORRECT,incorrect);
        startActivity(intent);
    }

    public void setKana() {
        for(int i:indexes){
            selKana.addAll(explodeString(kanaBank.get(i)));
            selKanaPron.addAll(kanaPronBank.get(i));
        }
    }
    public void buttonActions() {
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Starting activity";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER,0,250);
                if(guessBox.getText().toString().equals(selKanaPron.get(randTemp))){
                    text = "Correct!";
                    toast.cancel();
                    toast.setText(text);
                    toast.show();
                    correctCount++;
                    correctCounter.setText("Correct: "+correctCount);
                    correct.add(selKana.get(randTemp));
                }
                else{
                    text = "Incorrect...";
                    toast.cancel();
                    toast.setText(text);
                    toast.show();
                    incorrectCount++;
                    incorrectCounter.setText("Incorrect: "+incorrectCount);
                    incorrect.add(selKana.get(randTemp));
                }
                guessBox.setText("");
                selKana.remove(randTemp);
                selKanaPron.remove(randTemp);
                if(selKana.isEmpty()){
                    showResults();
                    selKana.add("Done!");
                    kanaBox.setTextSize(100);
                }
                randTemp = randomIntSet();
            }
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
