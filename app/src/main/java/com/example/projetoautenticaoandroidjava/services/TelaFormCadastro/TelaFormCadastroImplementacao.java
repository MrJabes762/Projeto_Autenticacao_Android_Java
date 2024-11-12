
package com.example.projetoautenticaoandroidjava.services.TelaFormCadastro;

import com.example.projetoautenticaoandroidjava.controllers.TelaFormCadastro;
import com.example.projetoautenticaoandroidjava.data.FirebaseFirestoreRepository;
import com.example.projetoautenticaoandroidjava.model.Usuario;
import com.example.projetoautenticaoandroidjava.services.Direcionador.DirecionadorDeLayout;
import com.example.projetoautenticaoandroidjava.services.TelaLogin.TelaLoginImplementacao;
import com.example.projetoautenticaoandroidjava.services.exibirMensagem.Exibir;

public class TelaFormCadastroImplementacao extends TelaFormCadastro {
    private Usuario usuario;

    @Override
    public void observadores() {
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
        usuario = new Usuario(
                getNomeCadastroEditText().getText().toString(),
                getEmailCadastroEditText().getText().toString(),
                getSenhaCadastroEditText().getText().toString());

        FirebaseFirestoreRepository.adicionarUsuario(this, usuario.getEmail(), usuario.getSenha(), usuario.getNome());
    }

    private void voltarParaTelaLogin() {
        DirecionadorDeLayout.irParaLayout(this, TelaLoginImplementacao.class);
        finish();
    }
}
