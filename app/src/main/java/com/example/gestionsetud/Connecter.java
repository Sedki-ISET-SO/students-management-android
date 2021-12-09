package com.example.gestionsetud;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connecter extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecter);

        Button connecter = ((Button) findViewById(R.id.connexion));
        connecter.setOnClickListener(this);

        Button annuler = ((Button) findViewById(R.id.connexionAnnuler));
        annuler.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText login = (EditText) findViewById(R.id.loginEditText);
        EditText password = (EditText) findViewById(R.id.passwordEditText);

        EtudiantDBHandler db = new EtudiantDBHandler(this);
        switch (view.getId()){

            case R.id.connexion:
                String login1 = login.getText().toString();
                String password1 = password.getText().toString();
                if(db.checkUser(login1) && db.checkpassword(password1)){
                    setResult(RESULT_OK);
                    finish();
                }
                else{
                    Toast.makeText(this,"Login ou Password Faux ",Toast.LENGTH_SHORT).show();
                }

                break;
            case  R.id.connexionAnnuler:
                setResult(RESULT_CANCELED);
                finish();
                break;

            default:
                break;
        }
    }
}