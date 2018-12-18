package com.acashikar.kanaguess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class StartupActivity extends AppCompatActivity {

    private TextView header;
    private TextView hiragana;
    private TextView katakana;
    private Button startButton;
    private Button clearButton;
    private Button selectAllButton;
    private ArrayList<CheckBox> checkedKana = new ArrayList<>();
    private ArrayList<Integer> checkedKanaIndexes = new ArrayList<>();
    private CheckBox check_a;
    private CheckBox check_ka;
    private CheckBox check_sa;
    private CheckBox check_ta;
    private CheckBox check_na;
    private CheckBox check_ha;
    private CheckBox check_ma;
    private CheckBox check_ya;
    private CheckBox check_ra;
    private CheckBox check_wa;
    private CheckBox kcheck_a;
    private CheckBox kcheck_ka;
    private CheckBox kcheck_sa;
    private CheckBox kcheck_ta;
    private CheckBox kcheck_na;
    private CheckBox kcheck_ha;
    private CheckBox kcheck_ma;
    private CheckBox kcheck_ya;
    private CheckBox kcheck_ra;
    private CheckBox kcheck_wa;
    public static final String EXTRA_INDEXES = "com.acashikar.kanaguess.INDEXES";
    static final int GUESSING_ACTIVITY_REQUEST = 1; // The request code

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_kana:
                    header.setText(R.string.title_header);
                    for(CheckBox c: checkedKana){
                        c.setVisibility(View.VISIBLE);
                    }
                    hiragana.setVisibility(View.VISIBLE);
                    katakana.setVisibility(View.VISIBLE);
                    startButton.setVisibility(View.VISIBLE);
                    clearButton.setVisibility(View.VISIBLE);
                    selectAllButton.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_settings:
                    header.setText(R.string.title_settings);
                    for(CheckBox c: checkedKana){
                        c.setVisibility(View.GONE);
                    }
                    hiragana.setVisibility(View.GONE);
                    katakana.setVisibility(View.GONE);
                    startButton.setVisibility(View.GONE);
                    clearButton.setVisibility(View.GONE);
                    selectAllButton.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_about:
                    header.setText(R.string.title_about);
                    for(CheckBox c: checkedKana){
                        c.setVisibility(View.GONE);
                    }
                    hiragana.setVisibility(View.GONE);
                    katakana.setVisibility(View.GONE);
                    startButton.setVisibility(View.GONE);
                    clearButton.setVisibility(View.GONE);
                    selectAllButton.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        check_a = findViewById(R.id.check_a);
        check_ka = findViewById(R.id.check_ka);
        check_sa = findViewById(R.id.check_sa);
        check_ta = findViewById(R.id.check_ta);
        check_na = findViewById(R.id.check_na);
        check_ha = findViewById(R.id.check_ha);
        check_ma = findViewById(R.id.check_ma);
        check_ya = findViewById(R.id.check_ya);
        check_ra = findViewById(R.id.check_ra);
        check_wa = findViewById(R.id.check_wa);
        kcheck_a = findViewById(R.id.kcheck_a);
        kcheck_ka = findViewById(R.id.kcheck_ka);
        kcheck_sa = findViewById(R.id.kcheck_sa);
        kcheck_ta = findViewById(R.id.kcheck_ta);
        kcheck_na = findViewById(R.id.kcheck_na);
        kcheck_ha = findViewById(R.id.kcheck_ha);
        kcheck_ma = findViewById(R.id.kcheck_ma);
        kcheck_ya = findViewById(R.id.kcheck_ya);
        kcheck_ra = findViewById(R.id.kcheck_ra);
        kcheck_wa = findViewById(R.id.kcheck_wa);
        checkedKana.addAll(Arrays.asList(check_a, check_ka, check_sa, check_ta, check_na,
                check_ha, check_ma, check_ya, check_ra, check_wa, kcheck_a, kcheck_ka,
                kcheck_sa, kcheck_ta, kcheck_na, kcheck_ha, kcheck_ma, kcheck_ya, kcheck_ra, kcheck_wa));
        header = findViewById(R.id.kana);
        hiragana = findViewById(R.id.hiragana);
        katakana = findViewById(R.id.katakana);
        header.setText("Select the kana you want to practice, then press \"Start\"!");
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        startButton = findViewById(R.id.startButton);
        clearButton = findViewById(R.id.clearButton);
        selectAllButton = findViewById(R.id.selectAllButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGuess(v);
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCheckBox(v);
            }
        });
        selectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAllCheckBox(v);
            }
        });
    }

    /** Called when the start button is pressed **/
    public void startGuess(View view){
        Intent intent = new Intent(this, GuessingActivity.class);
        checkedKana.clear();
        checkedKanaIndexes.clear();
        checkedKana.addAll(Arrays.asList(check_a, check_ka, check_sa, check_ta, check_na,
                check_ha, check_ma, check_ya, check_ra, check_wa, kcheck_a, kcheck_ka,
                kcheck_sa, kcheck_ta, kcheck_na, kcheck_ha, kcheck_ma, kcheck_ya, kcheck_ra, kcheck_wa));
        for(int i=0; i<checkedKana.size(); i++){
            if(checkedKana.get(i).isChecked()){
                checkedKanaIndexes.add(i);
            }
        }
        if(!checkedKanaIndexes.isEmpty()){
            intent.putExtra(EXTRA_INDEXES, checkedKanaIndexes);
            startActivityForResult(intent, GUESSING_ACTIVITY_REQUEST);
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "You haven't selected any kana!";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        }
    }

    /** Called when the clear button is pressed **/
    public void clearCheckBox(View view){
        for(CheckBox c: checkedKana){
            c.setChecked(false);
        }
    }

    /** Called when the select all button is pressed **/
    public void selectAllCheckBox(View view){
        for(CheckBox c: checkedKana){
            c.setChecked(true);
        }
    }

}
