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
import model.beans.Pessoa;
import model.daos.CurriculoDAO;
import model.daos.DisciplinaDAO;
import model.daos.PessoaDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.Disciplinas;
import view.Erro;
import view.NovaDisciplina;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class DisciplinasControl implements ActionListener {

    private Disciplinas tl;
    private TelaPrincipal telaP;
    //private NovaDisciplina tl;

    private Disciplina disc;
    private PessoaDAO pDAO = new PessoaDAO();
    private CurriculoDAO cDAO = new CurriculoDAO();
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    public DisciplinasControl(Disciplinas disc, TelaPrincipal tlP) {
        this.tl = disc;
        this.telaP = tlP;

        popularTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtVer()) {

            int row = tl.getTabela().getSelectedRow();
            int id = Integer.parseInt(tl.getTabela().getValueAt(row, 0) + "");

            try {
                disc = disciplinaDAO.search(id);
            } catch (DaoException ex) {
                Logger.getLogger(AlunosControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            NovaDisciplina nP = new NovaDisciplina();
            nP.getBtSalvar().removeActionListener(nP.getBtSalvar().getActionListeners()[0]);
            telaP.getInternoFrame().add(nP);
            nP.show();
            nP.getBtSalvar().setText("Alterar");

            nP.getTxtNomedisci().setText(disc.getNome());
            nP.getTxtCh().setText(String.valueOf(disc.getCh()));

            nP.getCbPedagogo().setSelectedItem(disc.getPessoa().getNome());
            nP.getCbTurmas().setSelectedItem(disc.getCurriculo().getNome());
            nP.getBtSalvar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    disc.setNome(nP.getTxtNomedisci().getText());
                    disc.setCh(Integer.parseInt(nP.getTxtCh().getText()));

                    try {
                        for (Pessoa pe : pDAO.searchAll()) {
                            if (pe.getNome().equals(nP.getCbPedagogo().getSelectedItem().toString())) {
                                //pe.getId();
                                disc.setPessoa(pe);
                            }

                        }
                    } catch (DaoException ex) {
                        Logger.getLogger(PerfilControler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        for (Curriculo c : cDAO.searchAll()) {
                            if (c.getNome().equals(nP.getCbTurmas().getSelectedItem().toString())) {
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
                        nP.dispose();
                    } catch (DaoException ex) {
                        telaErro();
                        Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
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
        model.addColumn("CH");
        model.addColumn("Pedagogo");
        model.addColumn("Curriculo");

        try {
            for (Disciplina d : disciplinaDAO.searchAll()) {
                model.addRow(new Object[]{
                    d.getId(),
                    d.getNome(),
                    d.getCh(),
                    d.getPessoa().getNome(),
                    d.getCurriculo().getNome()

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
        model.addColumn("CH");
        model.addColumn("Pedagogo");
        model.addColumn("Curriculo");

        try {
            for (Disciplina d : disciplinaDAO.buscaPorNome(nome)) {
                model.addRow(new Object[]{
                    d.getId(),
                    d.getNome(),
                    d.getCh(),
                    d.getPessoa().getNome(),
                    d.getCurriculo().getNome()

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
