package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;

public class TelaBloqDesbloq extends AppCompatActivity {

    private EditText loginUsuario;
    private CheckBox mandaTodosUsuarios;
    private Switch bloqueado;
    private TextInputEditText textoMensagem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_bloq_desbloq);

        loginUsuario = findViewById(R.id.editLoginDoUsuario);
        mandaTodosUsuarios = findViewById(R.id.checkTodos);
        bloqueado = findViewById(R.id.switchBloqueado);
        textoMensagem = findViewById(R.id.textMensagem);


        mandaTodosUsuarios.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(mandaTodosUsuarios.isChecked()){
                    bloqueado.setEnabled(false);
                    loginUsuario.setEnabled(false);
                }else{
                    bloqueado.setEnabled(true);
                    loginUsuario.setEnabled(true);
                }

            }
        });


    }


}