package com.example.myapplication12.Gestion_etudiant_prof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Messagerie.Addmessage;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Module;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.AddEmploit;
import com.example.myapplication12.Scolarité.Addcours;
import com.example.myapplication12.Scolarité.Emploit;
import com.example.myapplication12.Scolarité.Listcours;
import com.example.myapplication12.Services.Methodes_personne;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Addprof extends AppCompatActivity {

    Menu menuitem;
    private TextView text, text2, text3, title_pe;
    private ImageView scolarete1, messages1, evenement1;
    private EditText nom1, email1, tele1, pass1;
    private Spinner spinner, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_prof);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Ajouter un professeur");


        scolarete1 = (ImageView) findViewById(R.id.Scolarite7);
        messages1 = (ImageView) findViewById(R.id.messages7);
        evenement1 = (ImageView) findViewById(R.id.evenement7);

        spinner = (Spinner) findViewById(R.id.spinner);
        //spinner2 = (Spinner) findViewById(R.id.spinner2);

        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Addprof.this, Listcours.class);
                startActivity(in);
            }
        });
        messages1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Addprof.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Addprof.this, Listevent.class);
                startActivity(in);
            }
        });

        //text=(TextView) findViewById(R.id.Login1);
        //text2=(TextView) findViewById(R.id.signup);
        text3 = (TextView) findViewById(R.id.ajouterprof);

        nom1 = (EditText) findViewById(R.id.nom_proff);
        email1 = (EditText) findViewById(R.id.email_prof);
        tele1 = (EditText) findViewById(R.id.tele_prof);
        pass1 = (EditText) findViewById(R.id.mot_de_passe11);
        //title_pe=(TextView) findViewById(R.id.title_pe);

        //title_pe.setText("Add professeur");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("listmodules", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("modules", null);
        Type type = new TypeToken<ArrayList<Module>>() {
        }.getType();
        final ArrayList<Module> list11 = gson.fromJson(json, type);
        ArrayList<String> list1=new ArrayList<>();
        list1.add("choisir votre module");
        for(Module m : list11){
            list1.add(m.getNom());
        }
        System.out.println("test pour size initial :" + list1.size());
        //list1.add("notthis");

        ArrayList<String> list = new ArrayList<>();
        list.add("testnothis");

        ArrayList<String> list3 = new ArrayList<>();
        list3.add("3");
        list3.add("4");
        list3.add("5");


        System.out.println("size list1 =" + list1.size());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String module_nom = parent.getItemAtPosition(position).toString();

                final Module module = new Module();
                module.setNom(module_nom);
                String semestre=null;
                for(Module m :list11){
                    if(m.getNom().equals(module_nom)){
                        semestre=m.getSemestre();
                    }
                }
                final String finalSemestre = semestre;
                text3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String nom = String.valueOf(nom1.getText());
                        String email = String.valueOf(email1.getText());
                        String tele = String.valueOf(tele1.getText());
                        String pass = String.valueOf(pass1.getText());

                        String type = "Prof";

                        Personne p = new Personne(nom, pass, email, tele, type);
                        p.setModule(module);
                        p.setSemestre(finalSemestre);
                        Methodes_personne.createUser(p);

                        Toast.makeText(getApplicationContext(), "Vous avez ajouté un prof avec succès", Toast.LENGTH_LONG).show();


                        Intent in = new Intent(Addprof.this, Listprof.class);
                        startActivity(in);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exm_menu, menu);

        menuitem = menu;
        MenuItem itm1 = menuitem.findItem(R.id.item1);
        MenuItem itm2 = menuitem.findItem(R.id.item2);
        MenuItem itm3 = menuitem.findItem(R.id.item3);
        MenuItem itm4 = menuitem.findItem(R.id.item4);
        MenuItem itm5 = menuitem.findItem(R.id.item5);
        MenuItem itm6 = menuitem.findItem(R.id.item6);
        MenuItem itm7 = menuitem.findItem(R.id.item7);
        MenuItem itm8 = menuitem.findItem(R.id.item8);
        MenuItem itm88 = menuitem.findItem(R.id.item88);
        MenuItem itm9 = menuitem.findItem(R.id.item9);
        MenuItem itm10 = menuitem.findItem(R.id.item10);

        //MenuItem itm88 = menuitem.findItem(R.id.item88);
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
        itm88.setVisible(false);
        itm9.setVisible(false);


        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent in = new Intent(Addprof.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Addprof.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Addprof.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Addprof.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Addprof.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Addprof.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Addprof.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(Addprof.this, Addprof.class);
                startActivity(in8);
                break;
            case R.id.item9:
                Intent in9 = new Intent(Addprof.this, Emploit.class);
                startActivity(in9);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Addprof.this, Login.class);
                startActivity(in10);
                break;
            case R.id.item88:
                Intent in11 = new Intent(Addprof.this, Menuetudiant.class);
                startActivity(in11);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
