package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class TelaLogin extends AppCompatActivity {
    private EditText logEmail;
    private EditText logSenha;
    private Button btEntrar;
    private ProgressBar progressBar;
    private String[] mensagem = {
            "Preencha todos os campos",
            "Email ou senha inválido"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        logEmail = findViewById(R.id.loginEmail);
        logSenha = findViewById(R.id.loginSenha);
        btEntrar = findViewById(R.id.botaoLogar);
        progressBar = findViewById(R.id.barraDeProgresso);



    }

    public void login(View view) {
         String email = logEmail.getText().toString();
         String senha = logSenha.getText().toString();

         if (email.isEmpty() || senha.isEmpty()){
             Snackbar snackbar = Snackbar.make(view, mensagem[0], Snackbar.LENGTH_SHORT);
             snackbar.setBackgroundTint(Color.BLACK);
             snackbar.setTextColor(Color.WHITE);
             snackbar.show();
         }else{
             AutenticarUsuario(view);
         }

    }

    private void AutenticarUsuario(View view) {
        String email = logEmail.getText().toString();
        String senha = logSenha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressBar.setVisibility((View.VISIBLE));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipalSupervisor();
                        }
                    },3000);
                }else{
                    String erro;
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "Usuario ou senha incorretos!!!";
                    } catch (Exception e) {
                        erro = "Erro ao tentar logar...";
                    }

                    Snackbar snackbar = Snackbar.make(view,erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.BLACK);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if (usuarioAtual != null){
            TelaPrincipalSupervisor();
        }
    }

    private void TelaPrincipalSupervisor(){
        Intent intent = new Intent(getApplicationContext(),TelaSupervisor.class);
        startActivity(intent);
        finish();
    }
}