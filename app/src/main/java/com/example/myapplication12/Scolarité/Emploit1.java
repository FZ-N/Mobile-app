package com.example.myapplication12.Scolarité;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Module;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.google.gson.Gson;


public class Emploit1 extends AppCompatActivity {
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
                Intent in = new Intent(Emploit1.this, Listcours.class);
                startActivity(in);
            }
        });
        messages1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Emploit1.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Emploit1.this, Listevent.class);
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


        myurl = "file:///android_asset/emp.html";
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
                Intent in5 = new Intent(Emploit1.this, AddEmploit1.class);
                startActivity(in5);
                break;
            case R.id.item88:
                Intent in11 = new Intent(Emploit1.this, Menuetudiant.class);
                startActivity(in11);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Emploit1.this, Login.class);
                startActivity(in10);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

