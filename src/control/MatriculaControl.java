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
import model.beans.Aluno;
import model.beans.Matricula;
import model.beans.Turma;
import model.daos.AlunoDAO;
import model.daos.CurriculoDAO;
import model.daos.MatriculaDAO;
import model.daos.TurmaDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.Erro;
import view.NovaMatricula;

/**
 *
 * @author Vinícius
 */
public class MatriculaControl implements ActionListener {

    private NovaMatricula tl;
    private Matricula m;
    private MatriculaDAO mDAO;
    private Aluno al;
    private AlunoDAO alDAO;

    private Turma t;
    private TurmaDAO tDAO;

    public MatriculaControl(NovaMatricula tela, Aluno aluno) {
        this.tl = tela;
        this.al = aluno;

        tDAO = new TurmaDAO();
        mDAO = new MatriculaDAO();
        alDAO = new AlunoDAO();

        tl.getTxtAluno().setEditable(false);
        tl.getTxtAluno().setText(al.getNome());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtSalvar()) {

            m = new Matricula();

            try {
                for (Turma turma : tDAO.searchAll()) {
                    if (turma.getNome().equals(tl.getCbTurmas().getSelectedItem().toString())) {
                        m.setTurma(turma);
                        al.setTurma(turma);
                    }
                }
            } catch (DaoException ex) {
                Logger.getLogger(MatriculaControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
                m.setDtaVencimento((format.parse(tl.getTxtDtaVencimento().getText())));
                //aluno.setDtaNascimento(format.parse(tlPessoa.getTxtDtaNasc().getText()));
            } catch (ParseException ex) {
                Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            m.setAluno(al);

            m.setValorTotal(Double.parseDouble(tl.getTxtValorT().getText()));
            m.setValorMensalidade(Double.parseDouble(tl.getTxtValorMensa().getText()));

            try {
                mDAO.create(m);
                alDAO.update(al);

                telaSucesso();

            } catch (DaoException ex) {
                telaErro();
                Logger.getLogger(MatriculaControl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == tl.getCbEnsino()) {

            if (tl.getCbEnsino().getSelectedItem().toString().equals("Ensino Fundamental Inicial")) {
                tl.getTxtValorMensa().setText("400");
                tl.getTxtValorT().setText(String.valueOf(400 * 12));
                tl.getTxtValorT().setEditable(false);
                tl.getTxtValorMensa().setEditable(false);
                List list2;
                try {
                    tl.getCbTurmas().removeAllItems();
                    list2 = tDAO.buscaPorNome2("Ensino Fundamental Inicial");
                    for (int i = 0; i < list2.size(); i++) {
                        tl.getCbTurmas().insertItemAt(list2.get(i).toString(), i);
                    }

                } catch (DaoException ex) {
                    Logger.getLogger(MatriculaControl.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (tl.getCbEnsino().getSelectedItem().toString().equals("Ensino Fundamental Final")) {

                tl.getTxtValorMensa().setText("600");
                tl.getTxtValorT().setText(String.valueOf(600 * 12));
                tl.getTxtValorT().setEditable(false);
                tl.getTxtValorMensa().setEditable(false);

                List list4;
                try {
                    tl.getCbTurmas().removeAllItems();
                    list4 = tDAO.buscaPorNome2("Ensino Fundamental Final");
                    for (int i = 0; i < list4.size(); i++) {
                        tl.getCbTurmas().insertItemAt(list4.get(i).toString(), i);
                    }

                } catch (DaoException ex) {
                    Logger.getLogger(MatriculaControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (tl.getCbEnsino().getSelectedItem().toString().equals("Ensino Médio")) {

                tl.getTxtValorMensa().setText("800");
                tl.getTxtValorT().setText(String.valueOf(800 * 12));
                tl.getTxtValorT().setEditable(false);
                tl.getTxtValorMensa().setEditable(false);

                //configurar valor depois
                List list3;
                try {
                    tl.getCbTurmas().removeAllItems();
                    list3 = tDAO.buscaPorNome2("Ensino Médio");
                    for (int i = 0; i < list3.size(); i++) {
                        tl.getCbTurmas().insertItemAt(list3.get(i).toString(), i);
                    }

                } catch (DaoException ex) {
                    Logger.getLogger(MatriculaControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (e.getSource() == tl.getBtVoltar()) {
            tl.dispose();
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
