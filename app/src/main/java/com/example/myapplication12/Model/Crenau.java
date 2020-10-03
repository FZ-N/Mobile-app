package com.example.myapplication12.Model;

import java.util.ArrayList;
import java.util.Date;

public class Crenau {
    private Date Date_debut;
    private Date Date_fin;
    //.
    private Module M;

    //private ArrayList<Emploi> empl=new ArrayList<Emploi>();


    public Crenau(Date date_debut, Date date_fin, Module m) {
        Date_debut = date_debut;
        Date_fin = date_fin;
        M = m;
    }

    public Date getDate_debut() {
        return Date_debut;
    }

    public void setDate_debut(Date date_debut) {
        Date_debut = date_debut;
    }

    public Date getDate_fin() {
        return Date_fin;
    }

    public void setDate_fin(Date date_fin) {
        Date_fin = date_fin;
    }

    public Module getM() {
        return M;
    }

    public void setM(Module m) {
        M = m;
    }
}
