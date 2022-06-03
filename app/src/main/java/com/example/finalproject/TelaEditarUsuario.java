package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class TelaEditarUsuario extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText nomeUsuario, emailUsuario;
    private EditText entrada,intervalo, saida;
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_usuario);

        nomeUsuario = findViewById(R.id.cadastrarNome);
        emailUsuario = findViewById(R.id.cadastrarEmail);
        entrada = findViewById(R.id.cadastrarEntrada);




    }

    public void confirmarEdit(View view) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        String nome = nomeUsuario.getText().toString();
        String horEntrada = entrada.getText().toString();

        String receberEmail = getIntent().getExtras().toString();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("Usuarios")
                .whereEqualTo("Email",receberEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            if()
                            nome.
                        }
                    }
                });



//        DocumentReference documentReference = db.collection("Usuarios").whereEqualTo("ID",usuarioID);
//        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
//                if(documentSnapshot != null){
//                    emailUsuario.setText(email);
//                    nomeUsuario.setText(documentSnapshot.getString("Nome"));
//                }
//            }
//        });
    }
}