package com.example.myapplication12.Scolarité;

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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Gestion_etudiant_prof.Addetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Addprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Menu.Login;

import com.example.myapplication12.Messagerie.Addmessage;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Module;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;

import com.google.gson.Gson;


public class Emploit extends AppCompatActivity {
    private ImageView scolarete1, messages1, evenement1;
    Menu menuitem;
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploit);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        actionBar.setTitle("Emploi du temps des étudiants");
        scolarete1 = (ImageView) findViewById(R.id.Scolarite1);
        messages1 = (ImageView) findViewById(R.id.messages1);
        evenement1 = (ImageView) findViewById(R.id.evenement1);
        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Emploit.this, Listcours.class);
                startActivity(in);
            }
        });
        messages1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Emploit.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Emploit.this, Listevent.class);
                startActivity(in);
            }
        });

        String myurl;
        myWebView = (WebView) findViewById(R.id.web);
        WebSettings mWebSettings = myWebView.getSettings();


        mWebSettings.setJavaScriptEnabled(true); // Done above
        mWebSettings.setDomStorageEnabled(true); // Try
        mWebSettings.setSupportZoom(false);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAllowContentAccess(true);

        mWebSettings.setJavaScriptEnabled(true);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        final Personne p1 = gson.fromJson(json, Personne.class);


        if (p1.getType().equals("Prof")) {
            Module mod =p1.getModule();
            final String liste = mod.getNom();
            final String snum= p1.getSemestre();
            final int num= Integer.parseInt(snum);
            actionBar.setTitle("Emploi du temps du professeur " + liste);
           if (num <3)
           {
               myurl = "file:///android_asset/prof.html";
           }
            else if (num <5)
            {
                myurl = "file:///android_asset/prof2.html";
            }
            else
            {
                myurl = "file:///android_asset/prof3.html";
            }

                myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String list) {

                        view.evaluateJavascript("myVar('" + liste + "')", null);


                        view.loadUrl("javascript:myVar('X')");
                    }

            });
        }
        else
        {
            myurl = "file:///android_asset/emp.html";
            if (p1.getType().equals("Etudiant")) {
                if (p1.getAnnee().equals("1ere année"))
                    myurl = "file:///android_asset/emp.html";
                else if (p1.getAnnee().equals("2eme année"))
                    myurl = "file:///android_asset/emp2.html";
                else if (p1.getAnnee().equals("3eme année"))
                    myurl = "file:///android_asset/emp3.html";
            }
            else if (p1.getType().equals("Chef")) {
                Intent in5 = new Intent(Emploit.this, em.class);
                startActivity(in5);
            }

        }
        myWebView.loadUrl(myurl);
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


        itm1.setVisible(false);
        itm2.setVisible(false);
        itm3.setVisible(false);
        itm4.setVisible(false);
        itm5.setVisible(false);
        itm6.setVisible(false);
        itm7.setVisible(false);
        itm8.setVisible(false);
        itm9.setVisible(false);




        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        final Personne p1 = gson.fromJson(json, Personne.class);


        if (p1.getType().equals("Chef")) {
            itm5.setVisible(true);
            itm5.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }



        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item5:
                Intent in5 = new Intent(Emploit.this, AddEmploit.class);
                startActivity(in5);
                break;


            case R.id.item10:
                Intent in10 = new Intent(Emploit.this, Login.class);
                startActivity(in10);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}

