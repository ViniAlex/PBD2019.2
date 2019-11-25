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
import model.beans.views.Alunosturma;
import model.daos.AlunoDAO;
import model.daos.CurriculoDAO;
import model.daos.DAO;
import util.DaoException;
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

    public TurmasControl(Turmas tela, TelaPrincipal telaP) {
        this.tl = tela;
        tlP = telaP;

        aDAO = new AlunoDAO();
        cDAO = new CurriculoDAO();

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
