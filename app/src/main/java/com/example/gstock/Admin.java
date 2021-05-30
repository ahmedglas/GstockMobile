package com.example.gstock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Admin extends AppCompatActivity {

    Button _btnComposant,_btnMembre,_btnSuivi,_btnEmprunt,_btnFamille;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        _btnComposant = (Button) findViewById(R.id.btnComposant);
        _btnMembre = (Button) findViewById(R.id.btnMembre);
        _btnSuivi = (Button) findViewById(R.id.btnSuivi);
        _btnEmprunt= (Button) findViewById(R.id.btnEmprunt);
        _btnFamille = (Button) findViewById(R.id.btnFamille);
        db = openOrCreateDatabase("Stock", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Membre (id int primary key,nom varchar ,prenom varchar ,address varchar,tel1 int, tel2 int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Composant (Code int primary key,NomC varchar  ,Empruntable int,Quantite int,id_Famille int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Emprunt (id int primary key,DateE varchar, DateR varchar,Etat varchar,id_Membre int ,id_Composant int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS FamilleComposant (id int primary key , nomF varchar);");
        //db.execSQL("CREATE TABLE IF NOT EXISTS ADMIN (login varchar primary key, password varchar);");

        _btnComposant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Composants.class);
                startActivity(i);
            }

        });
        _btnMembre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AjoutMembre.class);
                startActivity(i);
            }

        });
        _btnSuivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), SuiviEmprunt.class);
                startActivity(i);
            }

        });
        _btnEmprunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Emprunt.class);
                startActivity(i);
            }

        });
        _btnFamille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), FamilleComposant.class);
                startActivity(i);
            }

        });
    }
}