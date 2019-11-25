/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.beans.AcPedagogico;
import model.beans.Aluno;
import model.beans.Pessoa;
import model.beans.views.AcpedagogicoView;
import model.daos.AcPedagogicoDAO;
import model.daos.AlunoDAO;
import model.daos.PessoaDAO;
import util.DaoException;
import view.AcPedagogicoTela;
import view.CadastroSucesso;
import view.Erro;
import view.NovoAcPedagogico;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class AcPedagogicosControl implements ActionListener {

    private TelaPrincipal tlP;
    private AcPedagogicoTela tl;
    private AcPedagogico acP;
    private AcPedagogicoDAO AcpDAO;
    private PessoaDAO pDAO;
    private AlunoDAO aDAO;

    private Aluno al;
    private Pessoa p;
    SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");

    public AcPedagogicosControl(AcPedagogicoTela tela, TelaPrincipal telaP) {
        this.tl = tela;
        this.tlP = telaP;

        AcpDAO = new AcPedagogicoDAO();
        pDAO = new PessoaDAO();
        aDAO = new AlunoDAO();

        popularTabela();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtVer()) {

            int row = tl.getTabela().getSelectedRow();
            int id = Integer.parseInt(tl.getTabela().getValueAt(row, 0) + "");

            try {
                acP = AcpDAO.search(id);
            } catch (DaoException ex) {
                Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            NovoAcPedagogico nACP = new NovoAcPedagogico(acP.getAluno(), tlP);
            nACP.getBtSalvar().removeActionListener(nACP.getBtSalvar().getActionListeners()[0]);
            tlP.getInternoFrame().add(nACP);
            nACP.show();
            nACP.getBtSalvar().setText("Alterar");

            nACP.getTxtAluno().setText(acP.getAluno().getNome());
            nACP.getTxtDesc().setText(acP.getDescricao());
            nACP.getTxtPedag().setText(acP.getPessoa().getNome());

            nACP.getCbStatus().setSelectedItem(acP.getStatus());

            nACP.getTxtData().setText(format.format(acP.getData()));

            nACP.getBtSalvar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        for (Aluno al : aDAO.searchAll()) {
                            if (al.getNome().equals(nACP.getTxtAluno().getText())) {
                                //pe.getId();
                                acP.setAluno(al);

                            }

                        }
                    } catch (DaoException ex) {
                        Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        for (Pessoa pe : pDAO.searchAll()) {
                            if (pe.getNome().equals(nACP.getTxtPedag().getText())) {

                                acP.setPessoa(pe);
                            }

                        }
                    } catch (DaoException ex) {
                        Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        acP.setData(format.parse(nACP.getTxtData().getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(AcPedagogicosControl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    acP.setDescricao(nACP.getTxtDesc().getText());
                    acP.setStatus(nACP.getCbStatus().getSelectedItem().toString());

                    try {
                        AcpDAO.update(acP);
                        telaSucesso();
                        nACP.dispose();
                    } catch (DaoException ex) {
                        Logger.getLogger(AcPedagogicosControl.class.getName()).log(Level.SEVERE, null, ex);
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
        model.addColumn("Aluno");
        model.addColumn("Turma");
        model.addColumn("Data");
        model.addColumn("Status");

        try {
            for (AcPedagogico acPeda : AcpDAO.searchAll()) {
                model.addRow(new Object[]{
                    acPeda.getId(),
                    acPeda.getAluno().getNome(),
                    acPeda.getAluno().getTurma().getNome(),
                    acPeda.getData(),
                    acPeda.getStatus()
                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PessoasControler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void popularTabelaBusca(String nome) {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabela().setModel(model);

        model.addColumn("ID");
        model.addColumn("Aluno");
        model.addColumn("Turma");
        model.addColumn("Data");
        model.addColumn("Status");

        try {
            for (AcpedagogicoView acPeda : AcpDAO.buscaPorNomeAluno(nome)) {
                model.addRow(new Object[]{
                    acPeda.getAcp_id(),
                    acPeda.getAluno_nome(),
                    acPeda.getAcp_status(),
                    acPeda.getAcp_data()
                    
                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PessoasControler.class.getName()).log(Level.SEVERE, null, ex);
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
