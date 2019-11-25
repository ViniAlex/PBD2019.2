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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import model.beans.Curriculo;
import model.beans.Pessoa;
import model.daos.PessoaDAO;
import util.DaoException;
import view.Alunos;
import view.CadastroSucesso;
import view.Erro;
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
    private Alunos tla;

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
                                telaSucesso();
                                tlP.setAdmON(false);

                                tlP.getItemCdPessoa().setEnabled(false);
                                tlP.getItemListarPessoas().setEnabled(false);
                                tlP.getMenuDisciplina().setEnabled(false);
                                tlP.getMenuMatricula().setEnabled(false);
                                tlP.getMenuTurma().setEnabled(false);

                                tl.dispose();

                                if (pe.getTipo().equals("Aluno")) {

                                    tlP = new TelaPrincipal();
                                    new BackupControl();
                                    tlP.getNomeUser().setText(pe.getNome());
                                    tlP.show();
                                    telaSucesso();
                                    tlP.setAdmON(false);

                                    tlP.getMenuDisciplina().setEnabled(false);
                                    tlP.getMenuMatricula().setEnabled(false);
                                    tlP.getMenuTurma().setEnabled(false);
                                    tlP.getMenuPessoa().setEnabled(false);
                                    tlP.getItemListarAcPedag().setEnabled(false);

                                    tl.dispose();
                                }
                            }
                            if (pe.getTipo().equals("Secretária")) {

                                tlP = new TelaPrincipal();
                                new BackupControl();
                                tlP.getNomeUser().setText(pe.getNome());
                                tlP.show();
                                telaSucesso();
                                telaSucesso();
                                tlP.setAdmON(false);

                                tlP.getItemCdPessoa().setEnabled(false);
                                tlP.getItemListarPessoas().setEnabled(false);

                                /*
                                if (tlP.getInternoFrame().getAllFrames().equals(tla)) {
                                    tla.getBtMatricula().setEnabled(false);
                                    tla.getBtAcPedg().setEnabled(true);
                                }
                                 */
                                tlP.getItemListarAcPedag().setEnabled(false);
                                tl.dispose();
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
                                telaSucesso();
                                tlP.isAdmON();
                                tlP.setAdmON(true);

                                tl.dispose();

                            }

                        }

                    }

                }
            } catch (DaoException ex) {
                telaErro();
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
            System.exit(0);
            
        }
    }

    public void telaSucesso() {

        CadastroSucesso sucesso = new CadastroSucesso();
        sucesso.show();
        sucesso.getMsg().setText("Seja bem vindo ao ARGUS");

    }

    public void telaSucessoMSG() {

        CadastroSucesso sucesso = new CadastroSucesso();
        sucesso.show();
        sucesso.getMsg().setText("Sua solicitação foi enviado ao ADM");
    }

    public void telaErro() {
        Erro tlErro = new Erro();
        tlErro.show();
        tlErro.getMsg().setText("ERRO! - Login ou Senha errados");

    }

}
