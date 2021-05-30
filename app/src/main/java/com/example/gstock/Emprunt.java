package com.example.gstock;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Emprunt extends AppCompatActivity {
    EditText _txtDateE, _txtDateR, _txtEtat, _txtId,_txtRechercheEmprunt;
    Button _btnAdd, _btnUpdate, _btnDelete, _btnSave, _btnCancel;
    SQLiteDatabase db;
    ImageButton _btnRecherche;
    Button _btnFirst,_btnPrevious,_btnNext,_btnLast;
    LinearLayout layNaviguer,layRecherche;
    int op = 1;
    Cursor cur,cur1,cur2;
    String x;
    Spinner _spnComposant,_spnMembre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprunt);
        layNaviguer = (LinearLayout) findViewById(R.id.layNaviguer);
        layRecherche = (LinearLayout) findViewById(R.id.layRecherche);
        _txtDateE = (EditText) findViewById(R.id.txtDateE);
        _txtDateR = (EditText) findViewById(R.id.txtDateR);
        _txtEtat = (EditText) findViewById(R.id.txtEtat);
        _txtId = (EditText) findViewById(R.id.txtId);
        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _btnDelete = (Button) findViewById(R.id.btnDelete);
        _btnFirst = (Button) findViewById(R.id.btnFirst);
        _btnPrevious = (Button) findViewById(R.id.btnPrevious);
        _btnNext = (Button) findViewById(R.id.btnNext);
        _btnLast = (Button) findViewById(R.id.btnLast);
        _spnComposant= (Spinner) findViewById(R.id.spnComposant);
        _spnMembre = (Spinner) findViewById(R.id.spnMembre);
        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnSave = (Button) findViewById(R.id.btnSave);
        _btnRecherche = (ImageButton) findViewById(R.id.btnRecherche);
        _txtRechercheEmprunt= (EditText) findViewById(R.id.txtRechercheEmprunt);
        ArrayList<String>list=new ArrayList<String>();
        ArrayList<String>list1=new ArrayList<String>();

        db = openOrCreateDatabase("Stock", MODE_PRIVATE, null);
        // Création de la table "users"
        db.execSQL("CREATE TABLE IF NOT EXISTS Emprunt (id int primary key,DateE varchar, DateR varchar,Etat varchar,id_Membre int ,id_Composant int, FOREIGN KEY (id_Membre) REFERENCES Membre(id), FOREIGN KEY (id_Composant) REFERENCES Composant(Code));");
        cur1= db.rawQuery("SELECT  * FROM Composant",null);
        cur1.moveToFirst();
        //if (cur1.moveToNext())  list.add(cur1.getString(1));
        while (cur1.moveToNext())
        {
            list.add(cur1.getString(0));
        }


        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        _spnComposant.setAdapter(adapt);
        _spnComposant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cur2= db.rawQuery("SELECT  * FROM Membre",null);
        cur2.moveToFirst();
        //if (cur2.moveToNext())  list1.add(cur2.getString(1));
        while (cur2.moveToNext())
        {
            list1.add(cur2.getString(0));
        }


        ArrayAdapter<String> adapt1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list1);
        _spnMembre.setAdapter(adapt1);
        _spnMembre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        layNaviguer.setVisibility(View.INVISIBLE);
        _btnSave.setVisibility(View.INVISIBLE);
        _btnCancel.setVisibility(View.INVISIBLE);

        _btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cur = db.rawQuery("select * from Emprunt where id like ?", new String[]{"%" + _txtRechercheEmprunt.getText().toString() + "%"});
                try {
                    cur.moveToFirst();
                    _txtId.setText(cur.getString(0));
                    _txtDateE.setText(cur.getString(1));
                    _txtDateR.setText(cur.getString(2));
                    _txtEtat.setText(cur.getString(3));
                    if (cur.getCount() == 1){
                        layNaviguer.setVisibility(View.INVISIBLE);
                    } else {
                        layNaviguer.setVisibility(View.VISIBLE);
                        _btnPrevious.setEnabled(false);
                        _btnNext.setEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"aucun résultat bla.",Toast.LENGTH_SHORT).show();
                    _txtId.setText("");
                    _txtDateE.setText("");
                    _txtDateR.setText("");
                    _txtEtat.setText("");
                    layNaviguer.setVisibility(View.INVISIBLE);
                }
            }
        });
        _btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    cur.moveToFirst();
                    _txtId.setText(cur.getString(0));
                    _txtDateE.setText(cur.getString(1));
                    _txtDateR.setText(cur.getString(2));
                    _txtEtat.setText(cur.getString(3));
                    _btnPrevious.setEnabled(false);
                    _btnNext.setEnabled(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"aucun composant est existant.",Toast.LENGTH_SHORT).show();

                }
            }
        });
        _btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    cur.moveToLast();
                    _txtId.setText(cur.getString(0));
                    _txtDateE.setText(cur.getString(1));
                    _txtDateR.setText(cur.getString(2));
                    _txtEtat.setText(cur.getString(3));
                    _btnPrevious.setEnabled(true);
                    _btnNext.setEnabled(false);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"aucun composant n'est existant.",Toast.LENGTH_SHORT).show();

                }
            }
        });

        _btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    cur.moveToNext();
                    _txtId.setText(cur.getString(0));
                    _txtDateE.setText(cur.getString(1));
                    _txtDateR.setText(cur.getString(2));
                    _txtEtat.setText(cur.getString(3));
                    _btnPrevious.setEnabled(true);
                    if (cur.isLast()){
                        _btnNext.setEnabled(false);
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }
            }
        });

        _btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    cur.moveToPrevious();
                    _txtId.setText(cur.getString(0));
                    _txtDateE.setText(cur.getString(1));
                    _txtDateR.setText(cur.getString(2));
                    _txtEtat.setText(cur.getString(3));
                    _btnNext.setEnabled(true);
                    if (cur.isFirst()){
                        _btnPrevious.setEnabled(false);
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }
            }
        });

        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 1;
                _txtId.setText("");
                _txtDateE.setText("");
                _txtDateR.setText("");
                _txtEtat.setText("");
                _btnSave.setVisibility(View.VISIBLE);
                _btnUpdate.setVisibility(View.INVISIBLE);
                _btnDelete.setVisibility(View.INVISIBLE);
                _btnAdd.setEnabled(false);
                _btnCancel = (Button) findViewById(R.id.btnCancel);
                layNaviguer.setVisibility(View.INVISIBLE);
                layRecherche.setVisibility(View.INVISIBLE);

            }
        });
        _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // tester si les champs ne sont pas vides
                try {
                    x = cur.getString(0);
                    op = 2;

                    _btnSave.setVisibility(View.VISIBLE);
                    _btnCancel.setVisibility(View.VISIBLE);

                    _btnDelete.setVisibility(View.INVISIBLE);
                    _btnUpdate.setEnabled(false);
                    _btnAdd.setVisibility(View.INVISIBLE);
                    layNaviguer.setVisibility(View.INVISIBLE);
                    layRecherche.setVisibility(View.INVISIBLE);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Sélectionnez une emprunt puis appyuer sur le bouton de modification", Toast.LENGTH_SHORT).show();
                }

            }
        });
        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (op == 1) {
                    // insertion
                    db.execSQL("insert into Emprunt (id,DateE,DateR,Etat,id_Membre  ,id_Composant) values (?,?,?,?,?,?);", new String[]{_txtId.getText().toString(), _txtDateE.getText().toString(), _txtDateR.getText().toString(), _txtEtat.getText().toString(),_spnMembre.getSelectedItem().toString(),_spnComposant.getSelectedItem().toString()});
                } else if (op == 2) {
                    // Mise à jour
                    db.execSQL("update Emprunt set  DateE=?, DateR=?,Etat=?,id_Membre=?  ,id_Composant=? where id=?;", new String[]{_txtDateE.getText().toString(), _txtDateR.getText().toString(), _txtEtat.getText().toString(),_spnMembre.getSelectedItem().toString(),_spnComposant.getSelectedItem().toString(), x});
                }

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);
                _btnRecherche.performClick();
                layRecherche.setVisibility(View.VISIBLE);
            }
        });
        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 0;

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);
                layRecherche.setVisibility(View.VISIBLE);

            }
        });
        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    x=  cur.getString(0);
                    AlertDialog dial = MesOptions();
                    dial.show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Sélectionner un compte puis appyuer sur le bouton de suppresssion",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });



    }

    private AlertDialog MesOptions(){
        AlertDialog MiDia = new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Est ce que vous voulez supprimer ce compte?")
                .setIcon(R.drawable.validate)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.execSQL("delete from Emprunt where id=?;",new String[] {cur.getString(0)});
                        _txtId.setText("");
                        _txtDateE.setText("");
                        _txtDateR.setText("");
                        _txtEtat.setText("");
                        layNaviguer.setVisibility(View.INVISIBLE);
                        cur.close();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        return MiDia;


    }

}