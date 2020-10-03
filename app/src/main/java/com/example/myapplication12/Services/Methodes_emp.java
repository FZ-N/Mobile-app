package com.example.myapplication12.Services;


import androidx.annotation.NonNull;

import com.example.myapplication12.Model.Emploi;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class Methodes_emp {




    public static void createmp(final Emploi c) {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        final CollectionReference complaintsRef = rootRef.collection("emp");
        complaintsRef.whereEqualTo("jour", c.getJour()).whereEqualTo("hd", c.getHd()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<Object, String> map = new HashMap<>();
                        map.put("salle", c.getSalle());
                        map.put("mod", c.getMod());
                        map.put("semaine", c.getSemaine());
                        complaintsRef.document(document.getId()).set(map, SetOptions.merge());
                    }
                }
            }
        });

    }

    public static void createmp2(final Emploi c) {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        final CollectionReference complaintsRef = rootRef.collection("emp2");
        complaintsRef.whereEqualTo("jour", c.getJour()).whereEqualTo("hd", c.getHd()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<Object, String> map = new HashMap<>();
                        map.put("salle", c.getSalle());
                        map.put("mod", c.getMod());
                        map.put("semaine", c.getSemaine());
                        complaintsRef.document(document.getId()).set(map, SetOptions.merge());
                    }
                }
            }
        });

    }


    public static void createmp3(final Emploi c) {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        final CollectionReference complaintsRef = rootRef.collection("emp3");
        complaintsRef.whereEqualTo("jour", c.getJour()).whereEqualTo("hd", c.getHd()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<Object, String> map = new HashMap<>();
                        map.put("salle", c.getSalle());
                        map.put("mod", c.getMod());
                        map.put("semaine", c.getSemaine());
                        complaintsRef.document(document.getId()).set(map, SetOptions.merge());
                    }
                }
            }
        });


    }
}
