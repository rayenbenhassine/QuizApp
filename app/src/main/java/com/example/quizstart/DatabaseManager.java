package com.example.quizstart;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Vector;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Game.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (id_user integer primary key, email text, meilleur_score integer)");
        db.execSQL("create table question (id_question integer primary key,  question text, prop1 text, prop2 text,prop3 text,reponse_correcte text,explication text,theme text)");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Quel footballeur est surnommé la Puce','Andres Iniesta','Carlos Tevez','Lionel Messi','Lionel Messi','explication1','sport')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('En quelle année Zinédine Zidane a-t-il pris sa retraite en tant que joueur','2002','2006','2008','2006','explication1','sport')");


        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Quelle équipe a éliminé le PSG en quarts de finale de la Ligue des Champions 2016','Manchester City ','Chelsea','Athletico Madrid ','Manchester City ','explication1','sport')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Quel footballeur a été élu Ballon d’or 2015  ','Cristiano Ronaldo','Neymar','Lionel Messi','Lionel Messi','explication1','sport')");

        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('De quel pays africain, les Éléphants constituent-ils le nom de l’équipe de foot','De quel pays africain, les Éléphants constituent-ils le nom de l’équipe de foot','Tunisie','Côte d’Ivoire','Côte d’Ivoire','explication1','sport')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Qui a peint Le chat angora','Honoré Fragonard ','Gustave Courbet','Henri Matisse','Honoré Fragonard','explication1','arts')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Qui a peint Le bal à Bougival','Honoré Fragonard','Paul Cézanne','Auguste Renoir','Auguste Renoir','explication1','arts')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Qui chante Walking On air','Katty Perry','Beyonce','Taylor Swift','Katty Perry','explication1','sport')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Qui chante Treasure','Bruno Mars ','Ed sheeran','Marron 5','Bruno Mars','explication1','arts')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Quel est le titre  plus entendu deMichael Jackson','Thriller','Bad','Off the Wall','Thriller','explication1','arts')");

        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Quand l Algérie est-elle devenue indépendante','1962','1953','1776','1962','explication1','histoire')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Quel roi succède à Mohammed V au Maroc','Mohamed VI','Abdallah II','Hassann II','Hassann II','explication1','histoire')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Qui est le premier président noir d Afrique du Sud','Jacob Zuma','Nelson Mandela','Steve Biko','Nelson Mandela','explication1','histoire')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Dans la mythologie romaine, qui a fondé Rome','Castor et Pollux','Romulus et Rémus','Jules césar','Romulus et Rémus','explication1','histoire')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Qui était Puyi','Le dernier empereur chinois','Le Dragon dans Mulan','Un opposant à Mao Tsé-Tong','Romulus et Rémus','explication1','histoire')");

        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Lequel des langages informatiques suivants est utilisé pour l intelligence artificielle ','FORTRAN','C','PROLOG','PROLOG','explication1','informatique')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Le cerveau de tout système informatique est','Mémoire','CPU','Unité arithmétique et logique – ALU','CPU','explication1','informatique')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Le système binaire utilise la base','10','16','2','2','explication1','informatique')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Laquelle des mémoires suivantes est non volatile','DRAM','ROM','SRAM','ROM','explication1','informatique')");
        db.execSQL("insert into question (question,prop1,prop2,prop3,reponse_correcte,explication,theme) values ('Le microprocesseur a été introduit dans quelle génération d ordinateur','Quatrième génération','Troisième génération','Deuxième génération','Quatrième génération','explication1','informatique')");

        Log.i("DATABASE", "onCreate invoked");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "drop table user";
        db.execSQL(sql);
    }
    public void insertUser(String email){
        String sql ="insert into user (email, meilleur_score) values ('" + email + "', 0)";
        this.getWritableDatabase().execSQL(sql);
        Log.i("DATABASE", "insert");
    }

    public Vector<User> selectUsers() {
        String sql = "select * from user";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        Vector<User> vectUser = new Vector<>();
        if (c.moveToFirst()) {
            do {
                int id_user = c.getInt(0);
                String email = c.getString(1);
                int meilleur_score = c.getInt(2);
                User user = new User(id_user, email, meilleur_score);
                vectUser.add(user);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return vectUser;
    }

    public boolean findUserbyEmail(String email_user) {
        String sql = "select count(*) from user where email = '" + email_user + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        int nb_user = 0;
        if (c.moveToFirst()) {
            do {
                nb_user = c.getInt(0);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return nb_user == 1;

    }


    public User selectUserByEmail(String email_user) {
        String sql = "select * from user where email = '" + email_user + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        User user =null;
        if (c.moveToFirst()) {
            do {
                int id_user = c.getInt(0);
                String email = c.getString(1);
                int meilleur_score = c.getInt(2);
                user = new User(id_user, email, meilleur_score);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return user;
    }

    public ArrayList<Question> selectQuestionsByTheme(String theme) {
        String sql = "select * from question where theme = '" + theme + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        ArrayList<Question> questions = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                int id_question = c.getInt(0);
                String question = c.getString(1);
                String prop1 = c.getString(2);
                String prop2 = c.getString(3);
                String prop3 = c.getString(4);
                String reponse_correcte = c.getString(5);
                String explication = c.getString(6);
                String theme_q = c.getString(7);

                Question question_obj = new Question(id_question, question, prop1,prop2,prop3,reponse_correcte,explication,theme_q);
                questions.add(question_obj);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return questions;

    }

    public void updateMeilleurScore(String email, int score) {
        String sql ="update user set meilleur_score = " + score + " where email = '" + email + "'";
        this.getWritableDatabase().execSQL(sql);
        Log.i("DATABASE", "insert");
    }
}
