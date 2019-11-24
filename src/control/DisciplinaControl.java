/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.beans.Curriculo;
import model.beans.Disciplina;
import model.beans.Pessoa;
import model.daos.CurriculoDAO;
import model.daos.DisciplinaDAO;
import model.daos.PessoaDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.Erro;
import view.NovaDisciplina;

/**
 *
 * @author Vinícius
 */
public class DisciplinaControl implements ActionListener {

    private NovaDisciplina tl;
    private DisciplinaDAO disciplinaDAO;
    private Disciplina disc;
    private PessoaDAO pDAO;
    private CurriculoDAO cDAO;

    public DisciplinaControl(NovaDisciplina telaDisci) {
        this.tl = telaDisci;
        disciplinaDAO = new DisciplinaDAO();
        pDAO = new PessoaDAO();
        cDAO = new CurriculoDAO();

        popularCb();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtSalvar()) {

            disc = new Disciplina();

            disc.setNome(tl.getTxtNomedisci().getText());
            disc.setCh(Integer.parseInt(tl.getTxtCh().getText()));

            try {
                for (Pessoa pe : pDAO.searchAll()) {
                    if (pe.getNome().equals(tl.getCbPedagogo().getSelectedItem().toString())) {
                        //pe.getId();
                        disc.setPessoa(pe);
                    }

                }
            } catch (DaoException ex) {
                Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                for (Curriculo c : cDAO.searchAll()) {
                    if (c.getNome().equals(tl.getCbTurmas().getSelectedItem().toString())) {
                        //pe.getId();
                        disc.setCurriculo(c);
                    }

                }
            } catch (DaoException ex) {
                Logger.getLogger(DisciplinaControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                    
                    disciplinaDAO.create(disc);
                    telaSucesso();
                    limpar();
                } catch (DaoException ex) {
                    telaErro();
                    Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

            if (e.getSource() == tl.getCbEnsino()) {
                if (tl.getCbEnsino().getSelectedItem().toString().equals("Ensino Fundamental Inicial")) {

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

                } if(tl.getCbEnsino().getSelectedItem().toString().equals("Ensino Fundamental Final")){
                    
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
                
                if(tl.getCbEnsino().getSelectedItem().toString().equals("Ensino Médio")) {

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

            if (e.getSource()
                    == tl.getBtVoltar()) {
                tl.dispose();
            }

        }

    

    public void popularCb() {
        try {

            List lista = pDAO.buscaPorCargo();

            for (int i = 0; i < lista.size(); i++) {
                tl.getCbPedagogo().insertItemAt(lista.get(i).toString(), i);
            }

        } catch (DaoException ex) {
            Logger.getLogger(DisciplinaControl.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void limpar(){
        tl.getTxtCh().setText("");
        tl.getTxtNomedisci().setText("");
        tl.getCbEnsino().setSelectedIndex(0);
        tl.getCbPedagogo().setSelectedIndex(0);
        tl.getCbTurmas().setSelectedIndex(0);
    }

}
