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
import javax.swing.table.DefaultTableModel;
import model.beans.Disciplina;
import model.beans.Media;
import model.beans.views.Alunosturma;
import model.daos.AlunoDAO;
import model.daos.CurriculoDAO;
import model.daos.DAO;
import model.daos.MediaDAO;
import util.DaoException;
import view.CadastrarMedia;
import view.TelaPrincipal;
import view.Turmas;

/**
 *
 * @author Vinícius
 */
public class TurmasControl implements ActionListener {

    private Turmas tl;
    private Alunosturma alT;
    private AlunoDAO aDAO;
    private TelaPrincipal tlP;
    private CurriculoDAO cDAO;

    private Media m;
    private MediaDAO mDAO;

    public TurmasControl(Turmas tela, TelaPrincipal telaP) {
        this.tl = tela;
        tlP = telaP;

        aDAO = new AlunoDAO();
        cDAO = new CurriculoDAO();
        mDAO = new MediaDAO();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtBuscar()) {

            popularTabelaBusca(tl.getCbTurmas().getSelectedItem().toString());
        }

        if (e.getSource() == tl.getCbEnsinos()) {
            if (tl.getCbEnsinos().getSelectedItem().toString().equals("Ensino Fundamental Inicial")) {

                List list2;
                try {
                    tl.getCbTurmas().removeAllItems();
                    list2 = cDAO.buscaPorNome2("Ensino Fundamental Inicial");
                    for (int i = 0; i < list2.size(); i++) {
                        tl.getCbTurmas().insertItemAt(list2.get(i).toString(), i);
                    }

                } catch (DaoException ex) {
                    Logger.getLogger(DisciplinaControl.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (tl.getCbEnsinos().getSelectedItem().toString().equals("Ensino Fundamental Final")) {

                List list4;
                try {
                    tl.getCbTurmas().removeAllItems();
                    list4 = cDAO.buscaPorNome2("Ensino Fundamental Final");
                    for (int i = 0; i < list4.size(); i++) {
                        tl.getCbTurmas().insertItemAt(list4.get(i).toString(), i);
                    }

                } catch (DaoException ex) {
                    Logger.getLogger(DisciplinaControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (tl.getCbEnsinos().getSelectedItem().toString().equals("Ensino Médio")) {

                List list3;
                try {
                    tl.getCbTurmas().removeAllItems();
                    list3 = cDAO.buscaPorNome2("Ensino Médio");
                    for (int i = 0; i < list3.size(); i++) {
                        tl.getCbTurmas().insertItemAt(list3.get(i).toString(), i);
                    }

                } catch (DaoException ex) {
                    Logger.getLogger(DisciplinaControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (e.getSource() == tl.getBtCdNotas()) {

            int row = tl.getTabelaAlunos().getSelectedRow();
            int id = Integer.parseInt(tl.getTabelaAlunos().getValueAt(row, 0) + "");

            CadastrarMedia cdM = new CadastrarMedia(tlP, id);
            //cdM.getBtSalvar().removeActionListener(cdM.getBtSalvar().getActionListeners()[0]);
            tlP.getInternoFrame().add(cdM);
            cdM.show();
            tl.dispose();

        }
        if (e.getSource() == tl.getBtDados()) {

            int row = tl.getTabelaAlunos().getSelectedRow();
            int id = Integer.parseInt(tl.getTabelaAlunos().getValueAt(row, 0) + "");

            CadastrarMedia cdM = new CadastrarMedia(tlP, id);
            cdM.getBtSalvar().removeActionListener(cdM.getBtSalvar().getActionListeners()[0]);
            tlP.getInternoFrame().add(cdM);
            cdM.show();
            tl.dispose();

            cdM.getCbDisciplinas().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        for (Media med : mDAO.buscaMedia(cdM.getTxtAluno().getText(), cdM.getCbDisciplinas().getSelectedItem().toString())) {
                            System.out.println(med);
                            cdM.getTxtMediaP().setText(String.valueOf(med.getMediaP()));
                            cdM.getTxtSiatuacao().setText(med.getSituacao());
                        }
                    } catch (DaoException ex) {
                        Logger.getLogger(TurmasControl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

            cdM.getTxtRecu().setEditable(true);

            cdM.getBtVoltar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    cdM.dispose();
                }
            });

            cdM.getBtSalvar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            cdM.getBtAtt().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    m = new Media();

                    cdM.getBtSalvar().enable(false);
                    double mediaP = Double.parseDouble(cdM.getTxtMediaP().getText());

                    double recupe = Double.parseDouble(cdM.getTxtRecu().getText());
                    double mediaF = (recupe + mediaP) / 2;
                    if (mediaP >= 7) {
                        cdM.getTxtSiatuacao().setText("AM - APROVADO POR MÉDIA");
                        cdM.getTxtSiatuacao().setEditable(false);
                        m.setSituacao("AM - APROVADO POR MÉDIA");
                    } else {
                        cdM.getTxtSiatuacao().setText("NÃO DEFINIDO");
                        cdM.getTxtSiatuacao().setEditable(false);
                        m.setSituacao("NÃO DEFINIDO");
                    }
                    if (mediaF >= 5) {
                        cdM.getTxtSiatuacao().setText("AP - APROVADO");
                        cdM.getTxtSiatuacao().setEditable(false);
                        m.setSituacao("AP - APROVADO");

                    }

                    if (mediaF <= 5) {

                        cdM.getTxtSiatuacao().setText("RP - REPROVADO");
                        cdM.getTxtSiatuacao().setEditable(false);
                        m.setSituacao("RP - REPROVADO");
                    }

                }
            });

        }

        if (e.getSource() == tl.getBtVoltar()) {
            tl.dispose();
        }

    }

    public void popularTabelaBusca(String nome) {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabelaAlunos().setModel(model);

        model.addColumn("ID");
        model.addColumn("Nome");

        try {
            for (Alunosturma alT : aDAO.getAlunosturma(nome)) {
                model.addRow(new Object[]{
                    alT.getAluno_id(),
                    alT.getAluno_nome()

                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
