package com.example.quizstart;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String email;
    private int score;
    private int meilleurScore;

    public User(int id, String email, int meilleurScore) {
        this.id = id;
        this.email = email;
        this.score = 0;
        this.meilleurScore = meilleurScore;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getScore() {
        return score;
    }

    public int getMeilleurScore() {
        return meilleurScore;
    }

    public void addScore() {
        this.score += 2;
    }
}
