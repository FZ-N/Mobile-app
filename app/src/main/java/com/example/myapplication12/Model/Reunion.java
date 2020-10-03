package com.example.myapplication12.Model;

import java.util.ArrayList;
import java.util.Date;

public class Reunion extends Evenement {
    public Reunion(String nom_event, Date date_event, String description_event, String image_event, ArrayList<Personne> per_participes) {
        super(nom_event, date_event, description_event, image_event, per_participes);
    }

    public Reunion() {
        super();
    }
}
