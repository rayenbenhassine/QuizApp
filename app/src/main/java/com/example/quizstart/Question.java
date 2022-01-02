package com.example.quizstart;


import java.io.Serializable;

public class Question implements Serializable {
    private int id_question;
    private String question;
    private String prop1;
    private String prop2;
    private String prop3;
    private String reponseCorrecte;
    private String explication;
    private String theme;

    public Question(int id_question, String question, String prop1, String prop2, String prop3, String reponseCorrecte, String explication, String theme) {
        this.id_question = id_question;
        this.question = question;
        this.prop1 = prop1;
        this.prop2 = prop2;
        this.prop3 = prop3;
        this.reponseCorrecte = reponseCorrecte;
        this.explication = explication;
        this.theme = theme;
    }

    public String getQuestion() {
        return question;
    }

    public String getProp1() {
        return prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public String getProp3() {
        return prop3;
    }

    public String getReponseCorrecte() {
        return reponseCorrecte;
    }

    public String getTheme() {
        return theme;
    }


    public String getExplication() {
        return explication;
    }
}
