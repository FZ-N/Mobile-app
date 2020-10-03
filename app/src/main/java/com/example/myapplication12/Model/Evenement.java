package com.example.myapplication12.Model;

import java.util.ArrayList;
import java.util.Date;

public class Evenement {
    //private String Id_event;
    private String Nom_event;
    private Date Date_event;
    private String Description_event;
    private String Image_event;
    //.

    private ArrayList<Personne> per_participes=new ArrayList<Personne>();

    public Evenement() {

    }

    public Evenement(String nom_event, Date date_event, String description_event, String image_event, ArrayList<Personne> per_participes) {
        Nom_event = nom_event;
        Date_event = date_event;
        Description_event = description_event;
        Image_event = image_event;
        this.per_participes = per_participes;
    }

    public String getNom_event() {
        return Nom_event;
    }

    public void setNom_event(String nom_event) {
        Nom_event = nom_event;
    }

    public Date getDate_event() {
        return Date_event;
    }

    public void setDate_event(Date date_event) {
        Date_event = date_event;
    }

    public String getDescription_event() {
        return Description_event;
    }

    public void setDescription_event(String description_event) {
        Description_event = description_event;
    }

    public String getImage_event() {
        return Image_event;
    }

    public void setImage_event(String image_event) {
        Image_event = image_event;
    }

    public ArrayList<Personne> getPer_participes() {
        return per_participes;
    }

    public void setPer_participes(ArrayList<Personne> per_participes) {
        this.per_participes = per_participes;
    }
}
