package com.example.myapplication12.Model;

import java.util.Date;

public class Cours {
    private String Nom_cours;
    private String Contenu_cours;
    private Date Date_cours;
    //+file

    //.

    private Matiere M;
    private Professeur Prf;

    public Cours() {
    }

    public Cours(String nom_cours, String contenu_cours, Date date_cours, Matiere m, Professeur prf) {
        Nom_cours = nom_cours;
        Contenu_cours = contenu_cours;
        Date_cours = date_cours;
        M = m;
        Prf = prf;
    }

    public String getNom_cours() {
        return Nom_cours;
    }

    public void setNom_cours(String nom_cours) {
        Nom_cours = nom_cours;
    }

    public String getContenu_cours() {
        return Contenu_cours;
    }

    public void setContenu_cours(String contenu_cours) {
        Contenu_cours = contenu_cours;
    }

    public Date getDate_cours() {
        return Date_cours;
    }

    public void setDate_cours(Date date_cours) {
        Date_cours = date_cours;
    }

    public Matiere getM() {
        return M;
    }

    public void setM(Matiere m) {
        M = m;
    }

    public Professeur getPrf() {
        return Prf;
    }

    public void setPrf(Professeur prf) {
        Prf = prf;
    }
}
