package com.example.gstock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SuiviEmprunt extends AppCompatActivity {
    SQLiteDatabase db;
    Cursor cur,cur1,cur2;
    RecyclerView LstComposant;
    List<String> list;
    List<String> listMemnbre;
    List<String> listComposant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi_emprunt);
        db = openOrCreateDatabase("Stock", MODE_PRIVATE, null);
        ArrayList<String> mots = new ArrayList<>();
        LstComposant= findViewById(R.id.LstComposant);
        list= new ArrayList<>();
        listMemnbre= new ArrayList<>();
        listComposant= new ArrayList<>();


        //db.execSQL("CREATE TABLE IF NOT EXISTS Membre (id int primary key,nom varchar ,prenom varchar ,address varchar,tel1 int, tel2 int);");
        //db.execSQL("CREATE TABLE IF NOT EXISTS Composant (Code int primary key,NomC varchar  ,Empruntable int,Quantite int,id_Famille int);");
       // db.execSQL("CREATE TABLE IF NOT EXISTS Emprunt (id int primary key,DateE varchar, DateR varchar,Etat varchar,id_Membre int ,id_Composant int);");
        //cur2 = db.rawQuery("select Composant.nomC, Membre.nom,Membre.tel1 from Composant, Membre,Emprunt where  id_Composant = Composants.CodeC and id_Membre = Membre.id and Emprunt.DateR > (SELECT strftime('%d/%m/%Y',date('now')))",new String[]{});

        cur2 = db.rawQuery("select id, id_Membre, id_Composant, DateR from Emprunt where DateR > (SELECT strftime('%d/%m/%Y',date('now')))"  , new String[]{});
        cur2.moveToFirst();
        if (cur2.moveToNext()) {
            while (cur2.moveToNext())
            {
                Log.d("cursor2", cur2.getString(0) +"      "+ cur2.getInt(1) + " "+ cur2.getInt(2));
                //cur1 = db.rawQuery("select nom  ,tel1 from Membre where id = ?" , new String[]{cur2.getString(1)});
                cur1 = db.rawQuery("select id, nom  ,tel1 from Membre" , null);
                cur1.moveToFirst();
                cur1.getCount();
                    do {
                        Log.d("cursor1", cur1.getString(0) + "  "+ cur1.getString(1));
                        if (cur1.getInt(0) == cur2.getInt(1)) {
                            Log.d("cursor1", cur1.getString(0) + "  "+ cur1.getString(1));
                            listMemnbre.add(cur1.getString(0) + ";"+ cur1.getString(1)+ ";"+ cur1.getString(2));
                        }
                    } while (cur1.moveToNext());

                cur = db.rawQuery("select Code, nomC from Composant " , new String[]{});
                cur.moveToFirst();
                    while (cur.moveToNext())
                    {
                        if (cur.getInt(0) == cur2.getInt(2)) {
                            Log.d("cursor0", cur.getString(0) + "  "+ cur.getString(1));
                            listComposant.add(cur.getString(1));
                        }
                    }

                //list.add(cur2.getString(1));
            }
        }

        List<SuiviEmpruntModel> newList = new ArrayList<>();

        if (!listMemnbre.isEmpty() && !listComposant.isEmpty()) {
            for (int i = 0; i < listMemnbre.size(); i++) {
                SuiviEmpruntModel suiviEmpruntModel = new SuiviEmpruntModel();
                suiviEmpruntModel.nomMembre = listMemnbre.get(i).split(";")[1];
                suiviEmpruntModel.tel = listMemnbre.get(i).split(";")[2];
                suiviEmpruntModel.nomC = listComposant.get(i);
                newList.add(suiviEmpruntModel);
            }
        }
        Log.i("emprunt",listComposant.toString()+"     "+listMemnbre.toString());
        SuiviEmpruntAdapter suiviEmpruntAdapter = new SuiviEmpruntAdapter(newList);
        LstComposant.setLayoutManager(new LinearLayoutManager(this));
        LstComposant.setAdapter(suiviEmpruntAdapter);
    }

}
