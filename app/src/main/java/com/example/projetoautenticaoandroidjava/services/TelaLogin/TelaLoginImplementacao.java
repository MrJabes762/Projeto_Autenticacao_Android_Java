package com.example.projetoautenticaoandroidjava.services.TelaLogin;

import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projetoautenticaoandroidjava.controllers.TelaLogin;
import com.example.projetoautenticaoandroidjava.data.FirebaseFirestoreRepository;
import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.services.Direcionador.DirecionadorDeLayout;
import com.example.projetoautenticaoandroidjava.services.TelaFormCadastro.TelaFormCadastroImplementacao;
import com.example.projetoautenticaoandroidjava.services.TelaPrincipal.TelaPrincipalImplementacao;
import com.example.projetoautenticaoandroidjava.model.Usuario;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class TelaLoginImplementacao extends TelaLogin {
    private Usuario user;
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
            try {
                verificarEAutenticarLogin();
            } catch (TelaLoginException e) {
                exibirMensagme(e.getMessage());
            }
        });
        getBtnCadastrar().setOnClickListener(v -> {
            irTelaFormCadastro();
        });
    }

    private void verificarEAutenticarLogin() throws TelaLoginException {
        setUser(new Usuario(
                getPegarEmail().getText().toString(),
                getPegarSenha().getText().toString()
        ));
        if (getUser().getEmail().isEmpty() || getUser().getSenha().isEmpty()) {
            throw new TelaLoginException("Preencha todos os campos");
        } else {
            autenticarUsuario(getUser())
                    .addOnCompleteListener(task -> {// depois da tarefa ser concluida
                        getProgressBar().setVisibility(alterarVisibilidade(getProgressBar()));
                        if (task.isSuccessful()) {// se deu sucesso
                            new Handler().postDelayed(this::irTelaPrincipal, 3000);
                        } else {// se nao conseguiu chama a exceção
                            if (task.getException() != null) {
                                exibirMensagme("Erro ao logar: ");
                            } else {
                                exibirMensagme("Erro desconhecido ao logar.");
                            }
                        }
                    });
        }
    }

    @Override
    protected void onStart() {// ciclo de vida do App
        super.onStart();
        if (usuarioAtual() != null){
            irTelaPrincipal();
        }
    }
    private FirebaseUser usuarioAtual(){
        return FirebaseFirestoreRepository.pegarUsuarioAtual();
    }
    private Task<AuthResult> autenticarUsuario(Usuario user) {// vai pasar a responsabilidade para o Firebase repository pra verificar o usuario e retorinar o resultado da execução
        return FirebaseFirestoreRepository.autenticarUsuario(user);
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
        exibirMensagme("Sucesso ao logar!");
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
    private void exibirMensagme(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }
    private Usuario getUser() {
        return user;
    }

    private void setUser(Usuario user) {
        this.user = user;
    }
}
