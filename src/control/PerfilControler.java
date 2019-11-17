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
import model.daos.CurriculoDAO;
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

    public PerfilControler(NovoPerfilCurricular pftela) {
        this.tlPerf = pftela;

        cvDAO = new CurriculoDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tlPerf.getBtSalvar()) {

            System.out.println(tlPerf.getTxtNome().getText().toString());
            //telaSucesso();
            cv = new Curriculo();
            cv.setNome(tlPerf.getTxtNome().getText());

            try {
                telaSucesso();
                cvDAO.create(cv);

            } catch (DaoException ex) {
                telaErro();
                Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == tlPerf.getBtAtt()) {
            if (tlPerf.getCBef().isSelected()) {
                tlPerf.getTxtNome().setText(tlPerf.getCBef().getText() + " - " + tlPerf.getCbSerieF().getSelectedItem().toString() + " " + tlPerf.getCbTurmaF().getSelectedItem().toString());

            } else {
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
