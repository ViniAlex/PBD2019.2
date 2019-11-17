/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.beans.Aluno;
import model.beans.Endereco;
import model.beans.Pessoa;
import model.daos.AlunoDAO;
import model.daos.PessoaDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.Erro;
import view.NovaPessoa;

/**
 *
 * @author Vinícius
 */
public class PessoaControl implements ActionListener {

    private NovaPessoa tlPessoa;
    private Pessoa pessoa;
    private Aluno aluno;
    private AlunoDAO alunoDAO;
    private PessoaDAO pessoaDAO;
    private Endereco end;

    public PessoaControl(NovaPessoa telaPessoa) {
        this.tlPessoa = telaPessoa;
        alunoDAO = new AlunoDAO();
        pessoaDAO = new PessoaDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tlPessoa.getBtCadastrar()) {

            if (tlPessoa.getCbTipo().getSelectedItem().toString().equals("Aluno")) {
                end = new Endereco();
                aluno = new Aluno();

                aluno.setNome(tlPessoa.getTxtAluno().getText());

                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
                    aluno.setDtaNascimento(format.parse(tlPessoa.getTxtDtaNasc().getText()));
                } catch (ParseException ex) {
                    Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
                }

                aluno.setTipo(tlPessoa.getCbTipo().getSelectedItem().toString());
                aluno.setNaturalidade(tlPessoa.getTxtNatu().getText());

                aluno.setMae(tlPessoa.getTxtMae().getText());
                aluno.setPai(tlPessoa.getTxtPai().getText());

                aluno.setRespFinan(tlPessoa.getCbRespFinanc().getSelectedItem().toString());
                aluno.setCpfRespFinan(tlPessoa.getTxtCpf().getText());

                end.setRua(tlPessoa.getTxtEnd().getText());
                end.setNumero(tlPessoa.getTxtNum().getText());
                end.setBairro(tlPessoa.getTxtBairro().getText());
                end.setCep(tlPessoa.getTxtCep().getText());
                end.setCidade(tlPessoa.getTxtCidade().getText());
                end.setEstado(tlPessoa.getCbUf().getSelectedItem().toString());

                aluno.setLogin(tlPessoa.getTxtLogin().getText());
                aluno.setSenha(tlPessoa.getTxtLogin().getText());

                aluno.setEnd(end);

                System.out.println(aluno);

                try {
                    telaSucesso();
                    alunoDAO.create(aluno);
                    limpar();
                } catch (DaoException ex) {
                    telaErro();
                    Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                end = new Endereco();
                pessoa = new Pessoa();

                pessoa.setNome(tlPessoa.getTxtAluno().getText());

                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
                    pessoa.setDtaNascimento(format.parse(tlPessoa.getTxtDtaNasc().getText()));
                } catch (ParseException ex) {
                    Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
                }

                pessoa.setTipo(tlPessoa.getCbTipo().getSelectedItem().toString());
                pessoa.setNaturalidade(tlPessoa.getTxtNatu().getText());

                pessoa.setLogin(tlPessoa.getTxtLogin().getText());
                pessoa.setSenha(tlPessoa.getTxtLogin().getText());

                end.setRua(tlPessoa.getTxtEnd().getText());
                end.setNumero(tlPessoa.getTxtNum().getText());
                end.setBairro(tlPessoa.getTxtBairro().getText());
                end.setCep(tlPessoa.getTxtCep().getText());
                end.setCidade(tlPessoa.getTxtCidade().getText());
                end.setEstado(tlPessoa.getCbUf().getSelectedItem().toString());

                pessoa.setEnd(end);

                try {
                    telaSucesso();
                    pessoaDAO.create(pessoa);
                    limpar();
                } catch (DaoException ex) {
                    telaErro();
                    Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        if (e.getSource() == tlPessoa.getBtVoltar()) {
            tlPessoa.dispose();
        }
    }

    public void limpar() {
        tlPessoa.getTxtAluno().setText("");
        tlPessoa.getTxtDtaNasc().setText("");
        tlPessoa.getTxtNatu().setText("");
        tlPessoa.getTxtPai().setText("");
        tlPessoa.getTxtMae().setText("");
        tlPessoa.getTxtCpf().setText("");
        tlPessoa.getTxtEnd().setText("");
        tlPessoa.getTxtNum().setText("");
        tlPessoa.getTxtBairro().setText("");
        tlPessoa.getTxtCep().setText("");
        tlPessoa.getTxtCidade().setText("");
        tlPessoa.getTxtLogin().setText("");
        tlPessoa.getTxtSenha().setText("");
        tlPessoa.getCbRespFinanc().setSelectedIndex(0);
        tlPessoa.getCbTipo().setSelectedIndex(0);
        tlPessoa.getCbUf().setSelectedIndex(0);
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
