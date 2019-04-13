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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * This app is a quiz about riddles
 */
public class MainActivity extends AppCompatActivity {

    private String playerName;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("SCORE_KEY");
            playerName = savedInstanceState.getString("PLAYER_NAME_KEY");
        }
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the configuration of the Activity changes.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("SCORE_KEY", score);
        outState.putString("PLAYER_NAME_KEY", playerName);

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    /*
        this method is called when player presses start button
     */
    public void question1(View view) {
        setPlayerName();
        Intent intent = new Intent(this, Question1Activity.class);
        intent.putExtra("Player", playerName);
        intent.putExtra("Score", score);
        startActivity(intent);
    }

    /*
        this method is called to set the players name
     */
    private void setPlayerName() {
        EditText input = findViewById(R.id.player_name);
        playerName = input.getText().toString();
    }

}
