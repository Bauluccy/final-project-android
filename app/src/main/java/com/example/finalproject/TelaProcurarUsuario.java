package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_procurar_usuario);

        pesquisarEmail = findViewById(R.id.textProcurarEmail);

    }

    public void iniciarPesquisa(View view) {
        String email = pesquisarEmail.getText().toString();

        Intent intent = new Intent(this, TelaEditarUsuario.class);
        intent.putExtra("Email",email);
        startActivity(intent);

    }
}