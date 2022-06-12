package com.example.appfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        List<Usuario> listaUsuarios = new ArrayList<>();;

        db = FirebaseFirestore.getInstance();
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                listaUsuarios.add(new Usuario(document.get("username").toString(), document.get("password").toString(), document.get("nombres").toString()));
                            }
                        } else {
                            Log.w("FIRESTORE", "Error getting documents.", task.getException());
                        }

                        UsuarioAdapter adapter = new UsuarioAdapter(listaUsuarios, getApplicationContext());
                        RecyclerView miRecycler = findViewById(R.id.RecyclerUsuarios);
                        miRecycler.setHasFixedSize(true);
                        miRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        miRecycler.setAdapter(adapter);
                    }
                });


    }
}