package com.example.cargame1;

import java.util.Date;

public class ScoreDate {

    private int score;
    private Date date;

    public ScoreDate(int score, Date date) {
        this.score = score;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }

}
