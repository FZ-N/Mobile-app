package com.example.myapplication12.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Gestion_etudiant_prof.Addprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Module;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.Addmodule;
import com.example.myapplication12.Scolarité.Listmodules;
import com.example.myapplication12.Scolarité.Methodes_cours;
import com.example.myapplication12.Services.Methodes_personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Login extends AppCompatActivity {

    private TextView text, text2;
    private EditText Nom, Mot_de_passe;
    SharedPreferences pref;

    private Module c = new Module();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        text = (TextView) findViewById(R.id.login);
        text2 = (TextView) findViewById(R.id.signup);

        Nom = (EditText) findViewById(R.id.nom);
        Mot_de_passe = (EditText) findViewById(R.id.mot_de_passe);



        Date date_test =new Date(2020,6,13,8,0,0);
        System.out.println("the day of this date is :"+date_test.getDay());

        final LinkedList<Module> modules = new LinkedList<Module>();
        System.out.println("wwww");
        Methodes_cours.GetAllmodules().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                System.out.println("plplplpl");
                if (task.isSuccessful()) {
                    System.out.println("okokokokok");
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //String idd= document.getId();

                        c = document.toObject(Module.class);
                        modules.add(c);
                        if(c.getNom()!= null){
                            arrayList.add(c.getNom());
                        }



                        System.out.println("le nom =" + c.getNom());
                    }
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("listmodules", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(modules);
                    editor.putString("modules", json);
                    editor.commit();

                } else {
                    System.out.println("nononon");
                }
            }
        });

        text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String nom = String.valueOf(Nom.getText());
                final String mot_de_passe = String.valueOf(Mot_de_passe.getText());
                Methodes_personne.GetUsers(nom, mot_de_passe).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String Type = null;
                            String nomuser = null;

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Personne p = document.toObject(Personne.class);
                                Type = p.getType();
                                nomuser = p.getNom();

                                pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                Gson gson = new Gson();


                                String json = gson.toJson(p);
                                editor.putString("personne_c", json);
                                editor.commit();

                            }
                            if ("Prof".equals(Type) || "Chef".equals(Type) || "Delegue".equals(Type) || "Etudiant".equals(Type)) {
                                //Intent in = new Intent(Login.this, Menuetudiant.class);

                                Intent in = new Intent(Login.this, Menuetudiant.class);
                                //in.putExtra("nom_user", nomuser);
                                startActivity(in);
                            } else {
                                Toast.makeText(getApplicationContext(), "Vos informations sont incorectes", Toast.LENGTH_LONG).show();
                            }

                        } else {

                        }


                        //ArrayList<Personne> ps = Methodes_personne.FindUser(nom,mot_de_passe);

                        /*if (ps.size()==1) {*/



                    /* else {
                        if ("Prof".equals(p.getType())) {
                            Intent in = new Intent(Login.this, Menuprof.class);
                            startActivity(in);
                        } else {
                            Toast.makeText(getApplicationContext(), "Votre informations sont incorectes", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(Login.this, Menuprof.class);
                            startActivity(in);
                        }
                    }*/

                /*Toast.makeText(getApplicationContext(), "Votre informations sont incorectes", Toast.LENGTH_LONG).show();
                Intent in = new Intent(Login.this, Menuprof.class);
                startActivity(in);*/


                    }
                });

            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final Module c = new Module();

                Intent in = new Intent(Login.this, signup.class);
                startActivity(in);
            }
        });


        //meuilleur methode que chaque fois onclicklistenr :


    }
    /*
    @Override
    public void onClick(View v){

        if(v.getId()==R.id.Add_evn){}
        if(){}
    }*/
}
