package com.example.myapplication12.Model;

import java.util.ArrayList;
import java.util.Date;

public class Message {
    private String Contenu_msg;
    private Date Date_msg;
    private String Type;
    //.
    private Personne Per_envoye;

    private ArrayList<Personne> Per_recus=new ArrayList<Personne>();

    public Message() {
    }

    public Message(Date Date_msg,String contenu_msg, Personne per_envoye, ArrayList<Personne> per_recus) {
        Contenu_msg = contenu_msg;
        Per_envoye = per_envoye;
        Per_recus = per_recus;
        this.Date_msg =Date_msg;
    }

    public String getContenu_msg() {
        return Contenu_msg;
    }

    public Date getDate_msg() {
        return Date_msg;
    }

    public void setDate_msg(Date date_msg) {
        Date_msg = date_msg;
    }

    public void setContenu_msg(String contenu_msg) {
        Contenu_msg = contenu_msg;
    }

    public Personne getPer_envoye() {
        return Per_envoye;
    }

    public void setPer_envoye(Personne per_envoye) {
        Per_envoye = per_envoye;
    }

    public ArrayList<Personne> getPer_recus() {
        return Per_recus;
    }

    public void setPer_recus(ArrayList<Personne> per_recus) {
        Per_recus = per_recus;
    }

    public String toString() {
        return this.Per_envoye.getNom() +" ("+ this.Contenu_msg+")";
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
