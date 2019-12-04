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
import javax.swing.table.DefaultTableModel;
import model.beans.Matricula;
import model.beans.Turma;
import model.beans.views.MatriculasView;
import model.beans.views.Solicipendencias;
import model.daos.AlunoDAO;
import model.daos.MatriculaDAO;
import model.daos.TurmaDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.Erro;
import view.Matriculas;
import view.MensalidadeTela;
import view.NovaMatricula;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class MatriculasControl implements ActionListener {

    private TelaPrincipal tlp;
    private Matriculas tl;
    private MatriculaDAO mDAO;
    private Matricula m;
    private TurmaDAO tDAO;
    private AlunoDAO aDAO;

    public MatriculasControl(Matriculas tela, TelaPrincipal telap) {
        this.tl = tela;
        this.tlp = telap;

        mDAO = new MatriculaDAO();
        aDAO = new AlunoDAO();
        tDAO = new TurmaDAO();
        popularTabelaView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getVerDados()) {

            int row = tl.getTabela().getSelectedRow();
            int id = Integer.parseInt(tl.getTabela().getValueAt(row, 0) + "");

            try {
                m = mDAO.search(id);
            } catch (DaoException ex) {
                Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            NovaMatricula nM = new NovaMatricula(m.getAluno());
            nM.getBtSalvar().removeActionListener(nM.getBtSalvar().getActionListeners()[0]);
            tlp.getInternoFrame().add(nM);
            nM.show();
            nM.getBtSalvar().setText("Alterar");

            nM.getTxtAluno().setText(m.getAluno().getNome());
            nM.getTxtValorMensa().setText(String.valueOf(m.getValorMensalidade()));
            nM.getTxtValorT().setText(String.valueOf(m.getValorTotal()));
            nM.getCbTurmas().setSelectedItem(m.getTurma());
            //nM.getCbEnsino()
            nM.getBtSalvar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        for (Turma turma : tDAO.searchAll()) {
                            if (turma.getNome().equals(nM.getCbTurmas().getSelectedItem().toString())) {
                                m.setTurma(turma);
                                m.getAluno().setTurma(turma);

                            }
                        }
                    } catch (DaoException ex) {
                        Logger.getLogger(MatriculaControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    m.setAluno(m.getAluno());
                    m.setValorMensalidade(Double.parseDouble(nM.getTxtValorMensa().getText()));
                    m.setValorTotal(Double.parseDouble(nM.getTxtValorT().getText()));

                    try {
                        mDAO.update(m);
                        aDAO.update(m.getAluno());
                        //m.setAluno();
                        telaSucesso();
                        nM.dispose();
                    } catch (DaoException ex) {

                        telaErro();
                        Logger.getLogger(MatriculasControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        }
        if (e.getSource() == tl.getBtMensalidade()) {

            int row = tl.getTabela().getSelectedRow();
            int id = Integer.parseInt(tl.getTabela().getValueAt(row, 0) + "");

            MensalidadeTela nTl = new MensalidadeTela(tlp, id);
            tlp.getInternoFrame().add(nTl);
            nTl.show();

            tl.dispose();

//            try {
//                m = mDAO.search(id);
//            } catch (DaoException ex) {
//                Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        if (e.getSource() == tl.getBtAtt()) {
            popularTabelaView();
        }
        if (e.getSource() == tl.getBtBusca()) {
            popularTabelaBusca(tl.getTxtBusca().getText());
        }
        if (e.getSource() == tl.getBtVoltar()) {
            tl.dispose();
        }
    }

    public void popularTabelaView() {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabela().setModel(model);

        model.addColumn("ID");
        model.addColumn("Aluno");
        model.addColumn("Turma");
        model.addColumn("Status");

        try {
            for (MatriculasView m : mDAO.getMatriculas()) {
                model.addRow(new Object[]{
                    m.getMatri_id(),
                    m.getAluno_nome(),
                    m.getTurma_nome(),
                    m.getStatus()

                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void popularTabelaBusca(String nome) {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabela().setModel(model);

        model.addColumn("ID");
        model.addColumn("Aluno");
        model.addColumn("Turma");

        try {
            for (MatriculasView m : mDAO.getMatriculasNome(nome)) {
                model.addRow(new Object[]{
                    m.getMatri_id(),
                    m.getAluno_nome(),
                    m.getTurma_nome()

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
