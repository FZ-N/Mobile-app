package com.example.myapplication12.Evenement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication12.Gestion_etudiant_prof.Addetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Addprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Messagerie.Addmessage;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.AddEmploit;
import com.example.myapplication12.Scolarité.Addcours;
import com.example.myapplication12.Scolarité.Emploit;
import com.example.myapplication12.Scolarité.Listcours;
import com.example.myapplication12.Services.Methodes_event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Editevent extends AppCompatActivity {

    Menu menuitem;
    private TextView t1, t2, t3, t4;
    private ImageView scolarete1, messages1, evenement1;
    private TextView nom2, date2, description2;
    private TextView nom1, nombreparticipants;
    private ImageView save, imageeven;
    private DatePicker date22;
    private TimePicker time22;
    private CheckBox interese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even2);
        final ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);


        scolarete1 = (ImageView) findViewById(R.id.Scolarite13);
        messages1 = (ImageView) findViewById(R.id.messages13);
        evenement1 = (ImageView) findViewById(R.id.evenement13);
        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Editevent.this, Listcours.class);
                startActivity(in);
            }
        });
        messages1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Editevent.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Editevent.this, Listevent.class);
                startActivity(in);
            }
        });

        //nom1 = (TextView) findViewById(R.id.even_nom1);
        nom2 = (TextView) findViewById(R.id.even_nom2);
        date2 = (TextView) findViewById(R.id.dateeven2);
        description2 = (TextView) findViewById(R.id.even_description2);
        imageeven = (ImageView) findViewById(R.id.imageevent);
        save = (ImageView) findViewById(R.id.save_even);


        interese = (CheckBox) findViewById(R.id.Jeparticipe);

        nombreparticipants = (TextView) findViewById(R.id.nombreparticipant);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        Personne p1 = gson.fromJson(json, Personne.class);

        if (p1.getType().equals("Prof") || p1.getType().equals("Etudiant")) {
            save.setVisibility(View.INVISIBLE);
        }


        Bundle extras = getIntent().getExtras();
        String nom = extras.getString("nom_even");
        actionBar.setTitle(nom);
        //String nom="test22";
        Methodes_event.Geteventbynom(nom).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String Type = null;
                    String nomevn = null;
                    Date dateevn = null;
                    String description = null;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        System.out.println("bonjour succe");
                        final Evenement e = document.toObject(Evenement.class);
                        //Type = p.getType();
                        nomevn = e.getNom_event();
                        //dateevn=e.getDate_event();
                        description = e.getDescription_event();
                        dateevn = e.getDate_event();
                        int i = e.getPer_participes().size();

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        String date_evn = dateFormat.format(dateevn);

                        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                        StorageReference pathReference = storageReference.child("/events/" + e.getNom_event());
                        File localFile = null;
                        try {
                            localFile = File.createTempFile("images", "jpg");
                        } catch (IOException d) {
                            d.printStackTrace();
                        }

                        final File finalLocalFile = localFile;
                        pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                                imageeven.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                            }
                        });
                        //holder.
                        //...
                        // StorageReference storageReference = FirebaseStorage.getInstance().getReference(ps.get(position).getIma);
                        //Glifrapp.with(context.l)


                        //nom1.setText(nomevn);
                        actionBar.setTitle(nomevn);
                        nom2.setText("Nom :" + nomevn);
                        description2.setText("Description :" + description);
                        date2.setText(date_evn);
                        nombreparticipants.setText("nombre de participant:" + i);

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                        Gson gson = new Gson();
                        String json = pref.getString("personne_c", "");
                        final Personne p1 = gson.fromJson(json, Personne.class);

                        // a voir apres
                        /*if(e.getPer_participes().contains(p1)){
                            interese.setChecked(true);
                            System.out.println("you are interesed xd :");
                        }*/
                        for (Personne p : e.getPer_participes()) {
                            if (p.getNom().equals(p1.getNom())) {
                                interese.setChecked(true);

                            }
                        }


                        interese.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View arg0) {
                                boolean isChecked = interese.isChecked();


                                //final ArrayList<Personne> pers_par = e.getPer_participes();
                                //ArrayList<Personne> pers_par1 =new ArrayList<>();
                                //Personne p1 = new Personne("ahmedxxxyyy", "ahmed", "ahmed@gcom", "060666", "Prof");
                                //for (int i = 0; i < ps.size(); i++) {
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                                Gson gson = new Gson();
                                String json = pref.getString("personne_c", "");
                                final Personne p1 = gson.fromJson(json, Personne.class);

                                //System.out.println("voila size malist:"+pers_par1.size()+"....."+pers_par.size());

                                if (isChecked) {

                                    /*for(Personne p:e.getPer_participes()){
                                        if(!p.getNom().equals(p1.getNom())){
                                            pers_par.add(p1);

                                        }
                                    }*/

                                    Methodes_event.updateevent(e.getNom_event()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            //Personne p = new Personne();
                                            if (task.isSuccessful()) {

                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    ArrayList<Personne> pers_par = e.getPer_participes();
                                                    pers_par.add(p1);


                                                    /*ArrayList<Personne> pers_pa = new ArrayList<>();
                                                    pers_pa.add(pers_par.get(0));
                                                    for (Personne p5 : pers_par) {
                                                        for (Personne p6 : pers_pa) {
                                                            if (!p6.getNom().equals(p5.getNom())) {
                                                                pers_pa.add(p5);
                                                            }
                                                        }
                                                    }*/

                                                    System.out.println("la list des particaipant size="+pers_par.size());
                                                    //System.out.println("verification="+pers_pa.size());
                                                    String idd = document.getId();
                                                    ArrayList<Map<String, Object>> ps = new ArrayList<Map<String, Object>>();
                                                    for (Personne p : pers_par) {
                                                        Map<String, Object> m = new HashMap<>();
                                                        if (p.getType().equals("Prof")) {
                                                            m.put("nom", p.getModule().getNom());

                                                            m.put("cours", null);
                                                            m.put("date", null);
                                                            m.put("periode", null);
                                                            m.put("profs", null);
                                                            m.put("semestre", null);
                                                        } else {
                                                            m = null;
                                                        }
                                                        Map<String, Object> p11 = new HashMap<>();
                                                        p11.put("nom", p.getNom());
                                                        p11.put("mot_de_passe", p.getMot_de_passe());
                                                        p11.put("email", p.getEmail());
                                                        p11.put("num_telephone", p.getNum_telephone());
                                                        p11.put("type", p.getType());
                                                        p11.put("module", m);
                                                        p11.put("semestre", p.getSemestre());
                                                        p11.put("annee", p.getAnnee());

                                                        ps.add(p11);
                                                    }


                                                    Methodes_event.getUsersCollection().document(idd).update("per_participes", ps);


                                                    Toast.makeText(getApplicationContext(), "Vous participez à cet évenement :", Toast.LENGTH_SHORT).show();
                                                    //p = document.toObject(Personne.class);
                                                    //ps.add(p);
                                                    //System.out.println("le nom ="+p.getNom());
                                                }
                                            }
                                        }
                                    });

                                } else {


                                    Methodes_event.updateevent(e.getNom_event()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            //Personne p = new Personne();
                                            if (task.isSuccessful()) {
                                                ArrayList<Personne> pers_par = e.getPer_participes();
                                                //ArrayList<Personne> pers_pa = new ArrayList<>();
                                                for (int i = 0; i <pers_par.size() ; i++) {


                                                    if (pers_par.get(i).getNom().equals(p1.getNom())) {
                                                        pers_par.remove(pers_par.get(i));
                                                    }
                                                }


                                                //System.out.println(pers_pa.size());

                                                ArrayList<Map<String, Object>> ps = new ArrayList<Map<String, Object>>();
                                                for (Personne p : pers_par) {
                                                    Map<String, Object> m = new HashMap<>();
                                                    if (p.getType().equals("Prof")) {
                                                        m.put("nom", p.getModule().getNom());

                                                        m.put("cours", null);
                                                        m.put("date", null);
                                                        m.put("periode", null);
                                                        m.put("profs", null);
                                                        m.put("semestre", null);
                                                    } else {
                                                        m = null;
                                                    }
                                                    Map<String, Object> p1 = new HashMap<>();
                                                    p1.put("nom", p.getNom());
                                                    p1.put("mot_de_passe", p.getMot_de_passe());
                                                    p1.put("email", p.getEmail());
                                                    p1.put("num_telephone", p.getNum_telephone());
                                                    p1.put("type", p.getType());

                                                    p1.put("module", m);
                                                    p1.put("semestre", p.getSemestre());
                                                    p1.put("annee", p.getAnnee());
                                                    ps.add(p1);
                                                }
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    String idd = document.getId();
                                                    Methodes_event.getUsersCollection().document(idd).update("per_participes", ps);

                                                    Toast.makeText(getApplicationContext(), "Vous ne participez plus:", Toast.LENGTH_SHORT).show();
                                                    //p = document.toObject(Personne.class);
                                                    //ps.add(p);
                                                    //System.out.println("le nom ="+p.getNom());
                                                }
                                            }
                                        }
                                    });

                                }


                            }
                        });


                    }

                }
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exm_menu, menu);

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
        switch (item.getItemId()) {
            case R.id.item1:
                Intent in = new Intent(Editevent.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Editevent.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Editevent.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Editevent.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Editevent.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Editevent.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Editevent.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(Editevent.this, Addprof.class);
                startActivity(in8);
                break;
            case R.id.item9:
                Intent in9 = new Intent(Editevent.this, Emploit.class);
                startActivity(in9);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Editevent.this, Login.class);
                startActivity(in10);
                break;
            case R.id.item88:
                Intent in11 = new Intent(Editevent.this, Menuetudiant.class);
                startActivity(in11);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
