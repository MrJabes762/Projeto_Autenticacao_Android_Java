package com.example.projetoautenticaoandroidjava.Services.TelaFormCadastro;

import android.widget.Toast;

import com.example.projetoautenticaoandroidjava.Controllers.TelaFormCadastro;
import com.example.projetoautenticaoandroidjava.Data.FirebaseFirestoreRepository;
import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.Services.Direcionador.DirecionadorDeLayout;
import com.example.projetoautenticaoandroidjava.Services.TelaLogin.TelaLoginImplementacao;
import com.example.projetoautenticaoandroidjava.model.Usuario;

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
                vetificarESalvarDados();
                voltarParaTelaLogin();
            } catch (FormCadastroException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void vetificarESalvarDados() throws FormCadastroException {
        setUser(new Usuario(
                getPegarNomeCadastro().getText().toString(),
                getPegarEmailCadastro().getText().toString(),
                getPegarSenhaCadastro().getText().toString()));
        if (getUser().getNome().isEmpty() || getUser().getEmail().isEmpty() || getUser().getSenha().isEmpty()) {
            throw new FormCadastroException("Os campos estão vazios. Preencha novamente.");
        }else {
            FirebaseFirestoreRepository.adicionarUsuario(this,getUser().getEmail(),getUser().getSenha());
        }
    }


    private void voltarParaTelaLogin() {
        DirecionadorDeLayout.irParaLayout(this, TelaLoginImplementacao.class);
        finish();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}