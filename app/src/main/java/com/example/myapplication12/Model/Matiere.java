package com.example.myapplication12.Model;

import java.util.ArrayList;

public class Matiere {
    private String Nom_matiere;
    //.

    private ArrayList<Professeur> Prf=new ArrayList<Professeur>();
    private ArrayList<Crenau> Crn=new ArrayList<Crenau>();

    public Matiere() {
    }

    public Matiere(String nom_matiere, ArrayList<Professeur> prf, ArrayList<Crenau> crn) {
        Nom_matiere = nom_matiere;
        Prf = prf;
        Crn = crn;
    }

    public String getNom_matiere() {
        return Nom_matiere;
    }

    public void setNom_matiere(String nom_matiere) {
        Nom_matiere = nom_matiere;
    }

    public ArrayList<Professeur> getPrf() {
        return Prf;
    }

    public void setPrf(ArrayList<Professeur> prf) {
        Prf = prf;
    }

    public ArrayList<Crenau> getCrn() {
        return Crn;
    }

    public void setCrn(ArrayList<Crenau> crn) {
        Crn = crn;
    }
}
