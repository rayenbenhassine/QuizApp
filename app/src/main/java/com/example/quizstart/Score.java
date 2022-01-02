package com.example.quizstart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Score extends AppCompatActivity {
    private TextView email;
    private TextView txtScore;
    private TextView txtMention;
    private Button btnTryAgain;
    private TextView meilleurScore;
    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        this.email = (TextView) findViewById(R.id.email);
        this.txtScore = (TextView) findViewById(R.id.txtScore);
        this.txtMention = (TextView) findViewById(R.id.txtMention);
        this.btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        this.meilleurScore = (TextView) findViewById(R.id.meilleurScore);
        this.db = new DatabaseManager(getApplicationContext());

        User user = (User) getIntent().getSerializableExtra("user");
        this.email.setText(user.getEmail());
        this.txtScore.setText(Integer.toString(user.getScore()));
        if(user.getScore() == 0){
            this.txtMention.setText("Aucune reponse correcte");
        }
        else if(user.getScore() >= 1 && user.getScore() <= 4){
            this.txtMention.setText("Passable");
        }
        else if(user.getScore() >= 5 && user.getScore() <= 7){
            this.txtMention.setText("Bien");
        }
        else{
            this.txtMention.setText("Excellent !");
        }
        if(user.getScore() > user.getMeilleurScore()){
            db.updateMeilleurScore(user.getEmail(), user.getScore());
            this.meilleurScore.setText("Félicitation c'est votre nouveau record");
            this.meilleurScore.setTextColor(Color.GREEN);
        }
        else{
            this.meilleurScore.setText("Votre meilleur score est : " + user.getMeilleurScore());
        }

        this.btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}