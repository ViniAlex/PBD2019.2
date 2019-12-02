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
import model.beans.Log;
import model.beans.Media;
import model.daos.LogDAO;
import util.DaoException;
import view.TelaLog;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class LogControl implements ActionListener {

    private TelaLog tl;
    private Log log;
    private LogDAO lDAO;
    private TelaPrincipal tlP;

    public LogControl(TelaLog tl, TelaPrincipal tlP) {
        this.tl = tl;
        this.tlP = tlP;
        lDAO = new LogDAO();

        popularTabelaBusca();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtVoltar()) {
            tl.dispose();
        }
    }

    public void popularTabelaBusca() {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabelaLog().setModel(model);

        model.addColumn("ID");
        model.addColumn("AUTOR");
        model.addColumn("DATA");
        model.addColumn("ANTERIOR");
        model.addColumn("ATUAL");
        model.addColumn("ENTIDADE");

        try {
            for (Log l : lDAO.buscaLog()) {
                model.addRow(new Object[]{
                    l.getId(),
                    l.getAutor(),
                    l.getData(),
                    l.getAnterior(),
                    l.getAtual(),
                    l.getTabela()

                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PerfilControler.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
