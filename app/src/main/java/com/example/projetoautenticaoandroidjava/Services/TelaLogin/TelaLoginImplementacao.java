package com.example.projetoautenticaoandroidjava.Services.TelaLogin;

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

    @Override
    public void Observadores() {
        getBtnEntrar().setOnClickListener(v -> {
            irTelaPrincipal();
        });
        getBtnCadastrar().setOnClickListener(v -> {
            irTelaFormCadastro();
        });
    }

    private void irTelaFormCadastro() { // verificação se o Hash Map esta vazio se não ja inicializa
        if (getCachedLayoutFormCadastro()!= null) {
            DirecionadorDeLayout.irParaLayout(this, getCachedLayoutFormCadastro());
        } else {
            setLayoutClass(TelaFormCadastroImplementacao.class);
            DirecionadorDeLayout.irParaLayout(this, getLayoutClass());
            getLayoutCache().put(R.layout.activity_tela_form_cadastro, getLayoutClass());// Armazena o layout em cache
        }
        finish();
    }

    private void irTelaPrincipal() {
        if (getCachedLayoutPrincipal() != null){
            DirecionadorDeLayout.irParaLayout(this, getCachedLayoutPrincipal());
        } else {
            setLayoutClass(TelaPrincipalImplementacao.class);
            DirecionadorDeLayout.irParaLayout(this, getLayoutClass());
            getLayoutCache().put(R.layout.activity_tela_principal,getLayoutClass());// Armazena o layout em cache
        }
        finish();
    }
}
