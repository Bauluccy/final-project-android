package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TelaProcurarUsuario extends AppCompatActivity {

    private EditText pesquisarEmail;
    private Button botaoPesquisar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String nomeUsuario;
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_procurar_usuario);

        pesquisarEmail = findViewById(R.id.textProcurarEmail);

    }

    public void iniciarPesquisa(View view) {

        pesquisarEmail.getText().toString();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

//        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
//        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
//                if(documentSnapshot != null){
//
//                }
//            }
//        });

    }
}