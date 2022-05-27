package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TelaSupervisor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_supervisor);
    }

    public void chamarCadastro(View view) {
        Intent intent = new Intent(getApplicationContext(), TelaCadastro.class);
        startActivity(intent);
    }

    public void chamarBloqDesb(View view) {
//        Intent intent = new Intent(getApplicationContext(), Tela)
    }

}