package com.example.gstock;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AjoutMembre extends AppCompatActivity {
    EditText _txtNom, _txtPrenom, _txtAdress, _txtTel1,_txtTel2,_txtId,_txtRechercheMembre;
    Button _btnAdd, _btnUpdate, _btnDelete, _btnSave, _btnCancel;
    ImageButton _btnRecherche;
    Button _btnFirst,_btnPrevious,_btnNext,_btnLast;
    LinearLayout layNaviguer,layRecherche;
    SQLiteDatabase db;
    int op = 1;
    Cursor cur,cur1;
    String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_membre);
        _txtId= (EditText) findViewById(R.id.txtId);
        _txtNom = (EditText) findViewById(R.id.txtNom);
        _txtPrenom = (EditText) findViewById(R.id.txtPrenom);
        _txtAdress = (EditText) findViewById(R.id.txtAdresse);
        _txtTel1 = (EditText) findViewById(R.id.txtTel1);
        _txtTel2 = (EditText) findViewById(R.id.txtTel2);
        layNaviguer = (LinearLayout) findViewById(R.id.layNaviguer);
        layRecherche = (LinearLayout) findViewById(R.id.layRecherche);
        _txtRechercheMembre= (EditText) findViewById(R.id.txtRechercheEmprunt);
        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _btnDelete = (Button) findViewById(R.id.btnDelete);
        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnSave = (Button) findViewById(R.id.btnSave);
        _btnFirst = (Button) findViewById(R.id.btnFirst);
        _btnPrevious = (Button) findViewById(R.id.btnPrevious);
        _btnNext = (Button) findViewById(R.id.btnNext);
        _btnLast = (Button) findViewById(R.id.btnLast);
        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnRecherche = (ImageButton) findViewById(R.id.btnRecherche);
        db = openOrCreateDatabase("Stock", MODE_PRIVATE, null);
        // Création de la table "users"
        db.execSQL("CREATE TABLE IF NOT EXISTS Membre (id int primary key,nom varchar ,prenom varchar ,address varchar,tel1 int, tel2 int);");
        layNaviguer.setVisibility(View.INVISIBLE);
        _btnSave.setVisibility(View.INVISIBLE);
        _btnCancel.setVisibility(View.INVISIBLE);
        _btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cur = db.rawQuery("select * from Membre where nom like ?", new String[]{"%" + _txtRechercheMembre.getText().toString() + "%"});
                try {
                    cur.moveToFirst();
                    _txtId.setText(cur.getString(0));
                    _txtNom.setText(cur.getString(1));
                    _txtPrenom.setText(cur.getString(2));
                    _txtAdress.setText(cur.getString(3));
                    _txtTel1.setText(cur.getString(4));
                    _txtTel2.setText(cur.getString(5));
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
                    _txtNom.setText("");
                    _txtPrenom.setText("");
                    _txtAdress.setText("");
                    _txtTel1.setText("");
                    _txtTel2.setText("");
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
                    _txtNom.setText(cur.getString(1));
                    _txtPrenom.setText(cur.getString(2));
                    _txtAdress.setText(cur.getString(3));
                    _txtTel1.setText(cur.getString(4));
                    _txtTel2.setText(cur.getString(5));
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
                    _txtNom.setText(cur.getString(1));
                    _txtPrenom.setText(cur.getString(2));
                    _txtAdress.setText(cur.getString(3));
                    _txtTel1.setText(cur.getString(4));
                    _txtTel2.setText(cur.getString(5));
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
                    _txtNom.setText(cur.getString(1));
                    _txtPrenom.setText(cur.getString(2));
                    _txtAdress.setText(cur.getString(3));
                    _txtTel1.setText(cur.getString(4));
                    _txtTel2.setText(cur.getString(5));
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
                    _txtNom.setText(cur.getString(1));
                    _txtPrenom.setText(cur.getString(2));
                    _txtAdress.setText(cur.getString(3));
                    _txtTel1.setText(cur.getString(4));
                    _txtTel2.setText(cur.getString(5));
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
                _txtNom.setText("");
                _txtPrenom.setText("");
                _txtAdress.setText("");
                _txtTel1.setText("");
                _txtTel2.setText("");
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
                    Toast.makeText(getApplicationContext(), "Sélectionnez un membre puis appyuer sur le bouton de modification", Toast.LENGTH_SHORT).show();
                }

            }
        });

        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (op == 1) {
                    // insertion
                    db.execSQL("insert into Membre (id,nom,prenom,address,tel1,tel2) values (?,?,?,?,?,?);", new String[]{_txtId.getText().toString(), _txtNom.getText().toString(), _txtPrenom.getText().toString(), _txtAdress.getText().toString(),_txtTel1.getText().toString(),_txtTel2.getText().toString()});
                } else if (op == 2) {
                    // Mise à jour
                    db.execSQL("update Membre set  nom=?, prenom=?, address=?, tel1=?, tel2=? where id=?;", new String[]{_txtNom.getText().toString(), _txtPrenom.getText().toString(), _txtAdress.getText().toString(),_txtTel1.getText().toString(),_txtTel2.getText().toString(), x});
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
                        db.execSQL("delete from Membre where id=?;",new String[] {cur.getString(0)});
                        _txtId.setText("");
                        _txtNom.setText("");
                        _txtPrenom.setText("");
                        _txtAdress.setText("");
                        _txtTel1.setText("");
                        _txtTel2.setText("");
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
