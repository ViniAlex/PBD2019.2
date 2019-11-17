/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.daos.AlunoDAO;
import model.daos.PessoaDAO;
import view.Login;

/**
 *
 * @author Vinícius
 */
public class LoginControler implements ActionListener {

    private Login login;
    private AlunoDAO alunoDAO;
    private PessoaDAO pessoaDAO;

    public LoginControler(Login lg) {
        this.login = lg;
        alunoDAO = new AlunoDAO();
        pessoaDAO = new PessoaDAO();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String senha = new String(login.getTxtSenha().getPassword());

        if (e.getSource() == login.getBtEntrar()) {
            /*
            if(login.getTxtUser().getText().equals("") || login.getTxtSenha().getText().equals("")){
                JOptionPane.showMessageDialog(null, "Campos não preenchidos!");
            }
            else{
                try {
                    
                    DAO dao = new DAO();
                    dao.realizarLogin(login.getTxtUser().getText(), senha);
                    
                    if(dao.realizarLogin(login.getTxtUser().getText(), senha) instanceof Funcionario){
                        
                        Funcionario f;
                        f = (Funcionario) dao.realizarLogin(login.getTxtUser().getText(), senha);
                        JOptionPane.showMessageDialog(null, "Funcionario Logado");
                        
                        tlPrincipal = new TelaPrincipal(f);
                        tlPrincipal.setVisible(true);
                        tlPrincipal.getNomeUsuarioLabel().setText("Usuário: "+f.getNome());
                        tlPrincipal.getMenuCaixa().setVisible(false);
                        tlPrincipal.getMenuFuncionario().setVisible(false);
                        
                        if(!f.getCargo().equals("Gerente")){
                            tlPrincipal.getMenuCaixa().setVisible(true);
                            tlPrincipal.getMenuFuncionario().setVisible(false);
                        }
                        
                        if(!f.getCargo().equals("Caixa")){
                            tlPrincipal.getMenuCaixa().setVisible(true);
                        }
                        
                        login.dispose();
                        
                    }
                    
                    else if(dao.realizarLogin(login.getTxtUser().getText(), senha) instanceof Administrador){
                        Administrador adm;
                        adm = (Administrador) dao.realizarLogin(login.getTxtUser().getText(), senha);
                        JOptionPane.showMessageDialog(null, "Administrador Logado");
                        tlPrincipal = new TelaPrincipal(adm);
                        tlPrincipal.setVisible(true);
                        tlPrincipal.getNomeUsuarioLabel().setText("Usuário: Administrador");
                    }

                
             else{
                JOptionPane.showMessageDialog(null, "Usuário ou Senha Incorretos!");
                login.getTxtUser().requestFocus();
            }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERRO ou realizar LOGIN");
                }
            }
             */
        }

        if (e.getSource() == login.getBtSair()) {
            login.dispose();
        }

    }

    public void realizarLogin(String login, String senha) {

        /*
        Query query = em.createNativeQuery("select * from funcionario where login like '"
                + login + "' and senha like '" + senha + "'", Funcionario.class);
         */
    }

}
