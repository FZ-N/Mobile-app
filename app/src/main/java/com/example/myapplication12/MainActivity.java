package com.example.myapplication12;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MainActivity extends Activity {

    private TextView mytext;
    //private StorageReference mStorageRef;
    private Button b1,b2;
    private ImageView i1;
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    //SharedPreferences shared = getApplicationContext().getSharedPreferences("YourSessionName", MODE_PRIVATE);

    private final int PICK_IMAGE_REQUEST = 71;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String var="ahmed";
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.btnChoose);
        b2 = (Button) findViewById(R.id.btnUpload);
        i1 = (ImageView) findViewById(R.id.imgView);

        //storage = FirebaseStorage.getInstance();
        storageReference =  FirebaseStorage.getInstance().getReference();

        //StorageReference storageReference = FirebaseStorage.getInstance().getReference("evenexm2.jpg");
        //StorageReference storageReference = storage.getReference();
        StorageReference pathReference = storageReference.child("/evenexm2.jpg");
        //StorageReference photoReference= storageReference.child("evenexm2.jpg");
        //StorageReference gsReference = storage.getReferenceFromUrl("gs://iwim-e513e.appspot.com/evenexm2.jpg");

        //Glide.with(this.getBaseContext() /* context */)
          //      .load(storageReference)
            //    .into(i1);



        File localFile = null;
        try {
            localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        final File finalLocalFile = localFile;
        pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Bitmap bitmap = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                i1.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });



        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
             //   downloadfile("test3",".jpg", DIRECTORY_PICTURES,uri.toString());
                //new DownloadTask(MainActivity.this, uri.toString());
                //PRDownloader.initialize(getApplicationContext());
                //PRDownloader.download(uri.toString(), "images", "test2");
                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        /*mStorageRef = FirebaseStorage.getInstance().getReference();

        Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });*/




        //se connecter avec la base de donnée + getref(cree ou ecrire dans le child
        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("exemple").child("tese1");
        //reference.setValue("ok");



        /*db.collection("test2").document("testxd")
                .update(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });*/

        /*final DocumentReference sfDocRef = db.collection("test2").document("testxd");

        db.runTransaction(new Transaction.Function<Double>() {
            @Override
            public Double apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                double newPopulation = snapshot.getDouble("born") + 1;
                if (newPopulation <= 2000) {
                    transaction.update(sfDocRef, "born", newPopulation);
                    return newPopulation;
                } else {
                    throw new FirebaseFirestoreException("Population too high",
                            FirebaseFirestoreException.Code.ABORTED);
                }
            }
        }).addOnSuccessListener(new OnSuccessListener<Double>() {
            @Override
            public void onSuccess(Double result) {

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });*/





        /*
        //realtime database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        */

        mytext=(TextView)findViewById(R.id.t);

        mytext.setText(var);
        mytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Personne p1 = new Personne("ahmed", "ahmed", "ahmed@gcom", "060666", "Prof");

                //se connecter avec la base de donnée + getref(cree ou ecrire dans le child
                //DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("tese1");
                //reference1.setValue("ok");
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference docRef = db.collection("Message");
                Methodes_msg_evt_.GetMessages(p1).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Message p = document.toObject(Message.class);
                                //String nom = p.getPer_recus().get(0).getEmail();
                                String nom="testt";
                                mytext = (TextView) findViewById(R.id.t);

                                mytext.setText(nom);
                            }
                        } else {

                        }


                        Intent in = new Intent(MainActivity.this, Login.class);
                        startActivity(in);
                    }
                });
            }});







    }
    private void chooseImage() {
        Intent intent = new Intent();
        //intent.setType("image/*");
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                i1.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("files/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
    public  void downloadfile( String filename, String fileextension, String destinationderectory, String url){
        DownloadManager downloadManager =(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Uri uri =Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        request.setDestinationInExternalFilesDir(this,destinationderectory,filename+fileextension);
        downloadManager.enqueue(request);
    }
}