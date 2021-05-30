package com.example.gstock;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FamilleComposant extends AppCompatActivity {
    EditText _txtNomF, _txtId;
    Button _btnAdd, _btnCancel,_btnSave,_btnDelete;
    SQLiteDatabase db;
    int op = 1;
    Cursor cur;
    String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famille_composant);
        _txtNomF = (EditText) findViewById(R.id.txtDateR);
        _txtId = (EditText) findViewById(R.id.txtId);
        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnSave = (Button) findViewById(R.id.btnSave);
        _btnDelete = (Button) findViewById(R.id.btnDelete);
        db = openOrCreateDatabase("Stock", MODE_PRIVATE, null);
        // Création de la table "users"
        db.execSQL("CREATE TABLE IF NOT EXISTS FamilleComposant (id int primary key , nomF varchar);");
        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 1;
                _txtNomF.setText("");
                _btnSave.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(false);
                _btnCancel = (Button) findViewById(R.id.btnCancel);
            }
        });
        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (op == 1) {
                    // insertion
                    db.execSQL("insert into FamilleComposant (id,nomF) values (?,?);", new String[]{_txtId.getText().toString(), _txtNomF.getText().toString()});
                }
                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
            }
        });
        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    x = cur.getString(0);
                    db.execSQL("delete from FamilleComposant where id=?;",new String[] {cur.getString(0)});
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Sélectionner une famille composant puis appyuer sur le bouton de suppresssion", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 0;

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);

            }
        });

    }}