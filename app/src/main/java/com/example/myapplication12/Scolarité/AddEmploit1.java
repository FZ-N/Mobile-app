package com.example.myapplication12.Scolarité;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Gestion_etudiant_prof.Addetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Messagerie.Addmessage;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Emploi;
import com.example.myapplication12.Model.Matiere;
import com.example.myapplication12.Model.Professeur;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_emp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddEmploit1 extends AppCompatActivity {

    Menu menuitem;
    String semestre = "2";
    private EditText sems,sem,dur,md;
    private TextView text3;
    FirebaseStorage storage;
    StorageReference storageReference;
    private ImageView scolarete1, messages1, evenement1;

    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emploit);
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DATE);
        int month = c.get(Calendar.MONTH);
        if (month > 9 | (month < 2 & day<15 ))
            semestre ="1";
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Ajouter un créneau (1ère année)");

        final Spinner salle = findViewById(R.id.salle);
        String[] items2 = new String[]{"Amphi 1", "Amphi 2", "L7","A3", "L25"};
        ArrayAdapter<String> adapter8 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        salle.setAdapter(adapter8);


        final Spinner hd = findViewById(R.id.hd);
        String[] itemsh = new String[]{"8:30", "10:30", "14:00","16:00"};
        ArrayAdapter<String> adapterh = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsh);
        hd.setAdapter(adapterh);


        final Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Lundi", "Mardi", "Mercredi","Jeudi", "Vendredi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference subjectsRef = rootRef.collection("Module");
        final Spinner spinner = (Spinner) findViewById(R.id.mod);
        final List<String> subjects = new ArrayList<>();
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, subjects);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        subjectsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("nom");
                        if (document.getString("semestre").equals(semestre))
                            subjects.add(subject);
                    }
                    adapter2.notifyDataSetChanged();
                }
            }
        });

        scolarete1 = (ImageView) findViewById(R.id.Scolarite8);
        messages1 = (ImageView) findViewById(R.id.messages8);
        evenement1 = (ImageView) findViewById(R.id.evenement8);
        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AddEmploit1.this, Listcours.class);
                startActivity(in);
            }
        });
        messages1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AddEmploit1.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AddEmploit1.this, Listevent.class);
                startActivity(in);
            }
        });

        sem = (EditText) findViewById(R.id.semaine);


        //pass1=(EditText) findViewById(R.id.password);
        text3 = (TextView) findViewById(R.id.ajouter);
        storageReference = FirebaseStorage.getInstance().getReference();




        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jour= String.valueOf(dropdown.getSelectedItem());
                String mod= String.valueOf(spinner.getSelectedItem());
                String sm= String.valueOf(sem.getText());
                String sr= String.valueOf(salle.getSelectedItem());
                String d = "2";
                String deb =  String.valueOf(hd.getSelectedItem());
                //String nom_matiere = String.valueOf(matiere1.getText());
                SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                Gson gson = new Gson();
                String json = pref.getString("personne_c", "");
                Professeur p1 = gson.fromJson(json, Professeur.class);

                //String tele = String.valueOf(tele1.getText());
                //String pass= String.valueOf(pass1.getText());
                //String type="Etudiant";

                Date date_cours = null;


                Date currentTime = Calendar.getInstance().getTime();

                Matiere m1 = new Matiere();


                Emploi c = new Emploi(sr,sm,d,deb,jour,mod);
                Methodes_emp.createmp(c);

                Toast.makeText(getApplicationContext(), "Votre créneau a été ajouté avec succès", Toast.LENGTH_LONG).show();

                Intent in = new Intent(AddEmploit1.this, AddEmploit1.class);
                startActivity(in);
            }
        });
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }
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
        itm5.setVisible(false);
        itm6.setVisible(false);
        itm7.setVisible(false);
        itm8.setVisible(false);



        //itm8.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent in = new Intent(AddEmploit1.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(AddEmploit1.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(AddEmploit1.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(AddEmploit1.this, AddEmploit1.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(AddEmploit1.this, AddEmploit1.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(AddEmploit1.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(AddEmploit1.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(AddEmploit1.this, Emploit.class);
                startActivity(in8);
                break;
            case R.id.item88:
                Intent in88 = new Intent(AddEmploit1.this, Menuetudiant.class);
                startActivity(in88);
                break;


            case R.id.item9:
                Intent in9 = new Intent(AddEmploit1.this, Emploit1.class);
                startActivity(in9);
                break;

            case R.id.item10:
                Intent in10 = new Intent(AddEmploit1.this, Login.class);
                startActivity(in10);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

