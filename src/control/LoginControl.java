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
import model.beans.Pessoa;
import model.daos.PessoaDAO;
import util.DaoException;
import view.Login;
import view.TelaPrincipal;
import view.TelaSolicitar;

/**
 *
 * @author Vinícius
 */
public class LoginControl implements ActionListener {

    private Login tl;
    private PessoaDAO pDAO = new PessoaDAO();
    private Pessoa p;
    private TelaPrincipal tlP;

    public LoginControl(Login tlLogin) {
        this.tl = tlLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtEntrar()) {

            try {
                for (Pessoa pe : pDAO.searchAll()) {
                    if (pe.getLogin().equals(tl.getTxtLogin().getText())) {
                        if (pe.getSenha().equals(tl.getTxtSenha().getText())) {

                            if (pe.getTipo().equals("Pedagogo")) {
                                tlP = new TelaPrincipal();
                                new BackupControl();
                                tlP.getNomeUser().setText(pe.getNome());
                                tlP.show();
                                tlP.setAdmON(false);
                                
                                tlP.getItemCdPessoa().setEnabled(false);
                                tlP.getItemListarPessoas().setEnabled(false);
                                tlP.getMenuDisciplina().setEnabled(false);
                                tlP.getMenuMatricula().setEnabled(false);
                                tlP.getMenuTurma().setEnabled(false);

                                tl.dispose();

                            }
                            if (pe.getTipo().equals("Aluno")) {

                            }
                            if (pe.getTipo().equals("Secretária")) {

                            }
                            if (pe.getTipo().equals("Coordenador")) {

                            }
                            if (pe.getTipo().equals("Direção")) {

                            }
                            if (pe.getTipo().equals("ADM")) {
                                tlP = new TelaPrincipal();
                                new BackupControl();
                                tlP.getNomeUser().setText(pe.getNome());
                                tlP.show();
                                tlP.isAdmON();
                                tlP.setAdmON(true);

                                tl.dispose();

                            }

                        }

                    }

                }
            } catch (DaoException ex) {
                Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
            }

            //System.out.println(tl.getTxtLogin().getText());
            //System.out.println(tl.getTxtSenha().getPassword());
        }
        if (e.getSource() == tl.getBtAlterSenha()) {
            tl.dispose();
            TelaSolicitar telaSoli = new TelaSolicitar();
            telaSoli.show();
            telaSoli.getCbStatus().setVisible(false);
            telaSoli.getLbStatus().setVisible(false);

        }
        if (e.getSource() == tl.getBtSair()) {
            tl.dispose();
        }
    }

}
