/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.beans.Disciplina;
import model.beans.Media;
import model.beans.views.Alunosturma;
import model.daos.AlunoDAO;
import model.daos.CurriculoDAO;
import model.daos.DAO;
import model.daos.MediaDAO;
import util.DaoException;
import view.Atencao;
import view.CadastrarMedia;
import view.CadastroSucesso;
import view.EmitirBoletim;
import view.Erro;
import view.TelaPrincipal;
import view.Turmas;

/**
 *
 * @author Vinícius
 */
public class TurmasControl implements ActionListener {

    private Turmas tl;
    private Alunosturma alT;
    private AlunoDAO aDAO;
    private TelaPrincipal tlP;
    private CurriculoDAO cDAO;

    private Media m;
    private MediaDAO mDAO;

    public TurmasControl(Turmas tela, TelaPrincipal telaP) {
        this.tl = tela;
        tlP = telaP;

        aDAO = new AlunoDAO();
        cDAO = new CurriculoDAO();
        mDAO = new MediaDAO();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtBuscar()) {

            popularTabelaBusca(tl.getCbTurmas().getSelectedItem().toString());
        }

        if (e.getSource() == tl.getBtBoletim()) {

            int row = tl.getTabelaAlunos().getSelectedRow();
            String nome = (tl.getTabelaAlunos().getValueAt(row, 1) + "");

            EmitirBoletim emB = new EmitirBoletim(tlP, nome);
            tlP.getInternoFrame().add(emB);
            emB.show();

            tl.dispose();

        }

