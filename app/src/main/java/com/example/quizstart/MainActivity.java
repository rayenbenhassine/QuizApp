package com.example.quizstart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private RadioGroup theme;
    private Button btnValider;
    private String selectedTheme;
    private AutoCompleteTextView email;
    private DatabaseManager db;
    private TextView error;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.theme = (RadioGroup) findViewById((R.id.theme));
        this.btnValider = (Button) findViewById(R.id.btnValider);
        this.email = (AutoCompleteTextView) findViewById(R.id.email);
        this.error = (TextView) findViewById(R.id.error);

        this.db = new DatabaseManager(getApplicationContext());


        Vector<User> vectUser = db.selectUsers();
        String[] emails = new String[vectUser.size()];
        for(int i = 0 ;i<vectUser.size(); i++){
            emails[i] = vectUser.get(i).getEmail();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,emails);

        email.setAdapter(adapter);


        btnValider.setEnabled(false);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btnValider.setEnabled(!editable.toString().isEmpty());
            }
        });

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_user = email.getText().toString();
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email_user);
                if(matcher.find()){
                    if(!db.findUserbyEmail(email_user)){
                        db.insertUser(email_user);
                    }
                    User user = db.selectUserByEmail(email_user);
                    selectedTheme = ((RadioButton)findViewById(theme.getCheckedRadioButtonId())).getText().toString();

                    switch (selectedTheme){
                        case "Sport":{
                            ArrayList<Question> questions = db.selectQuestionsByTheme("sport");
                            Intent intent = new Intent(getApplicationContext(), SportQ1.class);
                            intent.putExtra("questions",(Serializable) questions);
                            intent.putExtra("user", (Serializable) user);
                            intent.putExtra("themrrae", selectedTheme);
                            startActivity(intent);
                            break;
                        }
                        case "Histoire":{
                            ArrayList<Question> questions = db.selectQuestionsByTheme("histoire");
                            Intent intent = new Intent(getApplicationContext(), HistoireQ1.class);
                            intent.putExtra("questions",(Serializable) questions);
                            intent.putExtra("user", (Serializable) user);
                            startActivity(intent);
                            break;
                        }
                        case "Informatique":{
                            ArrayList<Question> questions = db.selectQuestionsByTheme("informatique");
                            Intent intent = new Intent(getApplicationContext(), HistoireQ1.class);
                            intent.putExtra("questions",(Serializable) questions);
                            intent.putExtra("user", (Serializable) user);
                            startActivity(intent);
                            break;
                        }
                        case "Arts":{
                            ArrayList<Question> questions = db.selectQuestionsByTheme("arts");
                            Intent intent = new Intent(getApplicationContext(), HistoireQ1.class);
                            intent.putExtra("questions",(Serializable) questions);
                            intent.putExtra("user", (Serializable) user);
                            startActivity(intent);
                            break;

                        }
                    }
                }
                else{
                    error.setText("veuillez saisir une adresse email valide");
                    error.setTextColor(Color.RED);
                }




            }
        });

    }
}