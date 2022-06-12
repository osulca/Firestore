package com.example.appfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tvRespuesta;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRespuesta = findViewById(R.id.tvRespuesta);
        tvRespuesta.setText("");

        db = FirebaseFirestore.getInstance();
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                tvRespuesta.append(
                                        "USUARIO: " + document.get("username") + "\n" +
                                        "NOMBRES: " + document.get("nombres") + "\n\n"
                                );
                            }
                        } else {
                            Log.w("FIRESTORE", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void siguiente(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public void EliminarFirestore(View view) {
        String id = "7V5oME1157z580Q34LK9";
        db.collection("usuarios")
                .document(id)
                .delete()
                .addOnSuccessListener(unused ->
                        Toast.makeText(getApplicationContext(), "Documento Eliminado", Toast.LENGTH_SHORT).show()
                )
                .addOnFailureListener(e ->
                        Log.e("ERROR:", e.getMessage())
                );
    }

    public void ActualizarFirestore(View view) {
        String id = "pSlo8UTrS7IQGP9E6k42";
        String nombre = "Pepito Perez";
        String username = "pperez";
        String password = "7V5oME1157z580Q34LK9";

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("username", username);
        usuario.put("password", password);
        usuario.put("nombres", nombre);

        db.collection("usuarios")
                .document(id)
                .set(usuario)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Documento Actualizado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("ERROR:", e.getMessage());
                    }
                });
    }

    public void MostrarFirestore(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
        startActivity(intent);
    }
}