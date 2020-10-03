package com.example.myapplication12.Model;

import java.util.ArrayList;
import java.util.Date;

public class Emploi {
    //.

    private String salle, semaine, dur, hd,jour, Mod;

    //private ArrayList<Crenau> Crn=new ArrayList<Crenau>();


    public Emploi() {
    }

    public Emploi(String salle, String semaine, String dur,String deb,String jour, String mod) {
        this.salle = salle;
        this.semaine = semaine;
        this.hd = deb;
        this.dur = dur;
        this.jour = jour;
        this.Mod = mod;
    }

    public String getDur() {
        return dur;
    }

    public void setDur(String dur) {
        this.dur = dur;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getSemaine() {
        return semaine;
    }

    public void setSemaine(String semaine) {
        this.semaine = semaine;
    }

    public String getHd() {
        return hd;
    }
    public String getMod() {
        return Mod;
    }

    public void setMod(String mod) {
        this.Mod = mod;
    }


    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }



}

