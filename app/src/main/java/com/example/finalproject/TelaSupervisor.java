package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class TelaSupervisor extends AppCompatActivity {
    private Button botaoDeslog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_supervisor);

        botaoDeslog = findViewById(R.id.botaoLogout);
    }

    public void chamarCadastro(View view) {
        Intent intent = new Intent(getApplicationContext(), TelaEditarUsuario.class);
        startActivity(intent);
    }

    public void chamarBloqDesb(View view) {
//        Intent intent = new Intent(getApplicationContext(), Tela)
    }

    public void botaoDeslogar(View view) {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(TelaSupervisor.this, TelaLogin.class);
        startActivity(intent);
        finish();
    }
}