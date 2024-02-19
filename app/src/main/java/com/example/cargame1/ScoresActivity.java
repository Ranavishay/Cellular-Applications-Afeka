package com.example.cargame1;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ScoresActivity extends AppCompatActivity {

    private TextView textViewScores;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        textViewScores = findViewById(R.id.textViewScores);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> finish());

        List<ScoreDate> tenScoresList = loadScoresSharedPreferences();
        displayScores(tenScoresList);
    }

    private List<ScoreDate> loadScoresSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("Scores", MODE_PRIVATE);
        String topScoresJson = preferences.getString("ScoresList", "");

        Gson gson = new Gson();
        Type type = new TypeToken<List<ScoreDate>>(){}.getType();
        List<ScoreDate> tenScoresList = gson.fromJson(topScoresJson, type);

        if (tenScoresList == null) {
            tenScoresList = new ArrayList<>();
        }

        return tenScoresList;
    }

    private void displayScores(List<ScoreDate> tenScoresList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tenScoresList.size(); i++) {
            ScoreDate scoreDate = tenScoresList.get(i);
            stringBuilder.append(i + 1).append(": ").append(scoreDate.getScore()).append(" - ").append(scoreDate.getDate()).append("\n");
        }
        textViewScores.setText(stringBuilder.toString());
    }
}
