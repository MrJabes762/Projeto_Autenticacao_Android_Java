package com.example.projetoautenticaoandroidjava.services.TelaFormCadastro;

import android.widget.Toast;

import com.example.projetoautenticaoandroidjava.controllers.TelaFormCadastro;
import com.example.projetoautenticaoandroidjava.data.FirebaseFirestoreRepository;
import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.services.Direcionador.DirecionadorDeLayout;
import com.example.projetoautenticaoandroidjava.services.TelaLogin.TelaLoginImplementacao;
import com.example.projetoautenticaoandroidjava.model.Usuario;
import com.example.projetoautenticaoandroidjava.services.exibirMensagem.Exibir;

public class TelaFormCadastroImplementacao extends TelaFormCadastro {
   private Usuario user;

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
            try {
                verificarESalvarDados();
                voltarParaTelaLogin();
            } catch (FormCadastroException e) {
                Exibir.exibirMensagem(this, e.getMessage());
            }
        });
    }

    private void verificarESalvarDados() throws FormCadastroException {
        setUser(new Usuario(
                getPegarNomeCadastro().getText().toString(),
                getPegarEmailCadastro().getText().toString(),
                getPegarSenhaCadastro().getText().toString()));

        FirebaseFirestoreRepository.adicionarUsuario(this,getUser().getEmail(),getUser().getSenha(), getUser().getNome());
    }


    private void voltarParaTelaLogin() {
        DirecionadorDeLayout.irParaLayout(this, TelaLoginImplementacao.class);
        finish();
    }

    private Usuario getUser() {
        return user;
    }

    private void setUser(Usuario user) {
        this.user = user;
    }
}