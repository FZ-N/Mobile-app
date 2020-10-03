package com.example.myapplication12.Services;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication12.Model.Cours;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.Editcours;
import com.example.myapplication12.Scolarité.Listcours;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.util.LinkedList;

import static android.content.Context.MODE_PRIVATE;
import static android.os.Environment.DIRECTORY_PICTURES;

public class MyAdapterCours extends RecyclerView.Adapter<MyAdapterCours.MyViewHolder> {
    private LinkedList<Cours> cours;
    private Context context;
    private TextView nom;
    StorageReference storageReference;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    //private MyAdapterEven.OnNoteListener mOnNoteListener ;

    public MyAdapterCours(LinkedList<Cours> cours , Context context ){
        this.cours=new LinkedList<Cours>();

        //????
        this.cours.addAll(cours);
        this.context=context;
        //this.mOnNoteListener=onNoteListener;
    }
    @Override
    public MyAdapterCours.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemLayoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.uncours , parent,false);
        MyAdapterCours.MyViewHolder vh = new MyAdapterCours.MyViewHolder(itemLayoutView);
        return  vh;
    }

    @Override
    public void onBindViewHolder(MyAdapterCours.MyViewHolder holder, int position){
        holder.nom_e.setText(cours.get(position).getNom_cours());
        holder.matiere.setText(cours.get(position).getPrf().getNom());

        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        Personne p = gson.fromJson(json, Personne.class);

        if(p.getType().equals("Etudiant") || p.getType().equals("Delegue") || (!cours.get(position).getPrf().getNom().equals(p.getNom()))){
            holder.edit.setVisibility(View.INVISIBLE);
            holder.delete.setVisibility(View.INVISIBLE);

        }


        //holder.
        //...
        // StorageReference storageReference = FirebaseStorage.getInstance().getReference(ps.get(position).getIma);
        //Glifrapp.with(context.l)
    }

    @Override
    public int getItemCount(){
        return cours.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public  TextView nom_e,description,matiere;
        public ImageView delete;
        public ImageView edit;
        public ImageView telecharger;

        public Context context;
        com.example.myapplication12.Services.MyAdapter.OnNoteListener onNoteListener;



        public MyViewHolder(View itemLayoutView){
            super(itemLayoutView);
            this.context=context;
            nom_e=itemLayoutView.findViewById(R.id.cours_nom);
            matiere=itemLayoutView.findViewById(R.id.cours_matiere);
            delete=itemLayoutView.findViewById(R.id.delet_cours);
            edit=itemLayoutView.findViewById(R.id.edit_cours);
            telecharger=itemLayoutView.findViewById(R.id.download_cours);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            //onNoteListener.OnNoteClick(getAdapterPosition());

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(), Editcours.class);
                    String m=nom_e.getText().toString();
                    in.putExtra("nom_cours", m);

                    Toast.makeText(v.getContext().getApplicationContext(),"Le cours :"+m,Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(in);
                }});
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    String nom11=nom_e.getText().toString();
                    Methodes_cours.deleteCours(nom11).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            //Personne p = new Personne();
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String idd= document.getId();
                                    Methodes_cours.getUsersCollection().document(idd).delete();

                                    StorageReference storageReference;
                                    storageReference =  FirebaseStorage.getInstance().getReference();
                                    StorageReference pathReference = storageReference.child("cours/"+nom_e.getText().toString());
                                    pathReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // File deleted successfully
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Uh-oh, an error occurred!
                                        }
                                    });

                                    Intent in=new Intent(v.getContext(), Listcours.class);
                                    v.getContext().startActivity(in);


                                    Toast.makeText(v.getContext().getApplicationContext(),"Votre modification a été enregistré avec succès :",Toast.LENGTH_SHORT).show();
                                    //p = document.toObject(Personne.class);
                                    //ps.add(p);
                                    //System.out.println("le nom ="+p.getNom());
                                }
                            }
                        }
                    });
                }});
            telecharger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    StorageReference storageReference;
                    storageReference =  FirebaseStorage.getInstance().getReference();
                    StorageReference pathReference = storageReference.child("cours/"+nom_e.getText().toString());
                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadfile(v.getContext(),nom_e.getText().toString(), ".pdf", DIRECTORY_PICTURES, uri.toString());

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                        }
                    });
                }});
            /*int itemPosition = RecyclerView.getChildLayoutPosition(v);
            String item = (String) List.get(itemPosition);
            Toast.makeText(context, item, Toast.LENGTH_LONG).show();*/

        }
    }
    public static void downloadfile(Context context,String filename, String fileextension, String destinationderectory, String url){
        DownloadManager downloadManager =(DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
        Uri uri =Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        request.setDestinationInExternalFilesDir(context,destinationderectory,filename+fileextension);
        downloadManager.enqueue(request);
    }
}
