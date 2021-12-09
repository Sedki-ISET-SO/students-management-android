package com.example.gestionsetud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Contact extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Button Web = ((Button) findViewById(R.id.web));
        Web.setOnClickListener(this);
        Button Appel = ((Button)findViewById(R.id.appel));
        Appel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.web:
                Uri uri = Uri.parse("http://www.google.fr/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case  R.id.appel:
                Uri uria = Uri.parse("tel:0612345678");
                Intent intenta = new Intent(Intent.ACTION_CALL, uria);
                startActivity(intenta);
                break;

            default:
                break;
        }
    }
}