package com.example.myapplication12.Messagerie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Gestion_etudiant_prof.Addetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Addprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Model.Emploi;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.AddEmploit;
import com.example.myapplication12.Scolarité.Addcours;
import com.example.myapplication12.Scolarité.Emploit;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.example.myapplication12.Services.Methodes_personne;
import com.example.myapplication12.Services.MyAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Addmessage extends AppCompatActivity implements MyAdapter.OnNoteListener {

    Menu menuitem;
    private LinearLayout t21;
    private TextView t211, t212, msg;
    public RecyclerView r,r2;
    private Object LayoutManager;
    private ImageView envoyer_msg,i1,b1;
    private ImageView scolarete1, messages1, evenement1;

    StorageReference storageReference;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    private Personne p = new Personne();
    private Personne p2 = new Personne();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmessage);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Ajouter un message");

        storageReference = FirebaseStorage.getInstance().getReference();

        msg = (TextView) findViewById(R.id.msg);
        envoyer_msg = (ImageView) findViewById(R.id.envoyermsg2);

        b1 = (ImageView) findViewById(R.id.envoyerfile);

        i1=(ImageView) findViewById(R.id.i1);

        r = (RecyclerView) findViewById(R.id.listdespersonnesmsg);
        r2 = (RecyclerView) findViewById(R.id.listdespersonnesmsg2);
        final LinkedList<Personne> prs = new LinkedList<Personne>();
        final LinkedList<Personne> prs2 = new LinkedList<Personne>();
        final Context context = this;


        final String[] nom = new String[1];
        final String[] nomuser = new String[1];
        //final String[] msguser = new String[1];

        /*Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Personne p2 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Personne p3 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");

        prs.add(p1);prs.add(p2);prs.add(p3);
        r.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
        MyAdapter myAdapter =new MyAdapter(prs,this);
        r.setAdapter(myAdapter);*/

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        final MyAdapter.OnNoteListener note = (MyAdapter.OnNoteListener) this;

        Methodes_personne.GetChef().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        p = document.toObject(Personne.class);
                        //  if(p.getType()=="Prof") {
                        prs.add(p);

                        //System.out.println("le nom ="+p.getNom());
                    }
                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapter myAdapter = new MyAdapter(prs, context, note);
                    r.setAdapter(myAdapter);


                    //del=(ImageView) findViewById(R.id.delet_personne);
                    //del.setVisibility(View.INVISIBLE);


                } else {

                }
            }


        });

        Methodes_personne.GetAllProfs().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        p = document.toObject(Personne.class);
                      //  if(p.getType()=="Prof") {
                            prs.add(p);

                        //System.out.println("le nom ="+p.getNom());
                    }
                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapter myAdapter = new MyAdapter(prs, context, note);
                    r.setAdapter(myAdapter);


                    //del=(ImageView) findViewById(R.id.delet_personne);
                    //del.setVisibility(View.INVISIBLE);


                } else {

                }
            }


        });


        Methodes_personne.GetAllEtudiants().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        p2 = document.toObject(Personne.class);
                        prs2.add(p2);
                        //System.out.println("le nom ="+p.getNom());
                    }
                    r2.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r2.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapter myAdapter = new MyAdapter(prs2, context, note);
                    r2.setAdapter(myAdapter);


                    //del=(ImageView) findViewById(R.id.delet_personne);
                    //del.setVisibility(View.INVISIBLE);


                } else {

                }
            }


        });

        envoyer_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("test", MODE_PRIVATE);
                ArrayList<Personne> pers = new ArrayList<>();
                //5 nbr users in list checked
                /*for (int i = 0; i < pref.getAll().size(); i++) {
                    String nom = "nom" + i;
                    nom = pref.getString("nom" + i, String.valueOf(false));
                    String email=pref.getString("email" + i, String.valueOf(false));
                    String tele=pref.getString("tele" + i, String.valueOf(false));
                    String mot_de_pass=pref.getString("mot_de_pass" + i, String.valueOf(false));
                    String type=pref.getString("type" + i, String.valueOf(false));
                    Personne p=new Personne(nom,mot_de_pass,email,tele,type);

                    System.out.println("les utilisateur de la list :" + nom+mot_de_pass);
                    pers.add(p);

                }*/
                Gson gson = new Gson();
                String json = pref.getString("ok", "");
                //!!!! to get type(class) of list personne
                Type type = new TypeToken<ArrayList<Personne>>() {
                }.getType();
                ArrayList<Personne> obj = gson.fromJson(json, type);
                for (Personne p : obj) {
                    System.out.println("les utilisateur de la list :" + p.getEmail());
                }


                context.getSharedPreferences("test", 0).edit().clear().commit();


                String contenu = String.valueOf(msg.getText());

                //Professeur p2 = new Professeur("ahmedxxx", "ahmed", "ahmed@gcom", "060666", "Prof");
                SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                Gson gson1 = new Gson();
                String json1 = pref1.getString("personne_c", "");
                final Personne p1 = gson1.fromJson(json1, Personne.class);
                //Professeur p1 = new Professeur("tarik", "rachid", "tarik@gcom", "0606466", "Prof");

                //String tele = String.valueOf(tele1.getText());
                //String pass= String.valueOf(pass1.getText());
                //String type="Etudiant";
                Date currentTime = Calendar.getInstance().getTime();

                ArrayList<Personne> ps = new ArrayList<>();
                //ps.add(p2);
                for (Personne p : obj) {
                    ps.add(p);
                }

                Date date_msg = null;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = dateFormat.format(currentTime);
                for (Personne p3 : ps) {
                    ArrayList<Personne> p4 = new ArrayList<>();
                    p4.add(p3);
                    Message m = new Message(currentTime, contenu, p1, p4);
                    if(!m.getContenu_msg().equals("")){
                    Methodes_msg_evt_.creatMessage(m);}
                    uploadImage(strDate,p3);
                }


                Toast.makeText(getApplicationContext(), "Votre message a été ajouté avec succès", Toast.LENGTH_LONG).show();

                Intent in = new Intent(Addmessage.this, Listmessage.class);
                startActivity(in);

            }
        });
        // Methodes_personne.GetAllUsers(new GetAllContactsOnCompleteListener());



        /*ArrayList<Personne> ps=new ArrayList<>();
        ps=Methodes_personne.getallUsers1();
        for(Personne p:ps){
            System.out.println("le nom ="+p.getNom());
        }*/


        //Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        /*Methodes_personne.GetAllUsers().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Personne p = document.toObject(Personne.class);
                        nom[0] =p.getNom();
                        //mytext7.setText(nom[0]);

                        nomuser[0] =p.getNom();
                        //msguser[0] =m.getContenu_msg();



                        t21 = (LinearLayout) findViewById(R.id.listdespersonnesmsg);
                        LayoutInflater v2 = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                        View v = v2.inflate(R.layout.unepersonnemsg, null, false);
                        t21.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        t211 = (TextView) findViewById(R.id.nompersonnemsg);
                        t211.setText(nomuser[0]);
                        //t212 = (TextView) findViewById(R.id.Msguser);
                        //t212.setText(msguser[0]);

                        //System.out.println("here is :"+mytext7.getText().toString());
                    }

                } else {

                }
            }

       });*/

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
        //itm9.setVisible(false);




        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Intent in = new Intent(Addmessage.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Addmessage.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Addmessage.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Addmessage.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Addmessage.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Addmessage.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Addmessage.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(Addmessage.this, Addprof.class);
                startActivity(in8);
                break;
            case R.id.item9:
                Intent in9 = new Intent(Addmessage.this, Emploit.class);
                startActivity(in9);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Addmessage.this, Login.class);
                startActivity(in10);
                break;
            case R.id.item88:
                Intent in11 = new Intent(Addmessage.this, Menuetudiant.class);
                startActivity(in11);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnNoteClick(int position) {

    }
    private void chooseImage() {
        Intent intent = new Intent();
        //intent.setType("image/*");
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                i1.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage(String contenu,Personne p_recu) {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();




            SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
            Gson gson1 = new Gson();
            String json1 = pref1.getString("personne_c", "");
            final Personne p1 = gson1.fromJson(json1, Personne.class);
            //Professeur p1 = new Professeur("tarik", "rachid", "tarik@gcom", "0606466", "Prof");

            //String tele = String.valueOf(tele1.getText());
            //String pass= String.valueOf(pass1.getText());
            //String type="Etudiant";
            Date currentTime = Calendar.getInstance().getTime();

            ArrayList<Personne> ps = new ArrayList<>();
            ps.add(p_recu);


            Date date_msg = null;
            Message m = new Message(currentTime, "", p1, ps);
            m.setType("File");
            Methodes_msg_evt_.creatMessage(m);

            StorageReference ref = storageReference.child("messages/"+ contenu);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Addmessage.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Addmessage.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
