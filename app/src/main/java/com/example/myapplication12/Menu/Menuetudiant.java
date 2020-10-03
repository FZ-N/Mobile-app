package com.example.myapplication12.Menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.Listcours;
import com.google.gson.Gson;

public class Menuetudiant extends AppCompatActivity {

    private Button button1,button2,button3,button4,button5;
    private ImageView vbutton1, vbutton2, vbutton3, vbutton4, vbutton5;
    private LinearLayout l4,l5;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        button1=(Button) findViewById(R.id.Messages);
        button2=(Button) findViewById(R.id.Evenements);
        button3=(Button) findViewById(R.id.Scolarite);
        button4=(Button) findViewById(R.id.gestionprof);
        button5=(Button) findViewById(R.id.gestionetud);
        vbutton1=(ImageView) findViewById(R.id.mes);
        vbutton2=(ImageView) findViewById(R.id.eve);
        vbutton3=(ImageView) findViewById(R.id.sco);
        vbutton4=(ImageView) findViewById(R.id.pro);
        vbutton5=(ImageView) findViewById(R.id.etu);

        l4=(LinearLayout) findViewById(R.id.gestionprofl);
        l5=(LinearLayout) findViewById(R.id.gestionetudl);

        //nomuser=(TextView) findViewById(R.id.nomcurentuser);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        final Personne p1 = gson.fromJson(json, Personne.class);
        name  =p1.getNom();


        if (p1.getType().equals("Etudiant"))

            actionBar.setTitle("Bienvenue "+name);
        else
        actionBar.setTitle("Bienvenue Professeur "+name);

        if(!"Chef".equals(p1.getType())){
            l4.setVisibility(View.INVISIBLE);
            l5.setVisibility(View.INVISIBLE);
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listmessage.class);
                startActivity(in);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listevent.class);
                startActivity(in);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listcours.class);
                startActivity(in);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listprof.class);
                startActivity(in);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listetudiant.class);
                startActivity(in);
            }
        });

        vbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listmessage.class);
                startActivity(in);
            }
        });

        vbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listevent.class);
                startActivity(in);
            }
        });
        vbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listcours.class);
                startActivity(in);
            }
        });
        vbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listprof.class);
                startActivity(in);
            }
        });
        vbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listetudiant.class);
                startActivity(in);
            }
        });
    }
}
