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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Gestion_etudiant_prof.Addetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Addprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.MainActivity;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.Model.Professeur;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.AddEmploit;
import com.example.myapplication12.Scolarité.Addcours;
import com.example.myapplication12.Scolarité.Emploit;
import com.example.myapplication12.Scolarité.Listcours;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.example.myapplication12.Services.Methodes_personne;
import com.example.myapplication12.Services.MyAdapterMessage;
import com.example.myapplication12.Services.MyAdapterMessageDiscussion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Discussion extends AppCompatActivity {

    static ArrayList<Message> messages1 = new ArrayList<>();

    Menu menuitem;
    private LinearLayout l1, l2, t21;
    private ImageView scolarete1, messages11, evenement1,b1,i1;
    private ImageView imageadd;
    private TextView t1, nom_recu, mytext7, t211, t212;
    private ListView t2, t3;
    public RecyclerView r;
    final ArrayList<Message> messages = new ArrayList<>();
    private Object LayoutManager;
    SharedPreferences pref;

    FirebaseStorage storage;
    StorageReference storageReference;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);


        /*scolarete1 = (ImageView) findViewById(R.id.Scolarite1);
        messages11 = (ImageView) findViewById(R.id.messages1);
        evenement1 = (ImageView) findViewById(R.id.evenement1);
        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Discussion.this, Listcours.class);
                startActivity(in);
            }
        });
        messages11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Discussion.this, Listmessage1.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Discussion.this, Listevent.class);
                startActivity(in);
            }
        });*/

        imageadd = (ImageView) findViewById(R.id.envoyermsg);
        t1 = (TextView) findViewById(R.id.messageaenvoye);
        b1 = (ImageView) findViewById(R.id.envoyerfile);

        i1=(ImageView) findViewById(R.id.i1);

        r = (RecyclerView) findViewById(R.id.listdesmessagesd);
        final LinkedList<Message> msgs = new LinkedList<Message>();
        final Context context = this;

        SharedPreferences pref3 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson3 = new Gson();
        String json3 = pref3.getString("personne_c", null);
        Personne pc = gson3.fromJson(json3, Personne.class);


        String nomuser = null;


        Bundle extras = getIntent().getExtras();
        nomuser = extras.getString("nom_per_non_connecte");
        /*String nomuser1 = extras.getString("nom_per_envoye");
        String nomuser2 = extras.getString("nom_per_recu");

        if (nomuser1.equals(pc.getNom())) {
            nomuser = nomuser2;
        } else {
            nomuser = nomuser1;
            System.out.println("wtfffff :");
        }*/
        /* else {
             nomuser =p4.getNom();
        }*/

        //}
        //System.out.println("..............this......." + nomuser);
        //nom_recu=(TextView) findViewById(R.id.nom_recu);
        //nom_recu.setText(nomuser);
        actionBar.setTitle(nomuser);


        Methodes_personne.Getuserbynom(nomuser).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Personne p2 = document.toObject(Personne.class);

                        pref = getApplicationContext().getSharedPreferences("personne_recu", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(p2);
                        editor.putString("personne_re", json);
                        editor.commit();

                        SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                        Gson gson1 = new Gson();
                        String json1 = pref1.getString("personne_c", "");
                        final Personne p1 = gson1.fromJson(json1, Personne.class);


                        //Personne p1 = new Personne("rachid", "rachid", "rachid@gcom", "060666", "Prof");
                        Task task1 = Methodes_msg_evt_.GetMessages1(p1, p2);
                        Task task2 = Methodes_msg_evt_.GetMessages1(p2, p1);
                        Task<List<QuerySnapshot>> alltask = Tasks.whenAllSuccess(task1, task2);
                        alltask.addOnCompleteListener(new OnCompleteListener<List<QuerySnapshot>>() {
                            @Override
                            public void onComplete(@NonNull Task<List<QuerySnapshot>> task) {
                                if (task.isSuccessful()) {

                                    for (QuerySnapshot document : task.getResult()) {
                                        for (QueryDocumentSnapshot document1 : document) {
                                            Message m = document1.toObject(Message.class);

                                            msgs.add(m);
                                            System.out.println("les message here :::"+m.getContenu_msg());
                                        }


                                    }
                                    Collections.sort(msgs, new Comparator<Message>() {
                                        @Override
                                        public int compare(Message o1, Message o2) {
                                            return o1.getDate_msg().compareTo(o2.getDate_msg());
                                        }


                                    });
                                    r.setHasFixedSize(true);
                                    LayoutManager = new LinearLayoutManager(context);
                                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                                    MyAdapterMessageDiscussion myAdapter = new MyAdapterMessageDiscussion(msgs, context);
                                    r.setAdapter(myAdapter);
                                } else {

                                }
                            }
                        });


                    }
                } else {

                }


            }
        });
        storageReference = FirebaseStorage.getInstance().getReference();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Professeur p2 = new Professeur("ahmedxxx", "ahmed", "ahmed@gcom", "060666", "Prof");

                SharedPreferences pref3 = getApplicationContext().getSharedPreferences("personne_recu", MODE_PRIVATE);
                Gson gson3 = new Gson();
                String json3 = pref3.getString("personne_re", "");
                Personne p2 = gson3.fromJson(json3, Personne.class);


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
                ps.add(p2);


                Date date_msg = null;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = dateFormat.format(currentTime);

                Message m = new Message(currentTime, t1.getText().toString(), p1, ps);
                m.setType("Messaage");
                if(!m.getContenu_msg().equals("")){
                Methodes_msg_evt_.creatMessage(m);}

                uploadImage(strDate);

                Toast.makeText(getApplicationContext(), "Votre message a été ajouter avec succès", Toast.LENGTH_LONG).show();
                Intent in = new Intent(Discussion.this, Discussion.class);
                String m1 = p1.getNom();
                String m2 = p2.getNom();
                //in.putExtra("nom_per_envoye", m1);
                in.putExtra("nom_per_non_connecte", m2);
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




        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent in = new Intent(Discussion.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Discussion.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Discussion.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Discussion.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Discussion.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Discussion.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Discussion.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(Discussion.this, Addprof.class);
                startActivity(in8);
                break;
            case R.id.item9:
                Intent in9 = new Intent(Discussion.this, Emploit.class);
                startActivity(in9);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Discussion.this, Login.class);
                startActivity(in10);
                break;
            case R.id.item88:
                Intent in11 = new Intent(Discussion.this, Menuetudiant.class);
                startActivity(in11);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("*/*");
        //intent.setType("*/*");
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
    private void uploadImage(String contenu) {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            SharedPreferences pref3 = getApplicationContext().getSharedPreferences("personne_recu", MODE_PRIVATE);
            Gson gson3 = new Gson();
            String json3 = pref3.getString("personne_re", "");
            Personne p2 = gson3.fromJson(json3, Personne.class);


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
            ps.add(p2);


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
                            Toast.makeText(Discussion.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Discussion.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
