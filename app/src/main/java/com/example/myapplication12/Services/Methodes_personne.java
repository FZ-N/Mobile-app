package com.example.myapplication12.Services;

import com.example.myapplication12.Model.Personne;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Methodes_personne {
    private static final String COLLECTION_NAME = "Personne";


    // --- COLLECTION REFERENCE ---

    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<Void> createUser(Personne p) {
        //Personne userToCreate = new Personne(nom);
        return Methodes_personne.getUsersCollection().document(p.getNom()).set(p);

    }

    // --- GET ---

    public static Task<DocumentSnapshot> getUser(String nom){
        return Methodes_personne.getUsersCollection().document(nom).get();
    }

    // --- UPDATE ---

    public static Task<QuerySnapshot> updateUser(String nom1, Personne p) {
        //return Methodes_personne.getUsersCollection().document(nom1).update("Nom",p.getNom(),"Email",p.getEmail(),"Num_telephone",p.getNum_telephone());
        return Methodes_personne.getUsersCollection().whereEqualTo("nom",nom1).get();
                //document(nom1).update("Nom",p.getNom(),"Email",p.getEmail(),"Num_telephone",p.getNum_telephone());

    }

    /*
    public static Task<Void> updateIsMentor(String uid, Boolean isMentor) {
        return Methodes_personne.getUsersCollection().document(uid).update("isMentor", isMentor);
    }
    */
    // --- DELETE ---

    public static Task<QuerySnapshot> deleteUser(String nom1) {
        return Methodes_personne.getUsersCollection().whereEqualTo("nom",nom1).get();
    }

    public static Task<QuerySnapshot> GetUsers(String nom, String mot_de_passe) {


        return getUsersCollection().whereEqualTo("nom", nom).whereEqualTo("mot_de_passe",mot_de_passe).get();

    }
    /*public static Task<QuerySnapshot> GetAllUsers(OnCompleteListener<QuerySnapshot> onCompleteListener) {


        return getUsersCollection().get().addOnCompleteListener(onCompleteListener);

    }*/
    public static Task<QuerySnapshot> GetAllUsers() {


        return getUsersCollection().get();

    }
    public static Task<QuerySnapshot> GetAllProfs() {


        return getUsersCollection().whereEqualTo("type","Prof").get();

    }

    public static Task<QuerySnapshot> GetChef() {


        return getUsersCollection().whereEqualTo("type","Chef").get();

    }

    public static Task<QuerySnapshot> Getuserbynom(String nom) {


        return getUsersCollection().whereEqualTo("nom",nom).get();

    }
    public static Task<QuerySnapshot> GetAllEtudiants() {


        return getUsersCollection().whereEqualTo("type","Etudiant").get();

    }

    /*public static ArrayList<Personne> getallUsers1() {
        ArrayList<Personne> ps = new ArrayList<>();
        Task<QuerySnapshot> tesk = Methodes_personne.GetAllUsers();

                    for (QueryDocumentSnapshot document : tesk.getResult()) {
                        Personne p = document.toObject(Personne.class);
                        ps.add(p);
                    }
                    //r.setHasFixedSize(true);
                    //LayoutManager = new LinearLayoutManager(this);


        return ps;



    }*/


    /*public static void main(String[] args) {
        String nom="ahmed";
        String mot_de_passe="";
        String Type=null;
        OnCompleteListener<QuerySnapshot> onCompleteListener;
        Task<QuerySnapshot> tasksk = Methodes_personne.GetAllUsers();

                            for (QueryDocumentSnapshot document : task.getResult())
                                //Personne p1 = p.toObject(Personne.class);
                                System.out.println("une personne :"+p.getNom());

                            }

    }*/


}
