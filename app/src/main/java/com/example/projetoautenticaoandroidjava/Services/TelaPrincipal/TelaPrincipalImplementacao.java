package com.example.projetoautenticaoandroidjava.Services.TelaPrincipal;

import com.example.projetoautenticaoandroidjava.Controllers.TelaPrincipal;
import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.Services.Direcionador.DirecionadorDeLayout;
import com.example.projetoautenticaoandroidjava.Services.TelaLogin.TelaLoginImplementacao;

public class TelaPrincipalImplementacao extends TelaPrincipal {
    @Override
    public void inicializarComponentes() {
        setEditTexs();
        setBtnDeslogar(findViewById(R.id.btnDeslogar));
    }

    private void setEditTexs() {
        setTextExibirEmail(findViewById(R.id.textExibirEmail));
        setTextExibirUsuairo(findViewById(R.id.textexibirUsuario));
    }

    @Override
    public void Observadores() {
        getBtnDeslogar().setOnClickListener(v -> {
            voltarParaLogin();
        });
    }

    private void voltarParaLogin() {
        DirecionadorDeLayout.irParaLayout(this, TelaLoginImplementacao.class);
        finish();
    }
}
