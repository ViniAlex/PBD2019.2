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
import model.beans.Aluno;
import model.beans.Disciplina;
import model.beans.Pessoa;
import model.beans.Solicitacao;
import model.beans.views.Solicipendencias;
import model.daos.AlunoDAO;
import model.daos.PessoaDAO;
import model.daos.SolicitacaoDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.Erro;
import view.Solicitacoes;
import view.TelaPrincipal;
import view.TelaSolicitar;
import view.TelaSolicitarADM;

/**
 *
 * @author Vinícius
 */
public class SolicitacoesControl implements ActionListener {

    private Solicitacao soli;
    private SolicitacaoDAO soliDAO;
    private Solicitacoes tl;
    private TelaPrincipal telaP;
    private PessoaDAO pDAO;
    private AlunoDAO aDAO;
    private Pessoa p;

    public SolicitacoesControl(Solicitacoes tela, TelaPrincipal telaPri) {
        this.tl = tela;
        this.telaP = telaPri;

        soliDAO = new SolicitacaoDAO();
        pDAO = new PessoaDAO();
        aDAO = new AlunoDAO();

        popularTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtVer()) {

            //tl = new Solicitacoes();
            int row = tl.getTabela1().getSelectedRow();
            int id = Integer.parseInt(tl.getTabela1().getValueAt(row, 0) + "");

            try {
                soli = soliDAO.search(id);
            } catch (DaoException ex) {
                Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            TelaSolicitarADM tela = new TelaSolicitarADM(tl, telaP);

            telaP.getInternoFrame().add(tela);
            tela.show();
            tela.getTxtNome().setText(soli.getNome());
            tela.getTxtNome().setEditable(false);

            tela.getCbTSoli().setSelectedItem(soli.getTipoSolicitacao());
            tela.getCbTSoli().setEnabled(true);

            tela.getCbStatus().setSelectedItem(soli.getStatus());

            tela.getBtBuscar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    popularTabelaBusca(tela, tela.getTxtNome().getText());
                }
            });

            tela.getBtVoltar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tela.dispose();
                }
            });

            tela.getBtEnviar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    soli.setStatus(tela.getCbStatus().getSelectedItem().toString());

                    int row = tela.getTabela().getSelectedRow();
                    int id = Integer.parseInt(tela.getTabela().getValueAt(row, 0) + "");
                    String lg = (String.valueOf(tela.getTabela().getValueAt(row, 2)));
                    String senha = (String.valueOf(tela.getTabela().getValueAt(row, 3)));

                    try {
                        p = pDAO.search(id);
                        p.setLogin(lg);
                        p.setSenha(senha);

                    } catch (DaoException ex) {
                        Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        pDAO.update(p);
                        soliDAO.update(soli);
                        telaSucesso();
                        tela.dispose();
                    } catch (DaoException ex) {
                        Logger.getLogger(SolicitacoesControl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

        }
        if (e.getSource() == tl.getBtPendentes()) {
            popularTabelaView();
        }
        if (e.getSource() == tl.getBtRemove()) {

            int row = tl.getTabela1().getSelectedRow();
            int id = Integer.parseInt(tl.getTabela1().getValueAt(row, 0) + "");

            try {
                soli = soliDAO.search(id);
                soliDAO.remove(soli);
            } catch (DaoException ex) {
                Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == tl.getBtAtt()) {
            popularTabela();
        }

    }

    public void popularTabelaBusca(TelaSolicitarADM tela, String nome) {

        DefaultTableModel model = new DefaultTableModel();

        tela.getTabela().setModel(model);

        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Login");
        model.addColumn("Senha");

        try {
            for (Pessoa p : pDAO.buscaPorNome(nome)) {
                System.out.println(p.getLogin());
                model.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getLogin(),
                    p.getSenha()

                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void popularTabela() {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabela1().setModel(model);

        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Status");
        model.addColumn("Tipo de Solicitação");

        try {
            for (Solicitacao s : soliDAO.searchAll()) {
                model.addRow(new Object[]{
                    s.getId(),
                    s.getNome(),
                    s.getStatus(),
                    s.getTipoSolicitacao()

                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void popularTabelaView() {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabela1().setModel(model);

        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Status");
        model.addColumn("Tipo de Solicitação");

        try {
            for (Solicipendencias s : soliDAO.getSolicitacoes()) {
                model.addRow(new Object[]{
                    s.getSolici_id(),
                    s.getNome(),
                    s.getStatus_solicitacao(),
                    s.getTipsolicitacao()

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
