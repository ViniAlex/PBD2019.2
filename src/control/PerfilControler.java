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
import model.beans.Curriculo;
import model.beans.Turma;
import model.daos.CurriculoDAO;
import model.daos.TurmaDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.Erro;
import view.NovoPerfilCurricular;

/**
 *
 * @author Vinícius
 */
public class PerfilControler implements ActionListener {

    private NovoPerfilCurricular tlPerf;
    private CurriculoDAO cvDAO;
    private Curriculo cv;
    private Turma t;
    private TurmaDAO tDAO;

    public PerfilControler(NovoPerfilCurricular pftela) {
        this.tlPerf = pftela;

        cvDAO = new CurriculoDAO();
        tDAO = new TurmaDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tlPerf.getBtSalvar()) {

            cv = new Curriculo();
            cv.setNome(tlPerf.getTxtNome().getText());
            
            t = new Turma();
            t.setNome(tlPerf.getTxtNome().getText());
            t.setCurriculo(cv);

            try {

                
                cvDAO.create(cv);
                tDAO.create(t);
                telaSucesso();

            } catch (DaoException ex) {
                telaErro();
                Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == tlPerf.getBtAtt()) {
            if (tlPerf.getCBefF().isSelected()) {
                tlPerf.getTxtNome().setText(tlPerf.getCBefF().getText() + " - " + tlPerf.getCbSerieFF().getSelectedItem().toString() + " " + tlPerf.getCbTurmaFF().getSelectedItem().toString());
                System.out.println(tlPerf.getCBefF().getText() + " - " + tlPerf.getCbSerieFF().getSelectedItem().toString() + " " + tlPerf.getCbTurmaFF().getSelectedItem().toString());
            }
            if (tlPerf.getCBefI().isSelected()) {
                tlPerf.getTxtNome().setText(tlPerf.getCBefI().getText() + " - " + tlPerf.getCbSerieFI().getSelectedItem().toString() + " " + tlPerf.getCbTurmaFI().getSelectedItem().toString());

            }
            if (tlPerf.getCBem().isSelected()) {
                tlPerf.getTxtNome().setText(tlPerf.getCBem().getText() + " - " + tlPerf.getCbSerieM().getSelectedItem().toString() + " " + tlPerf.getCbTurmaM().getSelectedItem().toString());
                //txtNome.setText(CBem.getText() + " - " + cbSerieM.getSelectedItem().toString() + " " + cbTurmaM.getSelectedItem().toString());
            }
        }
        if (e.getSource() == tlPerf.getBtVoltar()) {
            tlPerf.dispose();
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
