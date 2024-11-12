package com.example.projetoautenticaoandroidjava.services.TelaPrincipal;

import android.content.Context;

import com.example.projetoautenticaoandroidjava.controllers.TelaPrincipal;
import com.example.projetoautenticaoandroidjava.data.FirebaseFirestoreRepository;
import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.services.Direcionador.DirecionadorDeLayout;
import com.example.projetoautenticaoandroidjava.services.TelaLogin.TelaLoginImplementacao;
import com.example.projetoautenticaoandroidjava.model.Usuario;
import com.example.projetoautenticaoandroidjava.services.exibirMensagem.Exibir;

public class TelaPrincipalImplementacao extends TelaPrincipal {
    private String email;

    @Override
    public void observadores() {
        getBtnDeslogar().setOnClickListener(v -> {
            FirebaseFirestoreRepository.delogarUsuario();
            voltarParaLogin();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Context context = this;
        String usuarioId = FirebaseFirestoreRepository.pegarUsuarioAtual().getUid();
        Usuario.setId(usuarioId);
        setEmail(FirebaseFirestoreRepository.pegarUsuarioAtual().getEmail());
        FirebaseFirestoreRepository.pegarDados(usuarioId, new Callback<String>() {
            @Override
            public void onSuccess(String nome) {
                if (nome != null && !nome.isEmpty()) {
                    getTextExibirUsuario().setText(nome);
                } else {
                    Exibir.exibirMensagem(context, "Usuário não encontrado");
                }
                getTextExibirEmail().setText(getEmail());
            }

            @Override
            public void onError(String error) {
                Exibir.exibirMensagem(context, "Erro ao buscar dados: " + error);
            }
        });
    }

    private void voltarParaLogin() {
        DirecionadorDeLayout.irParaLayout(this, TelaLoginImplementacao.class);
        finish();
    }

    private String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }
}
