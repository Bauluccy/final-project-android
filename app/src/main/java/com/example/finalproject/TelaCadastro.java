package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TelaCadastro extends AppCompatActivity {

    private EditText cadEmail, cadSenha, cadConfirma, cadNome;
    private EditText editEntrada, editInter, editSaida;
    private Button btadastrar;
    private CheckBox ckSuper;
    private String[] mensagem = {
            "Usuario não encontrado, preencha os próximos campos!",
            "Usuário encontrado!" ,
            "Email ou senha inválidos! Tente novamente!!" ,
            "Por favor, digite um email válido para liberar os próximos campos!",
            "Preencha todos os campos!!!",
            "Usuario cadastrado!!!"};

    String usuarioID;

    private FirebaseAuth autentication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        
        cadEmail = findViewById(R.id.cadastrarEmail);
        cadSenha = findViewById(R.id.cadastrarSenha);
        cadConfirma = findViewById(R.id.cadastrarConfirma);
        cadNome = findViewById(R.id.cadastrarNome);

        editEntrada = findViewById(R.id.cadastrarEntrada);
        editInter = findViewById(R.id.cadastrarInter);
        editSaida = findViewById(R.id.cadastrarSaida);

        ckSuper = findViewById(R.id.checkSuper);

        btadastrar = findViewById(R.id.botaoEditarConfirmar);

//        cadEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//
//                if (!cadEmail.isFocused()){
//                    String verificarEmail = cadEmail.getText().toString();
//                    String verificarNome = cadNome.getText().toString();
//                    String verificarSenha = cadSenha.getText().toString();
//                    String verificarConfirma = cadConfirma.getText().toString();
//
////                    cadSenha.setEnabled(true);
////                    cadConfirma.setEnabled(true);
////                    editEntrada.setEnabled(true);
////                    editInter.setEnabled(true);
////                    editSaida.setEnabled(true);
////                    ckSuper.setEnabled(true);
////                    btadastrar.setEnabled(true);
//                }
//            }
//        });

    }

    public void cadastrarUsuario(View view) {

        String verificarEmail = cadEmail.getText().toString();
        String verificarNome = cadNome.getText().toString();
        String verificarSenha = cadSenha.getText().toString();
        String verificarConfirma = cadConfirma.getText().toString();
        String verificarEntrada = editEntrada.getText().toString();
        String verificarInter = editInter.getText().toString();
        String verificarSaida = editSaida.getText().toString();


        if(verificarEmail.isEmpty() || verificarNome.isEmpty() || verificarSenha.isEmpty() || verificarConfirma.isEmpty() || verificarEntrada.isEmpty() || verificarInter.isEmpty() || verificarSaida.isEmpty())
        {
            MostrarMensagem(view, mensagem[4]);

        }else{
            CadastrarFirebase(view);
        }

    }


    

    private void CadastrarFirebase(View view) {
        String verificarEmail = cadEmail.getText().toString();
        String verificarSenha = cadSenha.getText().toString();
        String verificarConfirma = cadConfirma.getText().toString();

        if (verificarSenha.equals(verificarConfirma)) {

            autentication =  FirebaseAuth.getInstance();

            autentication.getInstance().createUserWithEmailAndPassword(verificarEmail, verificarSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                        String erro;

                    if (task.isSuccessful()) {

                        SalvarDadosUsuario();

                        MostrarMensagem(view, mensagem[5]);
                    } else {

                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            erro = "Senha mínima de 6 caracteres!";

                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            erro = "Email inválido, tente novamente!";

                        } catch (Exception e) {
                            erro = "Erro ao cadastrar usuário.";
                        }

                        MostrarMensagem(view, erro);

                    }

                }
            });
        }else{
            MostrarMensagem(view, "As senhas não coincidem");
        }
    }


    private void SalvarDadosUsuario() {
        String usuarioNome = cadNome.getText().toString();
        String usuarioEntrada = editEntrada.getText().toString();
        String usuarioInter = editInter.getText().toString();
        String usuarioSaida = editSaida.getText().toString();
        String autenticacao = autentication.getUid().toString();
        String usuarioEmail = cadEmail.getText().toString();
        int supervisor;

        if(ckSuper.isChecked()){
            supervisor = 1;
        }else{
            supervisor = 0;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> dadosUsuarios= new HashMap<>();

        dadosUsuarios.put("Email",usuarioEmail);
        dadosUsuarios.put("Nome", usuarioNome);
        dadosUsuarios.put("HorarioEntrada", usuarioEntrada);
        dadosUsuarios.put("HorarioIntervalo", usuarioInter);
        dadosUsuarios.put("HorarioSaida", usuarioSaida);
        dadosUsuarios.put("Supervisor",supervisor);
        dadosUsuarios.put("ID",autenticacao);


        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document();

        documentReference.set(dadosUsuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db", "Sucesso ao salvar os dados do usuario.");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error", "Erro ao salvar os dados." + e.toString());
            }
        });

    }
    private void MostrarMensagem(View view,String string) {
        Snackbar snackbar = Snackbar.make(view, string, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(Color.BLACK);
        snackbar.setTextColor(Color.WHITE);
        snackbar.show();
    }
}

