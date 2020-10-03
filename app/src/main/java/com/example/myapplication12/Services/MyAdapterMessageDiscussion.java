package com.example.myapplication12.Services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication12.Messagerie.Discussion;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import static android.os.Environment.DIRECTORY_PICTURES;
import static com.example.myapplication12.Services.MyAdapterCours.downloadfile;

public class MyAdapterMessageDiscussion extends  RecyclerView.Adapter<MyAdapterMessageDiscussion.MyViewHolder>{
    private LinkedList<Message> msgs;
    private Context context;
    private TextView nom;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    //private MyAdapterEven.OnNoteListener mOnNoteListener ;

    public MyAdapterMessageDiscussion(LinkedList<Message> msgs , Context context ){
        this.msgs=new LinkedList<Message>();

        //????
        this.msgs.addAll(msgs);
        this.context=context;
        //this.mOnNoteListener=onNoteListener;
    }
    @Override
    public MyAdapterMessageDiscussion.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemLayoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.unmsgd , parent,false);
        MyAdapterMessageDiscussion.MyViewHolder vh = new MyAdapterMessageDiscussion.MyViewHolder(itemLayoutView);
        return  vh;
    }

    @Override
    public void onBindViewHolder(MyAdapterMessageDiscussion.MyViewHolder holder, int position){
        //holder.nom_e.setText(msgs.get(position).getPer_envoye().getNom());
        String ms =msgs.get(position).getContenu_msg();
        //holder.contenu.setText(ms);
        String m = null, msgfinal = null;
        int i=ms.length()/40 ;
        /*for (int j = 0; j <i ; j++) {


            m=ms.substring(j*40, Math.min(ms.length(), 40));
            msgfinal+=m+"\n";

        }*/
        //msgfinal="test \"\n\" anothertest";

        holder.contenu.setText(msgs.get(position).getContenu_msg());

        Date d = msgs.get(position).getDate_msg();
        //Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(d);

        holder.date_e.setText(strDate);
        SharedPreferences pref1 = context.getApplicationContext().getSharedPreferences("personne_connecte", 0);
        Gson gson1 = new Gson();
        String json1 = pref1.getString("personne_c", "");
        final Personne p1 = gson1.fromJson(json1, Personne.class);

        if (msgs.get(position).getPer_envoye().getNom().equals(p1.getNom()) && (context instanceof Discussion)) {
            holder.contenu.setBackgroundColor(Color.rgb(200, 200, 200));
        }
        else{
            holder.image_msg2.setVisibility(View.INVISIBLE);
            holder.contenu.setGravity(Gravity.RIGHT);
        }

        String type_msg="";
        if(msgs.get(position).getType()!=null){
            type_msg=msgs.get(position).getType();
        }
        if(type_msg.equals("File")){
            holder.downloadfichier.setVisibility(View.VISIBLE);
            holder.contenu.setText("Fichier...");
        }


        //holder.
        //...
        // StorageReference storageReference = FirebaseStorage.getInstance().getReference(ps.get(position).getIma);
        //Glifrapp.with(context.l)
    }

    @Override
    public int getItemCount(){
        return msgs.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public  TextView nom_e,contenu,date_e;
        public ImageView image_msg2,downloadfichier;



        public Context context;
        com.example.myapplication12.Services.MyAdapter.OnNoteListener onNoteListener;



        public MyViewHolder(View itemLayoutView){
            super(itemLayoutView);
            this.context=context;
            nom_e=itemLayoutView.findViewById(R.id.Nomuser);
            date_e=itemLayoutView.findViewById(R.id.Msgdate);
            contenu=itemLayoutView.findViewById(R.id.Msguser);
            image_msg2=itemLayoutView.findViewById(R.id.imagemsg2);
            downloadfichier=itemLayoutView.findViewById(R.id.download_fichier);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            //onNoteListener.OnNoteClick(getAdapterPosition());

            contenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* Intent in=new Intent(v.getContext(), Discussion.class);
                    String m=nom_e.getText().toString();
                    in.putExtra("nom_per_envoye", m);*/


                }});

            downloadfichier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    StorageReference storageReference;
                    storageReference =  FirebaseStorage.getInstance().getReference();
                    final StorageReference pathReference = storageReference.child("messages/"+date_e.getText().toString());
                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            //downloadfile(v.getContext(),date_e.getText().toString(), ".pdf", DIRECTORY_PICTURES, uri.toString());
                            downloadfile(v.getContext(),date_e.getText().toString(), "", DIRECTORY_PICTURES, uri.toString());

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
}
