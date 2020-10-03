package com.example.myapplication12.Services;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Personne;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Methodes_event {
    public static CollectionReference getUsersCollection(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Evenement");
    }

    public static Task<QuerySnapshot> GetAllevents() {


        return getUsersCollection().get();

    }

    public static Task<DocumentReference> creatEvent(Evenement p) {
        //Personne userToCreate = new Personne(nom);
        return getUsersCollection().add(p);

    }
    public static Task<QuerySnapshot> GetAllEvens() {


        return getUsersCollection().get();

    }
    public static Task<QuerySnapshot> deleteEven(String nom1) {
        return Methodes_event.getUsersCollection().whereEqualTo("nom_event",nom1).get();
    }
    public static Task<QuerySnapshot> Geteventbynom(String nom) {


        return getUsersCollection().whereEqualTo("nom_event",nom).get();

    }
    public static Task<QuerySnapshot> updateevent(String nom1) {

        return Methodes_event.getUsersCollection().whereEqualTo("nom_event",nom1).get();


    }
    public static Task<QuerySnapshot> updateeventpar(String nom1) {

        return Methodes_event.getUsersCollection().whereEqualTo("nom_event",nom1).get();


    }
}
