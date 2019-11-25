/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.beans.Pessoa;
import model.daos.PessoaDAO;
import util.DaoException;
import view.AlterarSenha;
import view.CadastroSucesso;
import view.Erro;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class AlterarSenhaControl implements ActionListener {

    private AlterarSenha tl;
    private TelaPrincipal tlP;
    private Pessoa p;
    private PessoaDAO pDAO;

    public AlterarSenhaControl(AlterarSenha tela, TelaPrincipal telaP) {
        this.tl = tela;
        this.tlP = telaP;

        pDAO = new PessoaDAO();

        this.p = buscaPessoa();
        this.tl.getTxtLogin().setText(p.getLogin());
        this.tl.getTxrSenha().setText(p.getSenha());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtOk()) {

            p.setLogin(tl.getTxtLogin().getText());
            p.setSenha(tl.getTxrSenha().getText());

            try {
                pDAO.update(p);
                telaSucesso();
                tl.dispose();
            } catch (DaoException ex) {
                Logger.getLogger(AlterarSenhaControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == tl.getBtVoltar()) {
            tl.dispose();
        }
    }

    public void telaSucesso() {

        CadastroSucesso sucesso = new CadastroSucesso();
        sucesso.show();
        sucesso.getMsg().setText("Sua senha foi alterada com sucesso");
    }

    public void telaErro() {
        Erro tlErro = new Erro();
        tlErro.show();

    }

    public Pessoa buscaPessoa() {
        try {
            for (Pessoa pe : pDAO.buscaPorNome(tlP.getNomeUser().getText())) {
                return pe;

            }
        } catch (DaoException ex) {
            Logger.getLogger(AlterarSenhaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
