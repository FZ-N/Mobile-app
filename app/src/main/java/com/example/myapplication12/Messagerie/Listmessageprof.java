package com.example.myapplication12.Messagerie;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Gestion_etudiant_prof.Addetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Addprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.AddEmploit;
import com.example.myapplication12.Scolarité.Addcours;
import com.example.myapplication12.Scolarité.Emploit;
import com.example.myapplication12.Scolarité.Listcours;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.example.myapplication12.Services.MyAdapterMessage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Listmessageprof extends AppCompatActivity {

    static ArrayList<Message> messages1 = new ArrayList<>();

    Menu menuitem;
    private LinearLayout l1, l2, t21;
    private ImageView scolarete1, messages11, evenement1;
    private ImageView imageadd;
    private TextView t1, mytext7, t211, t212;
    private ListView t2, t3;
    public RecyclerView r;
    final ArrayList<Message> messages = new ArrayList<>();
    private Object LayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Les messages");


        scolarete1 = (ImageView) findViewById(R.id.Scolarite10);
        messages11 = (ImageView) findViewById(R.id.messages10);
        evenement1 = (ImageView) findViewById(R.id.evenement10);
        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Listmessageprof.this, Listcours.class);
                startActivity(in);
            }
        });
        messages11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Listmessageprof.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Listmessageprof.this, Listevent.class);
                startActivity(in);
            }
        });
        //l1=(LinearLayout) findViewById(R.id.User1);
        //l2=(LinearLayout) findViewById(R.id.User2);
        //imageadd = (ImageView) findViewById(R.id.Add_msg);
        t1 = (TextView) findViewById(R.id.Msguser);
        //mytext7 = (TextView) findViewById(R.id.nom8);

        //t2 = (ListView) findViewById(R.id.allmessages);
        //Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        //Message m=new Message();
        //m.setPer_envoye(p1);

        final String[] nom = new String[1];
        final String[] nomuser = new String[1];
        final String[] msguser = new String[1];

        r = (RecyclerView) findViewById(R.id.listdesmessages);
        final LinkedList<Message> msgs = new LinkedList<Message>();
        final ArrayList<String> persmsgs1 = new ArrayList<String>();

        final Context context = this;

        /*Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Methodes_msg_evt_.GetMessagesrecu(p1).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Message m = document.toObject(Message.class);

                        msgs.add(m);

                    }
                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapterMessage myAdapter = new MyAdapterMessage(msgs, context);
                    r.setAdapter(myAdapter);

                } else {

                }
            }



        });*/
        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        final Personne p1 = gson.fromJson(json, Personne.class);
        //final Personne p1 = new Personne("ahmed", "ahmed", "ahmed@gcom", "060666", "Prof");
        persmsgs1.add(p1.getNom());
        Task task1 = Methodes_msg_evt_.GetMessages(p1);
        Task task2 = Methodes_msg_evt_.GetMessagesrecu(p1);
        Task<List<QuerySnapshot>> alltask = Tasks.whenAllSuccess(task1, task2);
        alltask.addOnCompleteListener(new OnCompleteListener<List<QuerySnapshot>>() {
            @Override
            public void onComplete(@NonNull Task<List<QuerySnapshot>> task) {
                if (task.isSuccessful()) {

                    /*Message msg1=new Message();
                    msg1.setPer_envoye(p1); //not existed one
                    msgs.add(msg1);*/
                    for (QuerySnapshot document : task.getResult()) {
                        for (QueryDocumentSnapshot document1 : document) {
                            Message m = document1.toObject(Message.class);
                            if (m.getPer_envoye().getType().equals("Prof")) {
                                if (!persmsgs1.contains(m.getPer_envoye().getNom())) {
                                    persmsgs1.add(m.getPer_envoye().getNom());

                                    msgs.add(m);
                                }


                                for (int i = 0; i < m.getPer_recus().size(); i++) {
                                    if (!persmsgs1.contains(m.getPer_recus().get(i).getNom())) {
                                        persmsgs1.add(m.getPer_recus().get(i).getNom());
                                        msgs.add(m);
                                    }
                                }
                            } else {
                                for (int i = 0; i < m.getPer_recus().size(); i++) {
                                    if (m.getPer_recus().get(i).getType().equals("Prof")) {
                                        if (!persmsgs1.contains(m.getPer_envoye().getNom())) {
                                            persmsgs1.add(m.getPer_envoye().getNom());

                                            msgs.add(m);
                                        }


                                        for (int j = 0; j < m.getPer_recus().size(); j++) {
                                            if (!persmsgs1.contains(m.getPer_recus().get(j).getNom())) {
                                                persmsgs1.add(m.getPer_recus().get(j).getNom());
                                                msgs.add(m);
                                            }
                                        }
                                    }
                                }
                            }

                        }


                    }
                    //il faut classer les msgs
                    /*int i=0;
                    for (Message m2 : msgs) {
                        for (Message m1 : msgs) {
                            if (m2.getPer_envoye().equals(m1.getPer_envoye())) {
                                i+=1;
                            }
                        }
                        if(i>1){
                            msgs.remove(m2);
                        }
                    }*/
                    Collections.sort(msgs, new Comparator<Message>() {
                        @Override
                        public int compare(Message o1, Message o2) {
                            Date currentTime = Calendar.getInstance().getTime();

                            return o1.getDate_msg().compareTo(o2.getDate_msg());
                        }


                    });
                    System.out.println("nomre de message !!!!!!" + msgs.size());
                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapterMessage myAdapter = new MyAdapterMessage(msgs, context);
                    r.setAdapter(myAdapter);
                } else {

                }
            }
        });


        /*System.out.println("here is :"+ nom[0]);

        Message m1=new Message();
        m1.setContenu_msg("msg here");
        m1.setPer_envoye(p1);
        messages.add(m1);
        for (int i = 0; i <messages.size() ; i++) {
            System.out.println("èçèç(çè"+messages.get(i).getContenu_msg());
        }*/


        /*ArrayAdapter<Message> arrayAdapter
                = new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_1 , messages);
        t2.setAdapter(arrayAdapter);*/

        //ArrayList<Message> MS= Methodes_msg_evt_.GetMessages();
        /*Methodes_msg_evt_.GetMessages("ok").addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Message p = document.toObject(Message.class);
                        String nom=p.getContenu_msg();
                        //t1=(TextView)findViewById(R.id.Msg1);

                        //t1.setText(nom);
                    }
                } else {

                }
            }});*/






       /* l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listmessage1.this, Discussion.class);
                startActivity(in);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listmessage1.this, Discussion.class);
                startActivity(in);
            }
        });*/
        /*imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Listmessage1.this, Addmessage.class);
                startActivity(in);
            }
        });*/


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
        //itm6.setVisible(false);
        itm7.setVisible(false);
        itm8.setVisible(false);
        itm9.setVisible(false);

        itm6.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent in = new Intent(Listmessageprof.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Listmessageprof.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Listmessageprof.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Listmessageprof.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Listmessageprof.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Listmessageprof.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Listmessageprof.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(Listmessageprof.this, Addprof.class);
                startActivity(in8);
                break;
            case R.id.item9:
                Intent in9 = new Intent(Listmessageprof.this, Emploit.class);
                startActivity(in9);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Listmessageprof.this, Login.class);
                startActivity(in10);
                break;
            case R.id.item88:
                Intent in11 = new Intent(Listmessageprof.this, Menuetudiant.class);
                startActivity(in11);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
