package com.example.projetoautenticaoandroidjava.Controllers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.Services.Operacoes;

public abstract class TelaFormCadastro extends AppCompatActivity implements Operacoes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_form_cadastro);
        inicializarComponentes();
        Observadores();
    }
}