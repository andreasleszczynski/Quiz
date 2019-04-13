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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This activity displays the result and player can choose to play again or leave
 */

public class EndActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        ImageView imageView = findViewById(R.id.image_end);

        TextView playerName = findViewById(R.id.player_name);
        playerName.setText(getIntent().getCharSequenceExtra("Player").toString());

        TextView score = findViewById(R.id.player_score);
        if (getIntent().getIntExtra("Score", 0) >= 70) {
            imageView.setImageResource(R.drawable.awesome);
        } else if (getIntent().getIntExtra("Score", 0) >= 40) {
            imageView.setImageResource(R.drawable.birdie);
        } else {
            imageView.setImageResource(R.drawable.dog);
        }
        score.setText(String.valueOf(getIntent().getIntExtra("Score", 0)));

        Button button_yes = findViewById(R.id.button_yes);
        button_yes.setOnClickListener(this);

        Button button_no = findViewById(R.id.button_no);
        button_no.setOnClickListener(this);
    }

    /*
        this method is called when player presses yes or no button
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_yes) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            this.finishAffinity();
        }
    }
}