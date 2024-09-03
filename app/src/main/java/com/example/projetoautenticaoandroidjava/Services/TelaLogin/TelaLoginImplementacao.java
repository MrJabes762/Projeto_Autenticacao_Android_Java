package com.example.projetoautenticaoandroidjava.Services.TelaLogin;

import android.view.View;
import android.widget.ProgressBar;

import com.example.projetoautenticaoandroidjava.Controllers.TelaLogin;
import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.Services.Direcionador.DirecionadorDeLayout;
import com.example.projetoautenticaoandroidjava.Services.TelaFormCadastro.TelaFormCadastroImplementacao;
import com.example.projetoautenticaoandroidjava.Services.TelaPrincipal.TelaPrincipalImplementacao;

import java.util.HashMap;

public class TelaLoginImplementacao extends TelaLogin {
    @Override
    public void inicializarComponentes() {
        setHashMapsECaches();
        setEditTexts();
        setBtns();
        setProgressBar(findViewById(R.id.Progressbar));
    }

    private void setBtns() {
        setBtnEntrar(findViewById(R.id.btnEntrar));
        setBtnCadastrar(findViewById(R.id.btnCadastrar));
    }

    private void setEditTexts() {
        setPegarEmail(findViewById(R.id.pegarEmail));
        setPegarSenha(findViewById(R.id.pegarSenha));
    }

    private void setHashMapsECaches() {
        setLayoutCache(new HashMap<>());
        setCachedLayoutPrincipal(null);
        setCachedLayoutFormCadastro(null);
        setLayoutClass(null);
    }
    private int alterarVisibilidade (ProgressBar progress){
        return progress.getVisibility() == View.VISIBLE ? View.GONE: View.VISIBLE;
    }

    @Override
    public void Observadores() {
        getBtnEntrar().setOnClickListener(v -> {
            irTelaPrincipal();
        });
        getBtnCadastrar().setOnClickListener(v -> {
            irTelaFormCadastro();
        });
    }

    private void irTelaFormCadastro() { // verificação se o Cache esta vazio se não ja inicializa
        if (getCachedLayoutFormCadastro()!= null) {
            DirecionadorDeLayout.irParaLayout(this, getLayoutCache().get(R.layout.activity_tela_form_cadastro));
        } else {
            setLayoutClass(TelaFormCadastroImplementacao.class);
            setCachedLayoutPrincipal(getLayoutClass());
            getLayoutCache().put(R.layout.activity_tela_form_cadastro, getCachedLayoutPrincipal());// Armazena o layout em cache
            DirecionadorDeLayout.irParaLayout(this, getLayoutCache().get(R.layout.activity_tela_form_cadastro));
        }
        finish();
    }

    private void irTelaPrincipal() {
        if (getCachedLayoutPrincipal() != null){
            DirecionadorDeLayout.irParaLayout(this, getLayoutCache().get(R.layout.activity_tela_principal));
        } else {
            setLayoutClass(TelaPrincipalImplementacao.class);
            setCachedLayoutFormCadastro(getLayoutClass());
            getLayoutCache().put(R.layout.activity_tela_principal,getCachedLayoutFormCadastro());// Armazena o layout em cache
            DirecionadorDeLayout.irParaLayout(this, getLayoutCache().get(R.layout.activity_tela_principal));
        }
        finish();
    }
}
