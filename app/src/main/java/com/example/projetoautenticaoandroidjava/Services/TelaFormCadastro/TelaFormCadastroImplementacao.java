package com.example.projetoautenticaoandroidjava.Services.TelaFormCadastro;

import com.example.projetoautenticaoandroidjava.Controllers.TelaFormCadastro;
import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.Services.Direcionador.DirecionadorDeLayout;
import com.example.projetoautenticaoandroidjava.Services.TelaLogin.TelaLoginImplementacao;

public class TelaFormCadastroImplementacao extends TelaFormCadastro {
    @Override
    public void inicializarComponentes() {
        setEditTexts();
        setBtnCadastrarForm(findViewById(R.id.btnCadstrarForm));
    }

    private void setEditTexts() {
        setPegarNomeCadastro(findViewById(R.id.pegarNomeCadastro));
        setPegarEmailCadastro(findViewById(R.id.pegarEmailCadastro));
        setPegarSenhaCadastro(findViewById(R.id.pegarSenhaCadastro));
    }

    @Override
    public void Observadores() {
        getBtnCadastrarForm().setOnClickListener(v -> {
            voltarParaTelaLogin();
        });
    }

    private void voltarParaTelaLogin() {
        DirecionadorDeLayout.irParaLayout(this, TelaLoginImplementacao.class);
        finish();
    }
}