        if (e.getSource() == tl.getCbEnsinos()) {
            if (tl.getCbEnsinos().getSelectedItem().toString().equals("Ensino Fundamental Inicial")) {

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

            }
            if (tl.getCbEnsinos().getSelectedItem().toString().equals("Ensino Fundamental Final")) {

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

            if (tl.getCbEnsinos().getSelectedItem().toString().equals("Ensino Médio")) {

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

        if (e.getSource() == tl.getBtCdNotas()) {

            int row = tl.getTabelaAlunos().getSelectedRow();
            int id = Integer.parseInt(tl.getTabelaAlunos().getValueAt(row, 0) + "");

            CadastrarMedia cdM = new CadastrarMedia(tlP, id);
            //cdM.getBtSalvar().removeActionListener(cdM.getBtSalvar().getActionListeners()[0]);
            tlP.getInternoFrame().add(cdM);
            cdM.show();
            tl.dispose();

        }
        if (e.getSource() == tl.getBtDados()) {

            int row = tl.getTabelaAlunos().getSelectedRow();
            int id = Integer.parseInt(tl.getTabelaAlunos().getValueAt(row, 0) + "");

            CadastrarMedia cdM = new CadastrarMedia(tlP, id);
            cdM.getBtSalvar().removeActionListener(cdM.getBtSalvar().getActionListeners()[0]);
            cdM.getBtAtt().removeActionListener(cdM.getBtAtt().getActionListeners()[0]);
            tlP.getInternoFrame().add(cdM);
            cdM.show();
            //tl.dispose();
            cdM.getBtSalvar().enable(false);

            popularDisciplinas(cdM);

            cdM.getCbDisciplinas().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //popularDisciplinas(cdM);

                    System.out.println("OLHA EU AKIiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                    cdM.getTxtRecu().setEditable(true);
                    cdM.getTxtMediaP().setText(String.valueOf(buscaMedia(cdM).getMediaP()));
                    cdM.getTxtSiatuacao().setText(buscaMedia(cdM).getSituacao());
                    cdM.getTxtMediaF().setText("");
                    cdM.getTxtRecu().setText("");

                    if (buscaMedia(cdM).getSituacao().equals("AM - APROVADO POR MÉDIA") || buscaMedia(cdM).getSituacao().equals("AP - APROVADO")) {
                        cdM.getTxtRecu().setEditable(false);

                        Atencao tlAt = new Atencao();
                        tlAt.show();
                        tlAt.getMsg().setText("ATENÇÃO! - ALUNO APROVADO");
                        cdM.getCbDisciplinas().setSelectedItem(null);

                    }
                }
            });

            cdM.getTxtRecu().setEditable(true);
            cdM.getTxtMediaP().setEditable(false);

            cdM.getBtVoltar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    cdM.dispose();
                }
            });

            cdM.getBtSalvar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    m = buscaMedia(cdM);

                    //m.setMediaF(Double.parseDouble(cdM.getTxtMediaF().getText()));
                    m.setRec(Double.parseDouble(cdM.getTxtRecu().getText()));
                    
                    if(!cdM.getTxtRecu().getText().equals("")){
                         m.setIsRec(true);
                    }
                   
                    
                    //m.setSituacao(cdM.getTxtSiatuacao().getText());

                    try {
                        mDAO.update(m);
                        mDAO.calcularMedia();
                        mDAO.calcularAttSituacao();
                        //popularDisciplinas(cdM);
                        telaSucesso();
                        //cdM.getCbDisciplinas().setSelectedItem(null);
                        cdM.getTxtMediaF().setText("");
                        cdM.getTxtRecu().setText("");
                        cdM.getTxtSiatuacao().setText("");

                    } catch (DaoException ex) {
                        telaErro();
                        Logger.getLogger(TurmasControl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

            cdM.getBtAtt().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    cdM.getBtSalvar().enable(true);
                    cdM.getBtAtt().enable(false);
                    cdM.getTxtRecu().setEditable(false);
                    double mediaP = Double.parseDouble(cdM.getTxtMediaP().getText());

                    double recupe = Double.parseDouble(cdM.getTxtRecu().getText());
                    double mediaF = (recupe + mediaP) / 2;
//                    
                    if (mediaF >= 5) {
                        cdM.getTxtSiatuacao().setText("AP - APROVADO");
                        cdM.getTxtSiatuacao().setEditable(false);

                        cdM.getTxtMediaF().setText(String.valueOf(mediaF));
                        System.out.println("entrei");
                        System.out.println(cdM.getTxtSiatuacao().getText());

                    } else {

                        cdM.getTxtSiatuacao().setText("RP - REPROVADO");
                        cdM.getTxtSiatuacao().setEditable(false);
                        cdM.getTxtMediaF().setText(String.valueOf(mediaF));
                        System.out.println("tbm entrei");
                        System.out.println(cdM.getTxtSiatuacao().getText());

                    }

                }
            });

        }

        if (e.getSource() == tl.getBtVoltar()) {
            tl.dispose();
        }

    }

    public void popularTabelaBusca(String nome) {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabelaAlunos().setModel(model);

        model.addColumn("ID");
        model.addColumn("Nome");

        try {
            for (Alunosturma alT : aDAO.getAlunosturma(nome)) {
                model.addRow(new Object[]{
                    alT.getAluno_id(),
                    alT.getAluno_nome()

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

    public void popularDisciplinas(CadastrarMedia cdM) {

        try {

            List lista = mDAO.buscaMediaSituacao(cdM.getTxtAluno().getText(), "NÃO DEFINIDO");
            cdM.getCbDisciplinas().removeAllItems();

            System.out.println(lista);
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i).toString());
                cdM.getCbDisciplinas().insertItemAt(lista.get(i).toString(), i);

            }
        } catch (DaoException ex) {
            Logger.getLogger(MediaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Media buscaMedia(CadastrarMedia cdM) {

        try {
            for (Media media : mDAO.buscaMedia(cdM.getTxtAluno().getText(), cdM.getCbDisciplinas().getSelectedItem().toString())) {
                //System.out.println(m);
                return media;

                //cdM.getTxtMediaP().setText(String.valueOf(m.getMediaP()));
                //cdM.getTxtSiatuacao().setText(m.getSituacao());
            }
        } catch (DaoException ex) {
            Logger.getLogger(TurmasControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
