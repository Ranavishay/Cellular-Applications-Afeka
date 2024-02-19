package com.example.cargame1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static int DELAY = 1000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStartWithButtons = findViewById(R.id.buttonStartWithButtons);
        Button buttonStartWithTilt = findViewById(R.id.buttonStartWithTilt);
        Button buttonViewScores = findViewById(R.id.buttonViewScores);
        Button buttonChangeSpeed = findViewById(R.id.buttonChangeSpeed);
        
        buttonChangeSpeed.setOnClickListener(view -> changeSpeed(buttonChangeSpeed));

        buttonStartWithButtons.setOnClickListener(view -> startGameWithButtons());
        buttonStartWithTilt.setOnClickListener(view -> startGameWithTilt());
        buttonViewScores.setOnClickListener(view -> viewScores());
    }

    @SuppressLint("SetTextI18n")
    private void changeSpeed(Button buttonChangeSpeed) {
        if (DELAY == 1000) {
            DELAY = 500;
            buttonChangeSpeed.setText("Fast Mode");
        } else {
            DELAY = 1000;
            buttonChangeSpeed.setText("Slow Mode");
        }
    }

    private void startGameWithButtons() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Mode", "buttons");
        intent.putExtra("Delay", DELAY);
        startActivity(intent);
    }

    private void startGameWithTilt() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Mode", "tilt");
        intent.putExtra("Delay", DELAY);
        startActivity(intent);
    }

    private void viewScores() {
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
    }
}
