package com.example.myapplication12.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Model.Module;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.Methodes_cours;
import com.example.myapplication12.Services.Methodes_personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

public class signup extends AppCompatActivity {

    private TextView text, text2, text3;
    private EditText nom1, email1, tele1, pass1;
    private RadioGroup radioGroup;
    private RadioButton etudiant, prof;
    private Spinner spinner, spinner1, spinner2;
    private LinearLayout layoutetudiant, layoutprof, layoutprof2;

    private Module c = new Module();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        text = (TextView) findViewById(R.id.Login1);
        text2 = (TextView) findViewById(R.id.signup);
        text3 = (TextView) findViewById(R.id.signup1);

        nom1 = (EditText) findViewById(R.id.username);
        email1 = (EditText) findViewById(R.id.mail);
        tele1 = (EditText) findViewById(R.id.phone);
        pass1 = (EditText) findViewById(R.id.password);


        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner2 = (Spinner) findViewById(R.id.spinner2);

        layoutetudiant = (LinearLayout) findViewById(R.id.layoutetudiant);
        layoutprof = (LinearLayout) findViewById(R.id.layoutprof);
        //layoutprof2 = (LinearLayout) findViewById(R.id.layoutprof2);
        //ArrayList<String> arrayList = new ArrayList<>();


        SharedPreferences pref = getApplicationContext().getSharedPreferences("listmodules", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("modules", null);
        Type type = new TypeToken<ArrayList<Module>>() {
        }.getType();
        final ArrayList<Module> list11= gson.fromJson(json, type);
        ArrayList<String> list1=new ArrayList<>();
        list1.add("choisir votre module");
        for(Module m : list11){
            list1.add(m.getNom());
        }
        System.out.println("test pour size initial :" + list1.size());
        //list1.add("notthis");

        ArrayList<String> list = new ArrayList<>();
        list.add("testnothis");


        ArrayList<String> list2 = new ArrayList<>();
        list1.add("choisir votre année");
        list2.add("1er année");
        list2.add("2eme année");
        list2.add("3eme année");

        ArrayList<String> list3 = new ArrayList<>();
        list3.add("3");
        list3.add("4");
        list3.add("5");


        /*arrayList.add("JAVA");
        arrayList.add("ANDROID");
        arrayList.add("C Language");
        arrayList.add("CPP Language");
        arrayList.add("Go Language");
        arrayList.add("AVN SYSTEMS");*/

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);



        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.etudiantx) {
                    layoutetudiant.setVisibility(View.VISIBLE);
                    layoutprof.setVisibility(View.INVISIBLE);
                    layoutprof2.setVisibility(View.INVISIBLE);
                } else {
                    if (checkedId == R.id.profx) {
                        layoutprof.setVisibility(View.VISIBLE);
                        layoutprof2.setVisibility(View.VISIBLE);
                        layoutetudiant.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });*/


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.etudiantx) {
                    layoutetudiant.setVisibility(View.VISIBLE);
                    layoutprof.setVisibility(View.INVISIBLE);
                    //layoutprof2.setVisibility(View.INVISIBLE);
                    System.out.println("etudiantetudaint");

                    spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            final String annee = parent.getItemAtPosition(position).toString();
                            text3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    String nom = String.valueOf(nom1.getText());
                                    String email = String.valueOf(email1.getText());
                                    String tele = String.valueOf(tele1.getText());
                                    String pass = String.valueOf(pass1.getText());
                                    String type = "Etudiant";


                                    Personne p = new Personne(nom, pass, email, tele, type);
                                    p.setAnnee(annee);
                                    //p.setSemestre(semestre);

                                    Methodes_personne.createUser(p);
                                    Toast.makeText(getApplicationContext(), "Votre compte a été créé avec succès !", Toast.LENGTH_LONG).show();

                                    Intent in = new Intent(signup.this, Login.class);
                                    startActivity(in);
                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if (checkedId == R.id.profx) {

                    layoutprof.setVisibility(View.VISIBLE);
                    //layoutprof2.setVisibility(View.VISIBLE);
                    layoutetudiant.setVisibility(View.INVISIBLE);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String module_nom = parent.getItemAtPosition(position).toString();
                            final Module module = new Module();
                            module.setNom(module_nom);
                            String semestre=null;
                            for(Module m :list11){
                                if(m.getNom().equals(module_nom)){
                                    semestre=m.getSemestre();
                                }
                            }



                            final String finalSemestre1 = semestre;
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
                                            p.setSemestre(finalSemestre1);

                                            Methodes_personne.createUser(p);
                                            Toast.makeText(getApplicationContext(), "Votre inscription a été accpeter avec succe", Toast.LENGTH_LONG).show();

                                            Intent in = new Intent(signup.this, Login.class);
                                            startActivity(in);
                                        }
                                    });
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                }
            }
        });


    }








        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.etudiantx) {

                    text3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            String nom = String.valueOf(nom1.getText());
                            String email = String.valueOf(email1.getText());
                            String tele = String.valueOf(tele1.getText());
                            String pass = String.valueOf(pass1.getText());
                            String type = "Etudiant";

                            Personne p = new Personne(nom, pass, email, tele, type);
                            Methodes_personne.createUser(p);
                            Toast.makeText(getApplicationContext(), "Votre inscription a été accpeter avec succe", Toast.LENGTH_LONG).show();

                            Intent in = new Intent(signup.this, Login.class);
                            startActivity(in);
                        }
                    });
                }
                if (checkedId == R.id.profx) {

                    text3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            String nom = String.valueOf(nom1.getText());
                            String email = String.valueOf(email1.getText());
                            String tele = String.valueOf(tele1.getText());
                            String pass = String.valueOf(pass1.getText());
                            String type = "Prof";

                            Personne p = new Personne(nom, pass, email, tele, type);
                            Methodes_personne.createUser(p);
                            Toast.makeText(getApplicationContext(), "Votre inscription a été accpeter avec succe", Toast.LENGTH_LONG).show();

                            Intent in = new Intent(signup.this, Login.class);
                            startActivity(in);
                        }
                    });
                }
            }
        });*/

        /*text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nom = String.valueOf(nom1.getText());
                String email = String.valueOf(email1.getText());
                String tele = String.valueOf(tele1.getText());
                String pass = String.valueOf(pass1.getText());
                String type = "Etudiant";

                Personne p = new Personne(nom, pass, email, tele, type);
                Methodes_personne.createUser(p);
                Toast.makeText(getApplicationContext(), "Votre inscription a été accpeter avec succe", Toast.LENGTH_LONG).show();

                Intent in = new Intent(signup.this, Login.class);
                startActivity(in);
            }
        });*/


}
