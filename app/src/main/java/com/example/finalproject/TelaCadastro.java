package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class TelaCadastro extends AppCompatActivity {

    private EditText editEmail, editSenha, editConfirma, editNome;
    private EditText editEntrada, editInter, editSaida;
    private Button btadastrar;
    private CheckBox ckSuper;
    private String[] mensagem = {
            "Usuario não encontrado, preencha os próximos campos!",
            "Usuário encontrado!" ,
            "Email ou senha inválidos! Tente novamente!!" ,
            "Por favor, digite um email válido para liberar os próximos campos!",
            "Preencha todos os campos!!!",
            "Usuario cadastrado!!!",
            "Senha mínima de 6 caracteres!",
            "Email inválido, tente novamente!"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        
        editEmail = findViewById(R.id.editarEmail);
        editSenha = findViewById(R.id.editarSenha);
        editConfirma = findViewById(R.id.editarConfirma);
        editNome = findViewById(R.id.editarNome);

        editEntrada = findViewById(R.id.editarEntrada);
        editInter = findViewById(R.id.editarInter);
        editSaida = findViewById(R.id.editarSaida);

        ckSuper = findViewById(R.id.checkSupervisor);

        btadastrar = findViewById(R.id.botaoCadastrar);

        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {




                if (!editEmail.isFocused()){
                    String verificarEmail = editEmail.getText().toString();
                    String verificarNome = editNome.getText().toString();
                    String verificarSenha = editSenha.getText().toString();
                    String verificarConfirma = editConfirma.getText().toString();



                    editSenha.setEnabled(true);
                    editConfirma.setEnabled(true);
                    editEntrada.setEnabled(true);
                    editInter.setEnabled(true);
                    editSaida.setEnabled(true);
                    ckSuper.setEnabled(true);
                    btadastrar.setEnabled(true);


                }
            }
        });

    }

    public void cadastrarUsuario(View view) {

        String verificarEmail = editEmail.getText().toString();
        String verificarNome = editNome.getText().toString();
        String verificarSenha = editSenha.getText().toString();
        String verificarConfirma = editConfirma.getText().toString();
        String verificarEntrada = editEntrada.getText().toString();
        String verificarInter = editInter.getText().toString();
        String verificarSaida = editSaida.getText().toString();

        if(verificarEmail.isEmpty() || verificarNome.isEmpty() || verificarSenha.isEmpty() || verificarConfirma.isEmpty() || verificarEntrada.isEmpty() || verificarInter.isEmpty() || verificarSaida.isEmpty())
        {
            Snackbar snackbar = Snackbar.make(view, mensagem[4], Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(Color.BLACK);
            snackbar.setTextColor(Color.WHITE);
            snackbar.show();

        }else{

            CadastrarFirebase(view);

//            Snackbar snackbar = Snackbar.make(view, mensagem[5], Snackbar.LENGTH_SHORT);
//            snackbar.setBackgroundTint(Color.BLACK);
//            snackbar.setTextColor(Color.WHITE);
//            snackbar.show();


        }

    }


    public void verificaLogin(EditText login){


    }

    private void CadastrarFirebase(View view) {
        String verificarEmail = editEmail.getText().toString();
        String verificarSenha = editSenha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(verificarEmail,verificarSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Snackbar snackbar = Snackbar.make(view, mensagem[5], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.BLACK);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }else{
                    String erro;

                    try {
                        throw task.getException();
                    }
                    catch(FirebaseAuthWeakPasswordException e){
                        erro = mensagem[6];

                    }catch (FirebaseAuthEmailException e){

                    }
                }

            }
        });

    }
}