package com.example.myapplication12.Scolarité;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.myapplication12.Gestion_etudiant_prof.Addetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Addprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Messagerie.Addmessage;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Cours;
import com.example.myapplication12.Model.Matiere;
import com.example.myapplication12.Model.Module;
import com.example.myapplication12.Model.Professeur;
import com.example.myapplication12.R;
//import com.example.myapplication12.Services.Methodes_cours;
import com.example.myapplication12.Services.Methodes_event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Addmodule extends AppCompatActivity {

    Menu menuitem;
    private EditText nom1, matiere1, date1, per1, id1,semestre1,periode1;
    private TextView text3;
    private ImageView i1, b1;
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    private ImageView scolarete1, messages1, evenement1;

    private final int PICK_IMAGE_REQUEST = 71;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmodule);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Ajouter un Module");


        scolarete1 = (ImageView) findViewById(R.id.Scolarite8);
        messages1 = (ImageView) findViewById(R.id.messages8);
        evenement1 = (ImageView) findViewById(R.id.evenement8);
        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Addmodule.this, Listcours.class);
                startActivity(in);
            }
        });
        messages1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Addmodule.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Addmodule.this, Listevent.class);
                startActivity(in);
            }
        });

        nom1 = (EditText) findViewById(R.id.module_nom2);
        semestre1 = (EditText) findViewById(R.id.module_semestre2);
        periode1 = (EditText) findViewById(R.id.module_periode2);

        //matiere1=(EditText) findViewById(R.id.dis);
        //tele1=(EditText) findViewById(R.id.phone);
        //pass1=(EditText) findViewById(R.id.password);
        text3 = (TextView) findViewById(R.id.ajoutermodule);


        storageReference = FirebaseStorage.getInstance().getReference();




        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nom = String.valueOf(nom1.getText());
                String semestre = String.valueOf(semestre1.getText());
                String periode = String.valueOf(periode1.getText());
                Date date_cours = null;
                Date currentTime = Calendar.getInstance().getTime();


                Module module =new Module();
                module.setNom(nom);
                module.setDate(currentTime);
                module.setPeriode(semestre);
                module.setSemestre(periode);

               Methodes_cours.creatmodule(module);  //faire de module

                Toast.makeText(getApplicationContext(), "Votre Module a été ajouter avec succe", Toast.LENGTH_LONG).show();

                Intent in = new Intent(Addmodule.this, Listmodules.class);
                startActivity(in);
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

        //itm8.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent in = new Intent(Addmodule.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Addmodule.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Addmodule.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Addmodule.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Addmodule.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Addmodule.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Addmodule.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(Addmodule.this, Addprof.class);
                startActivity(in8);
                break;
            case R.id.item9:
                Intent in9 = new Intent(Addmodule.this, Emploit.class);
                startActivity(in9);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Addmodule.this, Login.class);
                startActivity(in10);
                break;
            case R.id.item88:
                Intent in11 = new Intent(Addmodule.this, Menuetudiant.class);
                startActivity(in11);
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
