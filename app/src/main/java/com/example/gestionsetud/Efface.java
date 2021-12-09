package com.example.gestionsetud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Efface extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efface);

        Button effacer = ((Button) findViewById(R.id.effacer));
        effacer.setOnClickListener(this);

        Button annuler = ((Button) findViewById(R.id.effacerAnnuler));
        annuler.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText nom = (EditText) findViewById(R.id.nomeffEditText);

        EtudiantDBHandler db = new EtudiantDBHandler(this);
        switch (view.getId()){

            case R.id.effacer:
                String nom1 = nom.getText().toString();
                if(db.checkNom(nom1)){
                    db.removeEtudiantnom(nom1);
                    setResult(RESULT_OK);
                    finish();
                }
                else{
                    Toast.makeText(this,"Inexsistant Ou deja supprimer ", Toast.LENGTH_SHORT).show();
                }

                break;
            case  R.id.effacerAnnuler:
                setResult(RESULT_CANCELED);
                finish();
                break;

            default:
                break;
        }
    }
}

