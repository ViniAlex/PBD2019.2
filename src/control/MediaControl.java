/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.beans.Aluno;
import model.beans.Disciplina;
import model.beans.Media;
import model.daos.AlunoDAO;
import model.daos.DisciplinaDAO;
import model.daos.MediaDAO;
import util.DaoException;
import view.CadastrarMedia;
import view.CadastroSucesso;
import view.Erro;
import view.TelaPrincipal;
import view.Turmas;

/**
 *
 * @author Vinícius
 */
public class MediaControl implements ActionListener {

    private Media m;
    private MediaDAO mDAO;
    private CadastrarMedia tl;
    private TelaPrincipal tlP;
    private int idAluno;

    private Aluno al;
    private AlunoDAO aDAO;

    private Disciplina disci;
    private DisciplinaDAO dDAO;

    public MediaControl(CadastrarMedia tela, TelaPrincipal telaP, int id) {
        this.tl = tela;
        this.tlP = telaP;
        this.idAluno = id;

        mDAO = new MediaDAO();
        dDAO = new DisciplinaDAO();
        aDAO = new AlunoDAO();

        try {
            al = aDAO.search(idAluno);
        } catch (DaoException ex) {
            Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        tl.getTxtAluno().setText(al.getNome());
        tl.getTxtAluno().setEditable(false);

        tl.getTxtCurriculo().setText(al.getTurma().getNome());
        tl.getTxtCurriculo().setEditable(false);

        if (tl.getTxtSiatuacao().getText() == null) {
            tl.getTxtSiatuacao().setText("NÃO DEFINIDO");
        }
        tl.getTxtSiatuacao().setEditable(false);

        tl.getTxtMediaF().setEditable(false);
        tl.getTxtRecu().setEditable(false);

        tl.getBtSalvar().enable(true);
        m = new Media();

        popularDisciplinas();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtSalvar()) {

            m.setAluno(al);

            try {
                for (Disciplina disc : dDAO.buscaDisciplina2(tl.getTxtCurriculo().getText(), tl.getCbDisciplinas().getSelectedItem().toString())) {

                    m.setDisci(disc);

                }
            } catch (DaoException ex) {
                Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
            }

            m.setMediaP(Double.parseDouble(tl.getTxtMediaP().getText()));

            m.setSituacao(tl.getTxtSiatuacao().getText());

            try {
                mDAO.create(m);
                telaSucesso();
                limpar();

            } catch (DaoException ex) {
                Logger.getLogger(MediaControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == tl.getBtAtt()) {

            tl.getBtSalvar().enable(false);
            double mediaP = Double.parseDouble(tl.getTxtMediaP().getText());

            //double recupe = Double.parseDouble(tl.getTxtRecu().getText());
            //double mediaF = (recupe + mediaP) / 2;
            if (mediaP >= 7) {
                tl.getTxtSiatuacao().setText("AM - APROVADO POR MÉDIA");
                tl.getTxtSiatuacao().setEditable(false);
                m.setSituacao("AM - APROVADO POR MÉDIA");
            } else {
                tl.getTxtSiatuacao().setText("NÃO DEFINIDO");
                tl.getTxtSiatuacao().setEditable(false);
                m.setSituacao("NÃO DEFINIDO");
            }
//            if (mediaF >= 5) {
//                tl.getTxtSiatuacao().setText("AP - APROVADO");
//                tl.getTxtSiatuacao().setEditable(false);
//                m.setSituacao("AP - APROVADO");
//
//            }
//
//            if (mediaF <= 5) {
//
//                tl.getTxtSiatuacao().setText("RP - REPROVADO");
//                tl.getTxtSiatuacao().setEditable(false);
//                m.setSituacao("RP - REPROVADO");
//            }
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

    public void popularDisciplinas() {

        try {

            List lista = dDAO.buscaDisciplina(tl.getTxtCurriculo().getText());

            System.out.println(lista);
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i).toString());
                tl.getCbDisciplinas().insertItemAt(lista.get(i).toString(), i);

            }
        } catch (DaoException ex) {
            Logger.getLogger(MediaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpar() {
        tl.getTxtMediaP().setText("");
        tl.getCbDisciplinas().setSelectedIndex(0);
    }
}
