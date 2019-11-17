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
import static java.util.logging.Logger.getLogger;
import javax.swing.table.DefaultTableModel;
import model.beans.Pessoa;
import model.daos.PessoaDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.Erro;
import view.NovaPessoa;
import view.Pessoas;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class PessoasControler implements ActionListener {

    private Pessoas tlPessoas;
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private Pessoa p = new Pessoa();
    private TelaPrincipal telaP;

    public PessoasControler(Pessoas tlPessoas, TelaPrincipal tlP) {
        this.tlPessoas = tlPessoas;
        this.telaP = tlP;

        popularTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tlPessoas.getBtVer()) {

            int row = tlPessoas.getTabela().getSelectedRow();
            System.out.println(tlPessoas.getTabela().getValueAt(row, 0));
            Pessoa p = pessoaDAO.search((tlPessoas.getTabela().getValueAt(row, 0)));
            
            NovaPessoa nP = new NovaPessoa();
            nP.getBtCadastrar().removeActionListener(nP.getBtCadastrar().getActionListeners()[0]);
            telaP.getInternoFrame().add(nP);
            nP.show();
            nP.getBtCadastrar().setText("Alterar");
            nP.getCbTipo().setEnabled(false);
            nP.getCbTipo().setSelectedItem(p.getTipo());
            nP.getTxtAluno().setText(p.getNome());
            nP.getTxtNatu().setText(p.getNaturalidade());
            nP.getTxtDtaNasc().setText(p.getDtaNascimento().toString());
            nP.getTxtEnd().setText(p.getEnd().getRua());
            nP.getTxtNum().setText(p.getEnd().getNumero());
            nP.getTxtBairro().setText(p.getEnd().getBairro());
            nP.getTxtCep().setText(p.getEnd().getCep());
            nP.getTxtCidade().setText(p.getEnd().getCidade());
            nP.getCbUf().setSelectedItem(p.getEnd().getEstado());
            nP.getTxtLogin().setText(p.getLogin());
            nP.getTxtSenha().setText(p.getSenha());
            nP.getBtCadastrar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    try {
                        pessoaDAO.update(p);
                        telaSucesso();
                        nP.dispose();
                    } catch (DaoException ex) {
                        telaErro();
                        Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            //1 fazer um busca por id
            //2 carregar os dado na tela

        }
        if (e.getSource() == tlPessoas.getBtVoltar()) {
            tlPessoas.dispose();
        }

    }

    public void popularTabela() {

        DefaultTableModel model = new DefaultTableModel();

        this.tlPessoas.getTabela().setModel(model);

        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Cargo");

        try {
            for (Pessoa pe : pessoaDAO.searchAll()) {
                model.addRow(new Object[]{
                    pe.getId(),
                    pe.getNome(),
                    pe.getTipo()
                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void telaSucesso() {

        CadastroSucesso sucesso = new CadastroSucesso();
        sucesso.show();
    }

    public void telaErro() {
        Erro tlErro = new Erro();
        tlErro.show();

    }
}
