package com.example.myapplication12.Model;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Message;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Personne implements Serializable {
    private String nom;
    private String mot_de_passe;
    private String email;
    private String num_telephone;
    private String type;

    private String annee;

    private Module module;

    private String semestre;

    //attribus

    //private ArrayList<Message> Msg_envoyes=new ArrayList<Message>();
    //private ArrayList<Message> Msg_recus=new ArrayList<Message>();
    //private ArrayList<Evenement> Evn_participes=new ArrayList<Evenement>();

    public Personne() {
    }

    public Personne(String Nom, String Mot_de_passe, String Email, String Num_telephone, String Type) {
        nom = Nom;
        mot_de_passe = Mot_de_passe;
        email = Email;
        num_telephone = Num_telephone;
        this.type = Type;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getType() {
        return type;
    }

    public void setType(String Type) {
        type = Type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String Nom) {
        nom = Nom;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String Mot_de_passe) {
        mot_de_passe = Mot_de_passe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        email = Email;
    }

    public String getNum_telephone() {
        return num_telephone;
    }

    public void setNum_telephone(String Num_telephone) {
        num_telephone = Num_telephone;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}
