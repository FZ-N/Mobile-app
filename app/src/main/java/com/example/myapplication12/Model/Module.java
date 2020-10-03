package com.example.myapplication12.Model;

import java.util.ArrayList;
import java.util.Date;

public class Module {
    private String nom;
    private Date date;
    private String semestre;
    private String periode;

    private ArrayList<Personne> profs;
    private ArrayList<Cours> cours;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Personne> getProfs() {
        return profs;
    }

    public void setProfs(ArrayList<Personne> profs) {
        this.profs = profs;
    }

    public ArrayList<Cours> getCours() {
        return cours;
    }

    public void setCours(ArrayList<Cours> cours) {
        this.cours = cours;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }
}
