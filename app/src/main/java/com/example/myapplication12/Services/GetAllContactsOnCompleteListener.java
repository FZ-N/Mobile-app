package com.example.myapplication12.Services;

import androidx.annotation.NonNull;

import com.example.myapplication12.Model.Personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class GetAllContactsOnCompleteListener implements OnCompleteListener<QuerySnapshot> {

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {

        if (task.isSuccessful()) {

            for (QueryDocumentSnapshot document : task.getResult()) {
                Personne p = document.toObject(Personne.class);
                //prs.add(p);
                //System.out.println("le nom ="+p.getNom());
                System.out.println("nomnomnmonmnmn ="+p.getNom());
            }
            // Get the query snapshot from the task result
            /*QuerySnapshot querySnapshot = task.getResult();

            if (querySnapshot != null) {

                // Get the contact list from the query snapshot
                P = querySnapshot.toObjects(Personne.class);
            }*/

        } else {

        }

    }
}
