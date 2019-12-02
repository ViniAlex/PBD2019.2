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
import model.beans.Media;
import model.beans.Pessoa;
import model.daos.MediaDAO;
import util.DaoException;
import view.AlunoNotas;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class AlunoNotasCcontrol implements ActionListener {

    private AlunoNotas tl;
    private TelaPrincipal tlP;
    
    private MediaDAO mDAO;

    public AlunoNotasCcontrol(AlunoNotas tl, TelaPrincipal tlP) {
        this.tl = tl;
        this.tlP = tlP;
        
        mDAO = new MediaDAO();
        
        popularTabelaBusca(tlP.getNomeUser().getText());
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource() == tl.getBtVoltar()){
            tl.dispose();
        }
    }
    
     public void popularTabelaBusca(String nome) {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabelaNotas().setModel(model);

        model.addColumn("DISCIPLINA");
        model.addColumn("MÉDIA PACIAL");
        model.addColumn("RECUPERAÇÃO");
        model.addColumn("MÉDIA FINAL");
        model.addColumn("SITUAÇÃO");
        

        try {
            for (Media m : mDAO.buscaMediaAluno(nome)) {
                model.addRow(new Object[]{
                    m.getDisci().getNome(),
                    m.getMediaP(),
                    m.getRec(),
                    m.getMediaF(),
                    m.getSituacao()

                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PerfilControler.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
