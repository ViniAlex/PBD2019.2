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
import model.beans.Curriculo;
import model.beans.Disciplina;
import model.daos.CurriculoDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.Erro;
import view.NovoPerfilCurricular;
import view.PerfisCurriculares;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class PerfisControl implements ActionListener {

    private TelaPrincipal telaP;
    private PerfisCurriculares tl;

    private CurriculoDAO cDAO = new CurriculoDAO();
    private Curriculo c;

    public PerfisControl(PerfisCurriculares perfTl, TelaPrincipal tlP) {
        this.tl = perfTl;
        this.telaP = tlP;

        popularTabela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtVer()) {

            int row = tl.getTabela().getSelectedRow();
            int id = Integer.parseInt(tl.getTabela().getValueAt(row, 0) + "");

            try {
                c = cDAO.search(id);
            } catch (DaoException ex) {
                Logger.getLogger(PerfisControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            NovoPerfilCurricular nP = new NovoPerfilCurricular();
            nP.getBtSalvar().removeActionListener(nP.getBtSalvar().getActionListeners()[0]);
            telaP.getInternoFrame().add(nP);
            nP.show();
            nP.getBtSalvar().setText("Alterar");

            nP.getTxtNome().setEditable(false);
            nP.getTxtNome().setText(c.getNome());

            nP.getBtSalvar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    c.setNome(nP.getTxtNome().getText());

                    try {
                        telaSucesso();
                        cDAO.update(c);
                        nP.dispose();
                       
                        

                    } catch (DaoException ex) {
                        telaErro();
                        Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            

        }
        
        if (e.getSource() == tl.getBtBusca()) {

            popularTabelaBusca(tl.getTxtBusca().getText());
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
            for (Curriculo cv : cDAO.searchAll()) {
                model.addRow(new Object[]{
                    cv.getId(),
                    cv.getNome()

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
        model.addColumn("Nome");

        try {
            for (Curriculo cv : cDAO.buscaPorNome(nome)) {
                model.addRow(new Object[]{
                    cv.getId(),
                    cv.getNome()

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
