package com.example.android.quiz;

/*
  Copyright 2018 Andreas Leszczynski

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * This activity displays question 10 of the quiz
 */

public class Question10Activity extends AppCompatActivity {
    private int score;
    private String playerName;
    private CountDownTimer countDownTimer;
    private long timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
            recovering the instance state if available, else set attributes to values from
            last activity
         */
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("SCORE_KEY");
            playerName = savedInstanceState.getString("PLAYER_NAME_KEY");
            timer = savedInstanceState.getLong("TIMER_KEY");
        } else {
            score = getIntent().getIntExtra("Score", 0);
            playerName = getIntent().getCharSequenceExtra("Player").toString();
            // 31 seconds for timer
            timer = 31 * 1000;
        }
        setContentView(R.layout.activity_question10);
        startTimer();
    }

    /**
     * This method is called when the configuration of the Activity changes.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("SCORE_KEY", score);
        outState.putString("PLAYER_NAME_KEY", playerName);
        outState.putLong("TIMER_KEY", timer);
        countDownTimer.cancel();

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    /*
        this method is called when the player presses the next button
     */
    public void showResult(View view) {
        checkAnswer();
        gotoNextQuestion();
    }

    /*
        this method is called when the player presses the next button
     */
    private void checkAnswer() {
        CheckBox checkBox1 = findViewById(R.id.question10answer1);
        CheckBox checkBox2 = findViewById(R.id.question10answer2);
        CheckBox checkBox3 = findViewById(R.id.question10answer3);
        CheckBox checkBox4 = findViewById(R.id.question10answer4);

        if (checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked() && checkBox4.isChecked()) {
            score += 10;
        }
        Toast.makeText(this, getResources().getString(R.string.you_have) + " " + score + " " + getResources().getString(R.string.points), Toast.LENGTH_LONG).show();
    }

    /*
        this method is called to go to the next question, cancel the timer and pass attribute
        values to intent
     */
    private void gotoNextQuestion() {
        countDownTimer.cancel();
        Intent intent = new Intent(this, EndActivity.class);
        intent.putExtra("Score", score);
        intent.putExtra("Player", playerName);
        startActivity(intent);
    }

    /*
        this method is called to start the timer
     */
    private void startTimer() {
        final TextView textView = findViewById(R.id.timer10);
        countDownTimer = new CountDownTimer(timer, 1000) {
            @Override
            public void onTick(long millis) {
                textView.setText(String.format(Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millis),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
                timer = millis;
            }

            @Override
            public void onFinish() {
                gotoNextQuestion();
            }
        }.start();
    }
}