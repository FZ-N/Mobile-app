package com.example.myapplication12.Services;

import com.example.myapplication12.Model.Cours;
import com.example.myapplication12.Model.Emploi;
import com.example.myapplication12.Scolarité.Emploit;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Methodes_eml {

    public static CollectionReference getUsersCollection(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Emploi");
    }

    public static Task<QuerySnapshot> GetAllemploits() {


        return getUsersCollection().get();

    }

    public static Task<DocumentReference> createvent(Emploi e) {
        //Personne userToCreate = new Personne(nom);
        return getUsersCollection().add(e);

    }
    public static Task<QuerySnapshot> GetAllEvens() {


        return getUsersCollection().get();

    }
    public static Task<QuerySnapshot> deleteCours(String nom1) {
        return Methodes_cours.getUsersCollection().whereEqualTo("nom_cours",nom1).get();
    }

    public static Task<QuerySnapshot> Getcoursbynom(String nom) {


        return getUsersCollection().whereEqualTo("nom_cours",nom).get();

    }
}
