package com.example.cargame1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final int ROWS = 3;
    private static final int COLS = 7;
    private int carIndex = 1;
    private int carCrash = 0;
    private int countUpdateUI = 0;

    private static final long DELAY = 1000;
    final Handler handler = new Handler();
    private ImageView[][] matrixImages = new ImageView[ROWS][COLS];

    private ImageView main_IMG_heart1;
    private ImageView main_IMG_heart2;
    private ImageView main_IMG_heart3;
   private Button leftButton;
    private Button rightButton;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, DELAY);
            try {
                updateUI();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    private void updateUI() throws InterruptedException {
        countUpdateUI++;
        int[][] matrixCarObstacle = new int[ROWS][COLS];
        for (int i = 0; i < matrixImages.length; i++) {
            for (int j = 0; j < matrixImages[i].length; j++) {
                if ( matrixImages[i][j].getDrawable() != null && matrixImages[i][j].getDrawable() != matrixImages[carIndex][5].getDrawable()) {
                    matrixCarObstacle[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < matrixCarObstacle.length; i++) {
            for (int j = 0; j < matrixCarObstacle[i].length; j++) {
                if (matrixCarObstacle[i][j] == 1){
                    matrixCarObstacle[i][j]=0;
                    matrixImages[i][j].setImageResource(0);
                    if (j!=matrixCarObstacle[i].length -1){
                        if (matrixImages[i][j+1].getDrawable()==matrixImages[carIndex][5].getDrawable()) {
                            carCrash++;
                            if (carCrash==1) {
                                main_IMG_heart3.setVisibility(View.INVISIBLE);
                            }else {
                                if (carCrash == 2) {
                                    main_IMG_heart2.setVisibility(View.INVISIBLE);
                                }else {
                                    if (carCrash == 3) {
                                        main_IMG_heart1.setVisibility(View.INVISIBLE);
                                        TimeUnit.SECONDS.sleep(1);
                                        carCrash = 0;
                                        main_IMG_heart1.setVisibility(View.VISIBLE);
                                        main_IMG_heart2.setVisibility(View.VISIBLE);
                                        main_IMG_heart3.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }else{
                            matrixImages[i][j + 1].setImageResource(R.drawable.car_obstacle);
                        }
                    }
                }
            }
        }
        if (countUpdateUI == 3) {
            Random random = new Random();
            int randomNumber = random.nextInt(3);
            matrixImages[randomNumber][0].setImageResource(R.drawable.car_obstacle);
            countUpdateUI = 0;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMatrixFromViews();

        findViews();

        leftButton.setOnClickListener(view -> moveLeft());
        rightButton.setOnClickListener(view -> moveRight());

        handler.postDelayed(runnable, DELAY);
    }

    private void findViews() {
        leftButton = findViewById(R.id.buttonLeft);
        rightButton = findViewById(R.id.buttonRight);
        main_IMG_heart1 =findViewById(R.id.main_IMG_heart1);
        main_IMG_heart2=findViewById(R.id.main_IMG_heart2);
        main_IMG_heart3=findViewById(R.id.main_IMG_heart3);
    }

    private void moveRight() {
        if (carIndex != 2){
            carIndex++;
            matrixImages[carIndex-1][5].setImageResource(0);
            matrixImages[carIndex][5].setImageResource(R.drawable.car);
        }
    }

    private void moveLeft() {
        if (carIndex != 0){
            carIndex--;
            matrixImages[carIndex+1][5].setImageResource(0);
            matrixImages[carIndex][5].setImageResource(R.drawable.car);
        }
    }

    private void getMatrixFromViews() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrixImages[i][j] = findViewById(R.id.index1_1 + i*COLS + j);
            }
        }
    }
}