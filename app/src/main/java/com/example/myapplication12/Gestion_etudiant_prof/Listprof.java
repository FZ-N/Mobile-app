package com.example.myapplication12.Gestion_etudiant_prof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.example.myapplication12.Services.MyAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedList;

public class Listprof extends AppCompatActivity implements MyAdapter.OnNoteListener {
    Menu menuitem;
    private TextView mytext;
    private ImageView scolarete1, messages1, evenement1;
    private ImageView addprof;
    public RecyclerView r;
    private  Personne p=new Personne();
    private Object LayoutManager;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    private LinkedList<Personne> ps;
    public static final String MyPREFERENCES = "MyPrefs" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeprofs);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Les professeurs");


        scolarete1 = (ImageView) findViewById(R.id.Scolarite2);
        messages1 = (ImageView) findViewById(R.id.messages2);
        evenement1 = (ImageView) findViewById(R.id.evenement2);
        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Listprof.this, Listcours.class);
                startActivity(in);
            }
        });
        messages1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Listprof.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Listprof.this, Listevent.class);
                startActivity(in);
            }
        });

        r = (RecyclerView) findViewById(R.id.listdesprofs);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final LinkedList<Personne> ps = new LinkedList<Personne>();
        final Context context = this;
        final MyAdapter.OnNoteListener note = this;
        Methodes_personne.GetAllProfs().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //String idd= document.getId();

                        p = document.toObject(Personne.class);
                        ps.add(p);
                        //System.out.println("le nom ="+p.getNom());
                    }
                    /*SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(nome,n)
                    editor.commit();*/

                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapter myAdapter = new MyAdapter(ps, context, note);
                    r.setAdapter(myAdapter);
                    //r.setHasFixedSize(true);
                    //LayoutManager = new LinearLayoutManager(this);
                    //System.out.println("le nom ="+p.getNom());


                } else {

                }
            }


        });


        Methodes_personne.GetAllProfs().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        p = document.toObject(Personne.class);
                        ps.add(p);

                    }



                } else {

                }
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
        //itm8.setVisible(false);
        itm9.setVisible(false);



        itm8.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Intent in = new Intent(Listprof.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Listprof.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Listprof.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Listprof.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Listprof.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Listprof.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Listprof.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:

                Intent in8 = new Intent(Listprof.this, Addprof.class);
                startActivity(in8);
                break;


            case R.id.item10:
                Intent in10 = new Intent(Listprof.this, Login.class);
                startActivity(in10);
                break;
            case R.id.item88:
                Intent in11 = new Intent(Listprof.this, Menuetudiant.class);
                startActivity(in11);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnNoteClick(int position) {



        /*mytext=(TextView)findViewById(R.id.nompersonne);
        String m= mytext.getText().toString();

        mytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
                Toast.makeText(getApplicationContext(),"Hello je suis le nom",Toast.LENGTH_SHORT).show();

                    }

                    /*
                    @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Personne p = documentSnapshot.toObject(Personne.class);
                            String nom=p.getNom();
                            mytext=(TextView)findViewById(R.id.t);

                            mytext.setText(nom);


                        }*/
                //});




        //String nom= mytext.getText();
        //Toast.makeText(getApplicationContext(),"Hello"+m+position,Toast.LENGTH_SHORT).show();
        //ps.get(position);

        //Intent in=new Intent(Listprof.this, Editprof.class);
        //in.putExtra("t1",nom);
        //startActivity(in);
    }
}
