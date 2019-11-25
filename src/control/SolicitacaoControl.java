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
import model.beans.Solicitacao;
import model.daos.SolicitacaoDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.Erro;
import view.TelaSolicitar;

/**
 *
 * @author Vinícius
 */
public class SolicitacaoControl implements ActionListener {

    private Solicitacao soli;
    private SolicitacaoDAO soliDAO;
    private TelaSolicitar tl;

    public SolicitacaoControl(TelaSolicitar tela) {
        this.tl = tela;

        soliDAO = new SolicitacaoDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tl.getBtEnviar()) {

            soli = new Solicitacao();
            
            soli.setNome(tl.getTxtNome().getText()); 
            soli.setStatus("Pendente");
            soli.setTipoSolicitacao(tl.getCbTSoli().getSelectedItem().toString());
            
            try {
                soliDAO.create(soli);
                tl.dispose();
                telaSucesso();
                
            } catch (DaoException ex) {
                Logger.getLogger(SolicitacaoControl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        if (e.getSource() == tl.getBtVoltar()) {
            tl.dispose();
        }
    }
    
    public void telaSucesso() {

        CadastroSucesso sucesso = new CadastroSucesso();
        sucesso.show();
        sucesso.getMsg().setText("Sua solicitação de: "+tl.getCbTSoli().getSelectedItem().toString()+"\n foi enviada com sucesso para o ADM.");
    }

    public void telaErro() {
        Erro tlErro = new Erro();
        tlErro.show();

    } 
}
