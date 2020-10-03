package com.example.myapplication12.Scolarité;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Gestion_etudiant_prof.Addetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Messagerie.Addmessage;
import com.example.myapplication12.R;
import com.google.firebase.storage.StorageReference;

public class em extends AppCompatActivity {

    Menu menuitem;


    StorageReference storageReference;
    private Button pr1, dm1, tr1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuscolarite);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Choisissez une année");


        pr1 = (Button) findViewById(R.id.pr);
        dm1 = (Button)  findViewById(R.id.dm);
        tr1 = (Button)  findViewById(R.id.tr);
        pr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(em.this, Emploit1.class);
                startActivity(in);
            }
        });
       dm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(em.this, Emploit2.class);
                startActivity(in);
            }
        });
        tr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(em.this, Emploit3.class);
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
        MenuItem itm88 = menuitem.findItem(R.id.item88);
        MenuItem itm9 = menuitem.findItem(R.id.item9);
        MenuItem itm10 = menuitem.findItem(R.id.item10);


        itm1.setVisible(false);
        itm2.setVisible(false);
        itm3.setVisible(false);
        itm4.setVisible(false);
        itm6.setVisible(false);
        itm7.setVisible(false);
        itm8.setVisible(false);
        itm9.setVisible(false);


        itm5.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent in = new Intent(em.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(em.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(em.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(em.this, em.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(em.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(em.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(em.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(em.this, Emploit.class);
                startActivity(in8);
                break;
            case R.id.item88:
                Intent in11 = new Intent(em.this, Menuetudiant.class);
                startActivity(in11);
                break;
            case R.id.item10:
                Intent in10 = new Intent(em.this, Login.class);
                startActivity(in10);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

