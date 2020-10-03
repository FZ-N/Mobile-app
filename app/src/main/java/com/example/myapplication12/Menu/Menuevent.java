package com.example.myapplication12.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.R;

public class Menuevent extends AppCompatActivity {

    private Button button1,button2,button3;
    private ImageView image_add_evn;
    private TextView nomuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        getSupportActionBar().hide();
        button1=(Button) findViewById(R.id.Myevns);
        button2=(Button) findViewById(R.id.Partipe_evns);
        button3=(Button) findViewById(R.id.Autre_evns);
        image_add_evn=(ImageView) findViewById(R.id.Add_evn);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuevent.this, Listevent.class);
                startActivity(in);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuevent.this, Listevent.class);
                startActivity(in);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuevent.this, Listevent.class);
                startActivity(in);
            }
        });
        image_add_evn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuevent.this, Addevent.class);
                startActivity(in);
            }
        });
    }
}
