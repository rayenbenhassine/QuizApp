package com.example.quizstart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.Math;

import java.util.ArrayList;
import java.util.Vector;

public class SportQ3 extends AppCompatActivity {
    private TextView theme;
    private TextView explication;
    private TextView reponsecorrecte;
    private TextView question;
    private RadioGroup props;
    private RadioButton prop1;
    private RadioButton prop2;
    private RadioButton prop3;
    private Button valider;
    private Button questionSuivante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_q1);
        this.theme = (TextView) findViewById(R.id.theme);
        this.question = (TextView) findViewById(R.id.question);
        this.props = (RadioGroup) findViewById(R.id.props);
        this.prop1 = (RadioButton) findViewById(R.id.prop1);
        this.prop2 = (RadioButton) findViewById(R.id.prop2);
        this.prop3 = (RadioButton) findViewById(R.id.prop3);
        this.valider = (Button) findViewById(R.id.valider);
        this.explication = (TextView) findViewById(R.id.explication);
        this.reponsecorrecte = (TextView) findViewById(R.id.reponsecorrecte);
        this.questionSuivante = (Button) findViewById(R.id.questionSuivante);
        this.questionSuivante.setEnabled(false);

        ArrayList<Question> questions = (ArrayList<Question>) getIntent().getSerializableExtra("questions");
        this.theme.setText((String) getIntent().getExtras().get("theme"));
        int i = (int)(Math.random()*questions.size());
        this.theme.setText(questions.get(i).getTheme());
        this.question.setText(questions.get(i).getQuestion());
        this.prop1.setText(questions.get(i).getProp1());
        this.prop2.setText(questions.get(i).getProp2());
        this.prop3.setText(questions.get(i).getProp3());
        for(int j=0; j<questions.size(); j++){
            Log.i("a", questions.get(j).getQuestion());
        }


        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valider.setEnabled(false);
                String selectedprop = ((RadioButton)findViewById(props.getCheckedRadioButtonId())).getText().toString();
                if(selectedprop.equals(questions.get(i).getReponseCorrecte())){
                    reponsecorrecte.setText("Bravo\nLa réponse correcte : " + questions.get(i).getReponseCorrecte());
                    reponsecorrecte.setTextColor(Color.GREEN);
                    User user = (User) getIntent().getExtras().get("user");
                    user.addScore();
                }
                else{
                    reponsecorrecte.setText("Fausse reponse\nLa réponse corecte est " + questions.get(i).getReponseCorrecte());
                    reponsecorrecte.setTextColor(Color.RED);
                }
                explication.setText("Explication : " + questions.get(i).getExplication());
                questionSuivante.setEnabled(true);
                questions.remove(i);
                questionSuivante.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), SportQ4.class);
                        intent.putExtra("questions",(Serializable) questions);
                        intent.putExtra("user",(User) getIntent().getExtras().get("user"));

                        startActivity(intent);
                    }
                });
            }
        });






    }
}