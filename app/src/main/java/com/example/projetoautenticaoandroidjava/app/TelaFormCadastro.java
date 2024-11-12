
package com.example.projetoautenticaoandroidjava.controllers;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.services.Operacoes;

public abstract class TelaFormCadastro extends AppCompatActivity implements Operacoes {
    // UI Elements
    private EditText nomeCadastroEditText;
    private EditText emailCadastroEditText;
    private EditText senhaCadastroEditText;
    private AppCompatButton btnCadastrarForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_form_cadastro);
        inicializarComponentes();
        configurarListeners();
    }

    @Override
    public void inicializarComponentes() {
        nomeCadastroEditText = findViewById(R.id.pegarNomeCadastro);
        emailCadastroEditText = findViewById(R.id.pegarEmailCadastro);
        senhaCadastroEditText = findViewById(R.id.pegarSenhaCadastro);
        btnCadastrarForm = findViewById(R.id.btnCadstrarForm);
    }

    protected EditText getNomeCadastroEditText() {
        return nomeCadastroEditText;
    }

    protected EditText getEmailCadastroEditText() {
        return emailCadastroEditText;
    }

    protected EditText getSenhaCadastroEditText() {
        return senhaCadastroEditText;
    }

    protected AppCompatButton getBtnCadastrarForm() {
        return btnCadastrarForm;
    }
}
