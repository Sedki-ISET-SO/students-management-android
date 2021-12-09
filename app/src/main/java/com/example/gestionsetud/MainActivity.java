package com.example.gestionsetud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    EtudiantDBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button inscrire = ((Button) findViewById(R.id.inscri));
        inscrire.setOnClickListener(this);

        Button connecter = ((Button) findViewById(R.id.connec));
        connecter.setOnClickListener(this);

        Button contacter = ((Button) findViewById(R.id.contact));
        contacter.setOnClickListener(this);

        Button consulter = ((Button) findViewById(R.id.consulter));
        consulter.setOnClickListener(this);
        Button effacer = ((Button) findViewById(R.id.effacer));
        effacer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         db = new EtudiantDBHandler(this);
        switch (view.getId()){

            case R.id.inscri:
                Intent i = new Intent(MainActivity.this,Inscription.class);
                startActivityForResult(i,1);

                break;
            case  R.id.connec:
                Intent c = new Intent(MainActivity.this,Connecter.class);
                startActivityForResult(c,2);

                break;
            case R.id.contact:
                Intent co = new Intent(MainActivity.this,Contact.class);
                startActivity(co);

                break;

            case R.id.consulter:
                db.removeEtudiant(11);
                List<Etudiant> etudiant = db.getAllEtudiants();
                String mess= "";
                for (Etudiant et :etudiant) {
                    mess += et;
                }
                Toast.makeText(this,mess+"//// get user by id\n"+db.getUser(5),Toast.LENGTH_SHORT).show();
                break;

            case R.id.effacer:
                Intent o = new Intent(MainActivity.this,Efface.class);
                startActivityForResult(o,3);

        }
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data){
        db = new EtudiantDBHandler(this);
        switch (requestCode){
            case (1): switch (resultCode){
                case RESULT_OK:
                    Toast.makeText(this,"Inscription OK",
                            Toast.LENGTH_SHORT).show();
                    return;
                case RESULT_CANCELED:
                    Toast.makeText(this,"Inscription Annuler",
                            Toast.LENGTH_SHORT).show();
                    return;
            }
            case (2): switch (resultCode){
                case RESULT_OK:
                    Toast.makeText(this,"Connecter \n"+db.getUsern("user"),
                            Toast.LENGTH_SHORT).show();
                    return;
                case RESULT_CANCELED:
                    Toast.makeText(this,"Connexion Annuler",
                            Toast.LENGTH_SHORT).show();
                    return;
            }
            case (3): switch (resultCode){
                case RESULT_OK:
                    Toast.makeText(this,"Effacer Avec Succez",
                            Toast.LENGTH_SHORT).show();
                    return;
                case RESULT_CANCELED:
                    Toast.makeText(this,"Effacer Annuler",
                            Toast.LENGTH_SHORT).show();
                    return;
            }
        }
    }
}