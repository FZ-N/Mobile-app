package com.example.myapplication12.Gestion_etudiant_prof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Messagerie.Addmessage;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.AddEmploit;
import com.example.myapplication12.Scolarité.Addcours;
import com.example.myapplication12.Scolarité.Emploit;
import com.example.myapplication12.Scolarité.Listcours;
import com.example.myapplication12.Services.Methodes_personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Editetudiant extends AppCompatActivity {

    Menu menuitem;
    private EditText nom2, email2, tele2, mot_de_passe2;
    private TextView nom1;
    private ImageView save;
    private ImageView scolarete1, messages1, evenement1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulerprof);
        final ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        //actionBar.setTitle("Les cours");


        scolarete1 = (ImageView) findViewById(R.id.Scolarite14);
        messages1 = (ImageView) findViewById(R.id.messages14);
        evenement1 = (ImageView) findViewById(R.id.evenement14);
        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Editetudiant.this, Listcours.class);
                startActivity(in);
            }
        });
        messages1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Editetudiant.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Editetudiant.this, Listevent.class);
                startActivity(in);
            }
        });
        //nom1 = (TextView) findViewById(R.id.nom_prof);
        nom2 = (EditText) findViewById(R.id.nom_prof2);
        email2 = (EditText) findViewById(R.id.gmail_prof2);
        tele2 = (EditText) findViewById(R.id.telephone_prof2);
        save = (ImageView) findViewById(R.id.save_prof);
        mot_de_passe2 = (EditText) findViewById(R.id.mot_de_passe2);


        Bundle extras = getIntent().getExtras();
        String nom = extras.getString("nom_prof_etudiant");
        Methodes_personne.Getuserbynom(nom).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String Type = null;
                    String nomuser = null;
                    String email = null;
                    String tele = null;
                    String mot_de_passe = null;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Personne p = document.toObject(Personne.class);
                        //Type = p.getType();
                        nomuser = p.getNom();
                        email = p.getEmail();
                        tele = p.getNum_telephone();
                        mot_de_passe = p.getMot_de_passe();

                        //nom1.setText(nomuser);
                        actionBar.setTitle(nomuser);
                        nom2.setText(nomuser);
                        email2.setText(email);
                        tele2.setText(tele);
                        mot_de_passe2.setText(mot_de_passe);

                    }

                }
            }

        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom11 = actionBar.getTitle().toString();
                String nom22 = nom2.getText().toString();
                String email22 = email2.getText().toString();
                String tele22 = tele2.getText().toString();
                String mot_de_passe22 = mot_de_passe2.getText().toString();

                final Personne p = new Personne(nom22, mot_de_passe22, email22, tele22, "Etudiant");
                //Methodes_personne.updateUser(nom11,p);
                Methodes_personne.updateUser(nom11, p).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //Personne p = new Personne();
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String idd = document.getId();
                                Methodes_personne.getUsersCollection().document(idd).update("nom", p.getNom(),"mot_de_passe",p.getMot_de_passe(), "email", p.getEmail(), "num_telephone", p.getNum_telephone());
                                Intent in = new Intent(Editetudiant.this, Listetudiant.class);
                                startActivity(in);
                                Toast.makeText(getApplicationContext(), "Votre modification a été enregistré avec succès", Toast.LENGTH_SHORT).show();
                                //p = document.toObject(Personne.class);
                                //ps.add(p);
                                //System.out.println("le nom ="+p.getNom());
                            }
                        }
                    }
                });
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exm_menu,menu);

        menuitem=menu;
        MenuItem itm1 = menuitem.findItem(R.id.item1);
        MenuItem itm2 = menuitem.findItem(R.id.item2);
        MenuItem itm3 = menuitem.findItem(R.id.item3);
        MenuItem itm4 = menuitem.findItem(R.id.item4);
        MenuItem itm5 = menuitem.findItem(R.id.item5);
        MenuItem itm6 = menuitem.findItem(R.id.item6);
        MenuItem itm7 = menuitem.findItem(R.id.item7);
        MenuItem itm8 = menuitem.findItem(R.id.item8);
        MenuItem itm9 = menuitem.findItem(R.id.item9);
        MenuItem itm10 = menuitem.findItem(R.id.item10);

        MenuItem itm88 = menuitem.findItem(R.id.item88);
        MenuItem itm99 = menuitem.findItem(R.id.item99);
        MenuItem itm98 = menuitem.findItem(R.id.item99);

        itm1.setVisible(false);
        itm2.setVisible(false);
        itm3.setVisible(false);
        itm4.setVisible(false);
        itm5.setVisible(false);
        itm6.setVisible(false);
        itm7.setVisible(false);
        itm8.setVisible(false);
        itm9.setVisible(false);


        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Intent in = new Intent(Editetudiant.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Editetudiant.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Editetudiant.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Editetudiant.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Editetudiant.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Editetudiant.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Editetudiant.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(Editetudiant.this, Addprof.class);
                startActivity(in8);
                break;
            case R.id.item9:
                Intent in9 = new Intent(Editetudiant.this, Emploit.class);
                startActivity(in9);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Editetudiant.this, Login.class);
                startActivity(in10);
                break;
            case R.id.item88:
                Intent in11 = new Intent(Editetudiant.this, Menuetudiant.class);
                startActivity(in11);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
