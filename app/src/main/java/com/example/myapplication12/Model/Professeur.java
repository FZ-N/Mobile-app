package com.example.myapplication12.Model;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Professeur extends Personne {
    //.

    //je pense on peut l'ajouter dans un seul sens
    //private ArrayList<Matiere> M=new ArrayList<Matiere>();

    public Professeur() {
    }

    public Professeur(String nom, String mot_de_passe, String email, String num_telephone, String type) {
        super(nom, mot_de_passe, email, num_telephone,type);
    }
}
