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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.table.DefaultTableModel;
import model.beans.Aluno;
import model.beans.Pessoa;
import model.daos.AlunoDAO;
import model.daos.PessoaDAO;
import util.DaoException;
import view.Alunos;
import view.CadastroSucesso;
import view.Erro;
import view.NovaMatricula;
import view.NovaPessoa;
import view.NovoAcPedagogico;
import view.Pessoas;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class AlunosControl implements ActionListener {

    private Alunos tl;
    private AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno al;

    private PessoaDAO pDAO = new PessoaDAO();
    private Pessoa p;

    private TelaPrincipal telaP;
    SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");

    public AlunosControl(Alunos tlAlunos, TelaPrincipal tlP) {
        this.tl = tlAlunos;
        this.telaP = tlP;

        popularTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtVer()) {

            int row = tl.getTabela().getSelectedRow();
            int id = Integer.parseInt(tl.getTabela().getValueAt(row, 0) + "");

            try {
                al = alunoDAO.search(id);
            } catch (DaoException ex) {
                Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            NovaPessoa nP = new NovaPessoa();
            nP.getBtCadastrar().removeActionListener(nP.getBtCadastrar().getActionListeners()[0]);
            telaP.getInternoFrame().add(nP);
            nP.show();
            nP.getBtCadastrar().setText("Alterar");
            nP.getCbTipo().setEnabled(false);

            nP.getCbTipo().setSelectedItem(al.getTipo());
            nP.getTxtAluno().setText(al.getNome());
            nP.getTxtNatu().setText(al.getNaturalidade());
            nP.getTxtDtaNasc().setText(format.format(p.getDtaNascimento()));

            nP.getTxtMae().setText(al.getMae());
            nP.getTxtPai().setText(al.getPai());
            nP.getCbRespFinanc().setSelectedItem(al.getCpfRespFinan());
            nP.getTxtCpf().setText(al.getCpfRespFinan());

            nP.getTxtEnd().setText(al.getEnd().getRua());
            nP.getTxtNum().setText(al.getEnd().getNumero());
            nP.getTxtBairro().setText(al.getEnd().getBairro());
            nP.getTxtCep().setText(al.getEnd().getCep());
            nP.getTxtCidade().setText(al.getEnd().getCidade());
            nP.getCbUf().setSelectedItem(al.getEnd().getEstado());
            nP.getTxtLogin().setText(al.getLogin());
            nP.getTxtSenha().setText(al.getSenha());
            nP.getBtCadastrar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        al.setNome(nP.getTxtAluno().getText());
                        al.setNaturalidade(nP.getTxtNatu().getText());

                        try {

                            p.setDtaNascimento(format.parse(nP.getTxtDtaNasc().getText()));
                        } catch (ParseException ex) {
                            Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        al.setMae(nP.getTxtMae().getText());
                        al.setPai(nP.getTxtPai().getText());
                        al.setRespFinan(nP.getCbRespFinanc().getSelectedItem().toString());
                        al.setCpfRespFinan(nP.getTxtCpf().getText());

                        al.getEnd().setRua(nP.getTxtEnd().getText());
                        al.getEnd().setNumero(nP.getTxtNum().getText());
                        al.getEnd().setBairro(nP.getTxtBairro().getText());
                        al.getEnd().setCep(nP.getTxtCep().getText());
                        al.getEnd().setCidade(nP.getTxtCidade().getText());
                        al.getEnd().setEstado(nP.getCbUf().getSelectedItem().toString());

                        al.setLogin(nP.getTxtLogin().getText());
                        al.setSenha(nP.getTxtSenha().getText());

                        alunoDAO.update(al);
                        telaSucesso();
                        nP.dispose();
                    } catch (DaoException ex) {
                        telaErro();
                        Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        }
        if (e.getSource() == tl.getBtBusca()) {

            popularTabelaBusca(tl.getTxtBusca().getText());
        }
        if (e.getSource() == tl.getBtAcPedg()) {
            NovoAcPedagogico nvAc;
            //nvAc.show();

            int row = tl.getTabela().getSelectedRow();
            int id = Integer.parseInt(tl.getTabela().getValueAt(row, 0) + "");

            try {
                al = alunoDAO.search(id);
                nvAc = new NovoAcPedagogico(al, telaP);
                telaP.getInternoFrame().add(nvAc);

                nvAc.show();
                //nvAc.getTxtAluno().setText(al.getNome());
            } catch (DaoException ex) {
                Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            //nvAc.getTxtPedag().setText(telaP.getNomeUser().toString());
        }
        if (e.getSource() == tl.getBtMatricula()) {
            NovaMatricula nvM;
            int row = tl.getTabela().getSelectedRow();
            int id = Integer.parseInt(tl.getTabela().getValueAt(row, 0) + "");

            try {
                al = alunoDAO.search(id);
                nvM = new NovaMatricula(al);
                telaP.getInternoFrame().add(nvM);
                nvM.show();
            } catch (DaoException ex) {
                Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            //nvM.getTxtAluno().setText(al.getNome());
        }

        if (e.getSource() == tl.getBtVoltar()) {
            tl.dispose();
        }

    }

    public void popularTabela() {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabela().setModel(model);

        model.addColumn("ID");
        model.addColumn("Nome");

        try {
            for (Pessoa pe : alunoDAO.searchAll()) {
                model.addRow(new Object[]{
                    pe.getId(),
                    pe.getNome()

                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PerfilControler.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void popularTabelaBusca(String nome) {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabela().setModel(model);

        model.addColumn("ID");
        model.addColumn("Nome");

        try {
            for (Pessoa pe : alunoDAO.buscaPorNome(nome)) {
                model.addRow(new Object[]{
                    pe.getId(),
                    pe.getNome()

                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PerfilControler.class
                    .getName()).log(Level.SEVERE, null, ex);
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
