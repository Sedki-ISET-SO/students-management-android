package com.example.gestionsetud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inscription extends Activity implements View.OnClickListener {
    EtudiantDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Button annuler = ((Button) findViewById(R.id.inscription));
        annuler.setOnClickListener(this);

        Button inscrire = ((Button) findViewById(R.id.inscriAnnuler));
        inscrire.setOnClickListener(this);

        db = new EtudiantDBHandler (this);

    }
    boolean isEmpty(EditText txt){
        CharSequence str=txt.getText().toString();
        return TextUtils.isEmpty(str);
    }

    @Override
    public void onClick(View view) {

        EditText prenom = (EditText) findViewById(R.id.prenomEditText);
        EditText nom = (EditText) findViewById(R.id.nomEditText);
        EditText classe = (EditText) findViewById(R.id.classEditText);
        EditText groupe = (EditText) findViewById(R.id.groupeEditText);
        EditText login = (EditText) findViewById(R.id.loginEditText);
        EditText password = (EditText) findViewById(R.id.passwordEditText);



        switch (view.getId()){

            case R.id.inscription:
                try {
                    String prenom1 = prenom.getText().toString();
                    String nom1 = nom.getText().toString();
                    String classe1 = classe.getText().toString();
                    String groupe1 = groupe.getText().toString();
                    String login1 = login.getText().toString();
                    String password1 = password.getText().toString();
                    if (isEmpty(nom) || isEmpty(prenom) || isEmpty(classe) || isEmpty(groupe) || isEmpty(login) || isEmpty(password)) {
                        Toast.makeText(this,"Champs vide", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        db.insertUser(new Etudiant(prenom1, nom1, classe1, groupe1, login1, password1));
                        setResult(RESULT_OK);
                        finish();
                    }
                }catch (Exception e){
                    Toast.makeText(this,"errr",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case  R.id.inscriAnnuler:
                setResult(RESULT_CANCELED);
                finish();
                break;

            default:
                break;
        }
    }
}