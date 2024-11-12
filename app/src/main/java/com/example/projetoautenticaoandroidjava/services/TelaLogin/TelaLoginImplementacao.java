package com.example.projetoautenticaoandroidjava.services.TelaLogin;

import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.projetoautenticaoandroidjava.controllers.TelaLogin;
import com.example.projetoautenticaoandroidjava.data.FirebaseFirestoreRepository;
import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.services.Direcionador.DirecionadorDeLayout;
import com.example.projetoautenticaoandroidjava.services.TelaFormCadastro.FormCadastroException;
import com.example.projetoautenticaoandroidjava.services.TelaPrincipal.TelaPrincipalImplementacao;
import com.example.projetoautenticaoandroidjava.model.Usuario;
import com.example.projetoautenticaoandroidjava.services.exibirMensagem.Exibir;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class TelaLoginImplementacao extends TelaLogin {
    private Usuario user;

    private int alterarVisibilidade(ProgressBar progress) {
        return progress.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
    }

    @Override
    public void observadores() {
        getBtnEntrar().setOnClickListener(v -> {
            try {
                verificarEAutenticarLogin();
            } catch (TelaLoginException | FormCadastroException e) {
                Exibir.exibirMensagem(this, e.getMessage());
            }
        });
        getBtnCadastrar().setOnClickListener(v -> irTelaFormCadastro());
    }

    private void verificarEAutenticarLogin() throws TelaLoginException, FormCadastroException {
        setUser(new Usuario(getPegarEmail().getText().toString(), getPegarSenha().getText().toString()));
        if (getUser().getEmail().isEmpty() || getUser().getSenha().isEmpty()) {
            throw new TelaLoginException("Preencha todos os campos");
        } else {
            autenticarUsuario(getUser()).addOnCompleteListener(task -> {
                getProgressBar().setVisibility(alterarVisibilidade(getProgressBar()));
                if (task.isSuccessful()) {
                    new Handler().postDelayed(this::irTelaPrincipal, 3000);
                } else {
                    Exibir.exibirMensagem(this, "Erro ao logar: " + (task.getException() != null ? task.getException().getMessage() : "Erro desconhecido"));
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseFirestoreRepository.pegarUsuarioAtual() != null) {
            irTelaPrincipal();
        }
    }

    private Task<AuthResult> autenticarUsuario(Usuario user) {
        return FirebaseFirestoreRepository.autenticarUsuario(user);
    }

    private void irTelaFormCadastro() {
        if (getCachedLayoutFormCadastro() != null) {
            DirecionadorDeLayout.irParaLayout(this, getCachedLayoutFormCadastro());
        } else {
            setCachedLayoutFormCadastro(TelaFormCadastroImplementacao.class);
            DirecionadorDeLayout.irParaLayout(this, getCachedLayoutFormCadastro());
        }
        finish();
    }

    private void irTelaPrincipal() {
        Exibir.exibirMensagem(this, "Sucesso ao Logar");
        if (getCachedLayoutPrincipal() != null) {
            DirecionadorDeLayout.irParaLayout(this, getCachedLayoutPrincipal());
        } else {
            setCachedLayoutPrincipal(TelaPrincipalImplementacao.class);
            DirecionadorDeLayout.irParaLayout(this, getCachedLayoutPrincipal());
        }
        finish();
    }

    private Usuario getUser() {
        return user;
    }

    private void setUser(Usuario user) {
        this.user = user;
    }
}
