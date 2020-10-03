package com.example.myapplication12.Scolarité;

import com.example.myapplication12.Model.Cours;
import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Module;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Methodes_cours {
    public static CollectionReference getUsersCollection(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Cours");
    }

    public static Task<QuerySnapshot> GetAllcours() {


        return getUsersCollection().get();

    }

    public static Task<QuerySnapshot> GetAllmodules() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Module").get();


    }

    public static Task<DocumentReference> creatcours(Cours c) {
        //Personne userToCreate = new Personne(nom);
        return getUsersCollection().add(c);

    }

    public static Task<DocumentReference> creatmodule(Module c) {
        //Personne userToCreate = new Personne(nom);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Module").add(c);


    }
    public static Task<QuerySnapshot> deleteModule(String nom1) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Module").whereEqualTo("nom",nom1).get();

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

    public static Task<QuerySnapshot> GetModulebynom(String nom) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return  db.collection("Module").whereEqualTo("nom",nom).get();

    }
}
