package com.example.myapplication12.Services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication12.Messagerie.Discussion;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import static android.content.Context.MODE_PRIVATE;

public class MyAdapterMessage extends RecyclerView.Adapter<MyAdapterMessage.MyViewHolder> {
    private LinkedList<Message> msgs;

    private Context context;
    private TextView nom;
    SharedPreferences pref;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    //private MyAdapterEven.OnNoteListener mOnNoteListener ;

    public MyAdapterMessage(LinkedList<Message> msgs, Context context) {
        this.msgs = new LinkedList<Message>();

        //????
        this.msgs.addAll(msgs);
        this.context = context;
        //this.mOnNoteListener=onNoteListener;
    }

    @Override
    public MyAdapterMessage.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.unmsg, parent, false);
        MyAdapterMessage.MyViewHolder vh = new MyAdapterMessage.MyViewHolder(itemLayoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapterMessage.MyViewHolder holder, int position) {
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        final Personne p1 = gson.fromJson(json, Personne.class);

        if (p1.getNom().equals(msgs.get(position).getPer_recus().get(0).getNom())) {
            holder.nom_e.setText(msgs.get(position).getPer_envoye().getNom());
            holder.nom_e.setBackgroundColor(Color.CYAN);
        } else {
            holder.nom_e.setText(msgs.get(position).getPer_recus().get(0).getNom());
        }
        //holder.nom_e2.setText(msgs.get(position).getPer_recus().get(0).getNom());
        Date d = msgs.get(position).getDate_msg();
        //Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(d);
        //System.out.println("Converted String: " + strDate);


        String ms = msgs.get(position).getContenu_msg();
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);*/
        holder.contenu.setText(ms.substring(0, Math.min(ms.length(), 50)) + ":" );
        holder.date_e.setText(strDate);

        //ahmed c'est le partenaire du session


        if (msgs.get(position).getPer_envoye().getNom().equals(p1.getNom()) && (context instanceof Discussion)) {
            holder.contenu.setBackgroundColor(Color.rgb(200, 200, 200));
        }
        //holder.
        //...
        // StorageReference storageReference = FirebaseStorage.getInstance().getReference(ps.get(position).getIma);
        //Glifrapp.with(context.l)
    }

    @Override
    public int getItemCount() {
        return msgs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nom_e, contenu,date_e, nom_e2;


        public Context context;
        com.example.myapplication12.Services.MyAdapter.OnNoteListener onNoteListener;


        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            this.context = context;
            nom_e = itemLayoutView.findViewById(R.id.Nomuser);
            date_e = itemLayoutView.findViewById(R.id.Msgdate);
            contenu = itemLayoutView.findViewById(R.id.Msguser);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            //onNoteListener.OnNoteClick(getAdapterPosition());

            contenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    SharedPreferences pref2;
                    Intent in = new Intent(v.getContext(), Discussion.class);
                    String m = nom_e.getText().toString();
                    //String m2 = nom_e2.getText().toString();

                    in.putExtra("nom_per_non_connecte", m);
                    //in.putExtra("nom_per_recu", m2);
                    /*pref2 = context.getApplicationContext().getSharedPreferences("nom_recu", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref2.edit();
                    editor.putString("nom_user_r", m);
                    editor.commit();*/
                    //context.getApplicationContext().getSharedPreferences("personne_recu", 0).edit().clear().commit();

                    v.getContext().startActivity(in);


                }
            });
            nom_e.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Intent in = new Intent(v.getContext(), Discussion.class);
                    String m = nom_e.getText().toString();
                    //String m2 = nom_e2.getText().toString();
                    in.putExtra("nom_per_non_connecte", m);
                    //in.putExtra("nom_per_recu", m2);
                    //context.getApplicationContext().getSharedPreferences("personne_recu", 0).edit().clear().commit();
                    v.getContext().startActivity(in);


                }
            });

            /*int itemPosition = RecyclerView.getChildLayoutPosition(v);
            String item = (String) List.get(itemPosition);
            Toast.makeText(context, item, Toast.LENGTH_LONG).show();*/

        }
    }
        /*public interface OnNoteListener {
            //pour detecter click et position
            void OnNoteClick(int position);
        }*/
}
