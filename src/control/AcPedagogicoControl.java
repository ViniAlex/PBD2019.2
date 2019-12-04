/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import javax.swing.JFileChooser;
import model.beans.Aluno;
import model.beans.Pessoa;
import model.daos.AcPedagogicoDAO;
import model.daos.AlunoDAO;
import model.daos.PessoaDAO;
import util.DaoException;
import model.beans.AcPedagogico;
import view.CadastroSucesso;
import view.Erro;
import view.NovoAcPedagogico;
import view.SalvarForm;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class AcPedagogicoControl implements ActionListener {

    private TelaPrincipal tlP;
    private NovoAcPedagogico tl;
    private AcPedagogicoDAO AcpDAO;
    private PessoaDAO pDAO;
    private AlunoDAO aDAO;

    private AcPedagogico acP;
    private Aluno al;
    private Pessoa p;

    public AcPedagogicoControl(NovoAcPedagogico tela, Aluno aluno, TelaPrincipal telaP) {
        this.tl = tela;
        this.al = aluno;
        this.tlP = telaP;
        AcpDAO = new AcPedagogicoDAO();
        pDAO = new PessoaDAO();
        aDAO = new AlunoDAO();

        tl.getTxtAluno().setText(al.getNome());
        tl.getTxtAluno().setEditable(false);

        tl.getTxtPerfCu().setText(al.getTurma().getNome());
        tl.getTxtPerfCu().setEditable(false);

        tl.getTxtPedag().setText(tlP.getNomeUser().getText());
        tl.getTxtPedag().setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtSalvar()) {

            acP = new AcPedagogico();

            acP.setAluno(al);

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");

                acP.setData(format.parse(tl.getTxtData().getText()));
                //aluno.setDtaNascimento(format.parse(tlPessoa.getTxtDtaNasc().getText()));
            } catch (ParseException ex) {
                Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            acP.setDescricao(tl.getTxtDesc().getText());
            acP.setStatus(tl.getCbStatus().getSelectedItem().toString());

            try {
                for (Pessoa pe : pDAO.searchAll()) {
                    if (pe.getNome().equals(tl.getTxtPedag().getText())) {
                        acP.setPessoa(pe);
                    }
                }
            } catch (DaoException ex) {
                Logger.getLogger(MatriculaControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {

                AcpDAO.create(acP);
                telaSucesso();
                tl.dispose();

            } catch (DaoException ex) {
                telaErro();
                Logger.getLogger(PessoaControl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == tl.getBtRelatorio()) {

            SalvarForm slF = new SalvarForm();
            tlP.getInternoFrame().add(slF);

            int valor = slF.getjFileChooser1().showOpenDialog(tlP);

            if (valor == JFileChooser.APPROVE_OPTION) {

                File diretorio = slF.getjFileChooser1().getSelectedFile();
                System.out.println(diretorio);
                gerarRelatorio(diretorio);
                telaSucessoRelatorio();
                //tl.getTxtDir().setText(diretorio.toString());
            }

        }
        if (e.getSource() == tl.getBtReturn()) {
            tl.dispose();
        }

    }

    public void gerarRelatorio(File diretorio) {

        Document doc = new Document();

        try {
            //PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Vinícius\\Desktop\\2019.2\\PBD 2019.2\\ARGUS\\src\\relatorios\\r" + tl.getTxtAluno().getText() + ".pdf"));
            PdfWriter.getInstance(doc, new FileOutputStream(diretorio + tl.getTxtAluno().getText() + ".pdf"));

            doc.open();

            doc.setPageSize(PageSize.A4);

            doc.addTitle("Relatóio do Acompanhamento Pedagógico");
            doc.add(new Paragraph("Aluno: " + tl.getTxtAluno().getText() + ""));
            doc.add(new Paragraph("Ensino/Turma: " + tl.getTxtPerfCu().getText() + ""));
            doc.add(new Paragraph("Data: " + tl.getTxtData().getText() + ""));
            doc.add(new Paragraph("Descrição: " + tl.getTxtDesc().getText() + ""));
            doc.add(new Paragraph("Pedagogo: " + tl.getTxtPedag().getText() + ""));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AcPedagogicoControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AcPedagogicoControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            doc.close();
        }

        //
    }

    public void telaSucessoRelatorio() {

        CadastroSucesso sucesso = new CadastroSucesso();
        sucesso.show();
        sucesso.getMsg().setText("Relatório realizado com sucesso");

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
