package com.example.gestionsetud;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EtudiantDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "EtudiantManager";
    private static final String TABLE_ETUDIANT = "table_Etudiant";
    private static final String COLONNE_ID = "id";
    private static final String COLONNE_NOM = "nom";
    private static final String COLONNE_PRENOM = "prenom";
    private static final String COLONNE_CLASSE = "classe";
    private static final String COLONNE_GROUPE = "groupe";
    private static final String COLONNE_LOGIN = "login";
    private static final String COLONNE_PASSWORD = "password";
    private static final String REQUETE_CREATION_BD = "create table "
            + TABLE_ETUDIANT + " (" + COLONNE_ID + " integer primary key autoincrement, "
            + COLONNE_NOM + " TEXT not null, "
            + COLONNE_PRENOM + " TEXT not null, "
            + COLONNE_CLASSE + " TEXT not null, "
            + COLONNE_GROUPE + " TEXT not null, "
            + COLONNE_LOGIN + " TEXT not null, "
            + COLONNE_PASSWORD + " TEXT not null);";

    public EtudiantDBHandler(Context context, String nom, SQLiteDatabase.CursorFactory cursorfactory, int version) {
        super(context, nom , cursorfactory, version );
    }
    public EtudiantDBHandler(Context context) {
        super(context, DATABASE_NAME ,null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_ETUDIANT + ";");
        onCreate(db);
    }

    public void insertUser(Etudiant etudiant) {
        SQLiteDatabase maBD = this.getWritableDatabase();

        ContentValues valeurs = new ContentValues();

        valeurs.put(COLONNE_NOM,etudiant.getNom());
        valeurs.put(COLONNE_PRENOM,etudiant.getPrenom());
        valeurs.put(COLONNE_CLASSE,etudiant.getClasse());
        valeurs.put(COLONNE_GROUPE,etudiant.getGroupe());
        valeurs.put(COLONNE_LOGIN,etudiant.getLogin());
        valeurs.put(COLONNE_PASSWORD,etudiant.getPassword());
        maBD.insert(TABLE_ETUDIANT, null, valeurs);
        maBD.close();
    }

    public int updateEtudiant(int id, Etudiant EtudiantToUpdate) {
        SQLiteDatabase maBD =this.getWritableDatabase();
        ContentValues valeurs = new ContentValues();

        valeurs.put(COLONNE_NOM,EtudiantToUpdate.getNom());
        valeurs.put(COLONNE_PRENOM,EtudiantToUpdate.getPrenom());
        valeurs.put(COLONNE_CLASSE,EtudiantToUpdate.getClasse());
        valeurs.put(COLONNE_GROUPE,EtudiantToUpdate.getGroupe());
        valeurs.put(COLONNE_LOGIN,EtudiantToUpdate.getLogin());
        valeurs.put(COLONNE_PASSWORD,EtudiantToUpdate.getPassword());
        maBD.update(TABLE_ETUDIANT, valeurs,COLONNE_ID + " = "+ id, null);
        maBD.close();
        return id;
    }

    public void removeEtudiantnom(String nom) {
        SQLiteDatabase maBD = this.getWritableDatabase();
        maBD.delete(TABLE_ETUDIANT, COLONNE_NOM+ " = ?", new String[] { nom});
        maBD.close();
    }
    public void removeEtudiant(int id) {
        SQLiteDatabase maBD = this.getWritableDatabase();
        maBD.delete(TABLE_ETUDIANT,COLONNE_ID +" = "+ id, null);
        maBD.close();
    }

    public Etudiant getUser(int id) {
        SQLiteDatabase maBD = this.getReadableDatabase();
        Cursor c = maBD.query(TABLE_ETUDIANT,
                        new String[] {
                                COLONNE_ID, COLONNE_NOM,
                                COLONNE_PRENOM,
                                COLONNE_CLASSE,
                                COLONNE_GROUPE,
                                COLONNE_LOGIN,
                                COLONNE_PASSWORD
                        },
                        COLONNE_ID + " =? " ,
                        new  String[]{String.valueOf(id)},
                        null, null, null);
        return cursorToEtudiant(c);
    }
    public Etudiant getUsern(String nom) {
        SQLiteDatabase maBD = this.getReadableDatabase();
        Cursor c = maBD.query(TABLE_ETUDIANT,
                new String[] {
                        COLONNE_ID, COLONNE_NOM,
                        COLONNE_PRENOM,
                        COLONNE_CLASSE,
                        COLONNE_GROUPE,
                        COLONNE_LOGIN,
                        COLONNE_PASSWORD
                },
                COLONNE_LOGIN + " =? " ,
                new  String[]{String.valueOf(nom)},
                null, null, null);
        return cursorToEtudiant(c);
    }

    public ArrayList<Etudiant> getAllEtudiants() {
        SQLiteDatabase maBD = this.getReadableDatabase();
        Cursor c = maBD.query(TABLE_ETUDIANT,
                new String[] { COLONNE_ID, COLONNE_NOM,
                        COLONNE_PRENOM,
                        COLONNE_CLASSE,
                        COLONNE_GROUPE,
                        COLONNE_LOGIN,
                        COLONNE_PASSWORD},
                null, null, null,null, null);
        return cursorToEtudiants(c);
    }

    private Etudiant cursorToEtudiant(Cursor c) {
        if (c==null || c.getCount() == 0)
            return null;
        c.moveToFirst();
        Etudiant retEtudiant = new Etudiant();
        retEtudiant.setId(c.getInt(0));
        retEtudiant.setNom(c.getString(1));
        retEtudiant.setPrenom(c.getString(2));
        retEtudiant.setClasse(c.getString(3));
        retEtudiant.setGroupe(c.getString(4));
        retEtudiant.setLogin(c.getString(5));
        retEtudiant.setPassword(c.getString(6));
        c.close();
        return retEtudiant;
    }
    private ArrayList<Etudiant>cursorToEtudiants(Cursor c) {
        if (c.getCount() == 0)
            return new ArrayList<Etudiant>(0);
        ArrayList<Etudiant> retEtudiants = new
                ArrayList<Etudiant>(c.getCount());
        c.moveToFirst();
        do {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(c.getInt(0));
            etudiant.setNom(c.getString(1));
            etudiant.setPrenom(c.getString(2));
            etudiant.setClasse(c.getString(3));
            etudiant.setGroupe(c.getString(4));
            etudiant.setLogin(c.getString(5));
            etudiant.setPassword(c.getString(6));
            retEtudiants.add(etudiant);
        } while (c.moveToNext());
        c.close();
        return retEtudiants;
    }

    public boolean checkUser(String login){
        SQLiteDatabase maBD = this.getReadableDatabase();
        Cursor c = maBD.query(TABLE_ETUDIANT,
                new String[] {
                        COLONNE_ID, COLONNE_NOM,
                        COLONNE_PRENOM,
                        COLONNE_CLASSE,
                        COLONNE_GROUPE,
                        COLONNE_LOGIN,
                        COLONNE_PASSWORD
                },
                COLONNE_LOGIN + " =? " ,
                new  String[]{String.valueOf(login)},
                null, null, null);
        return (c.getCount()>0);
    }

    public boolean checkNom(String nom){
        SQLiteDatabase maBD = this.getReadableDatabase();
        Cursor c = maBD.query(TABLE_ETUDIANT,
                new String[] {
                        COLONNE_ID, COLONNE_NOM,
                        COLONNE_PRENOM,
                        COLONNE_CLASSE,
                        COLONNE_GROUPE,
                        COLONNE_LOGIN,
                        COLONNE_PASSWORD
                },
                COLONNE_NOM + " =? " ,
                new  String[]{String.valueOf(nom)},
                null, null, null);
        return (c.getCount()>0);
    }
    public boolean checkpassword(String password) {
        SQLiteDatabase maBD = this.getReadableDatabase();
        Cursor c = maBD.query(TABLE_ETUDIANT,
                new String[]{
                        COLONNE_ID, COLONNE_NOM,
                        COLONNE_PRENOM,
                        COLONNE_CLASSE,
                        COLONNE_GROUPE,
                        COLONNE_LOGIN,
                        COLONNE_PASSWORD
                },
                COLONNE_PASSWORD + " =? ",
                new String[]{String.valueOf(password)},
                null, null, null);
        return (c.getCount() > 0);
    }
}