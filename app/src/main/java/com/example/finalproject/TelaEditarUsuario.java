package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TelaEditarUsuario extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText nomeUsuario, emailUsuario;
    private EditText entrada,intervalo, saida;
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_usuario);

        nomeUsuario = findViewById(R.id.editarNome);
        emailUsuario = findViewById(R.id.editarEmail);
        entrada = findViewById(R.id.editarEntrada);




    }

    public void confirmarEdit(View view) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    emailUsuario.setText(email);
                    nomeUsuario.setText(documentSnapshot.getString("Nome"));
                }
            }
        });
    }
}