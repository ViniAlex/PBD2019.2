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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.Finishings;
import javax.swing.JFileChooser;
import model.beans.Financeiro;
import model.beans.Matricula;
import model.daos.FinanceiroDAO;
import model.daos.MatriculaDAO;
import util.DaoException;
import view.CadastroSucesso;
import view.MensalidadeTela;
import view.SalvarForm;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class MensalidadeControl implements ActionListener {

    private MensalidadeTela tl;
    private TelaPrincipal tlP;
    private Financeiro fin;
    private FinanceiroDAO fDAO;
    private Matricula m;
    private MatriculaDAO mDAO;

    public MensalidadeControl(MensalidadeTela tl, TelaPrincipal tlP, int id) {
        this.tl = tl;
        this.tlP = tlP;

        fDAO = new FinanceiroDAO();
        mDAO = new MatriculaDAO();

        //getMatricula(id);
        try {
            for (Financeiro fin : fDAO.getFinanceiro(id)) {
                this.fin = fin;
                tl.getTxtAluno().setText(fin.getMatricula().getAluno().getNome());
                tl.getTxtAluno().setEditable(false);

                tl.getTxtPerfCu().setText(fin.getMatricula().getAluno().getTurma().getNome());
                tl.getTxtPerfCu().setEditable(false);

                tl.getTxtStatus().setText(fin.getStatus());
                tl.getTxtStatus().setEditable(false);

                tl.getTxtValorT().setText(String.valueOf(fin.getValorTotal()));
                tl.getTxtValorT().setEditable(false);

                tl.getTxtP().setText(String.valueOf(fin.getQtdParcela()));
                tl.getTxtP().setEditable(false);

                tl.getTxtMensalidade().setText(String.valueOf(fin.getValorMensalidade()));
                tl.getTxtMensalidade().setEditable(false);

            }
        } catch (DaoException ex) {
            Logger.getLogger(MensalidadeControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtSalvar()) {

            double valorPago = Double.parseDouble(tl.getTxtVaPg().getText());
            double valorR = fin.getValorTotal() - valorPago;

            fin.setValorPago(valorPago);
            fin.setQtdParcela(fin.getQtdParcela() - 1);

            if (valorR == 0) {
                fin.setQtdParcela(0);
            }

            fin.setValorTotal(valorR);

            try {
                fDAO.update(fin);
                telaSucesso();
                tl.dispose();
            } catch (DaoException ex) {
                Logger.getLogger(MensalidadeControl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == tl.getBtRecibo()) {
            SalvarForm slF = new SalvarForm();
            tlP.getInternoFrame().add(slF);

            int valor = slF.getjFileChooser1().showOpenDialog(tlP);

            if (valor == JFileChooser.APPROVE_OPTION) {

                File diretorio = slF.getjFileChooser1().getSelectedFile();
                System.out.println(diretorio);
                emitirRecibo(diretorio);
                telaSucessoRelatorio();
                //tl.getTxtDir().setText(diretorio.toString());
            }

        }
        if (e.getSource() == tl.getBtReturn()) {
            tl.dispose();
        }
    }

    public Matricula getMatricula(int id) {

        try {
            m = mDAO.search(id);
            return m;
        } catch (DaoException ex) {
            Logger.getLogger(MensalidadeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void telaSucesso() {

        CadastroSucesso sucesso = new CadastroSucesso();
        sucesso.show();
        sucesso.getMsg().setText("Pagamento realizado com sucesso");

    }

    public void emitirRecibo(File diretorio) {
        Document doc = new Document();

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(diretorio + tl.getTxtAluno().getText() + ".pdf"));
           
            doc.open();
            doc.setPageSize(PageSize.A4);

            doc.addTitle("Boletim");
            doc.add(new Paragraph("Aluno: " + tl.getTxtAluno().getText()));
            doc.add(new Paragraph("Turma: " + tl.getTxtPerfCu().getText()));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph("Valor da mensalidade: " + tl.getTxtMensalidade().getText()));
            doc.add(new Paragraph("Valor Restante: " + tl.getTxtValorT().getText()));
            doc.add(new Paragraph("Status: " + tl.getTxtStatus().getText()));

            doc.add(new Paragraph(""));
            doc.add(new Paragraph(""));

            doc.add(new Paragraph("Valor recebido: " + tl.getTxtVaPg().getText()));

            
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(AcPedagogicoControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            doc.close();
        }

    }

    public void telaSucessoRelatorio() {

        CadastroSucesso sucesso = new CadastroSucesso();
        sucesso.show();
        sucesso.getMsg().setText("Relatório realizado com sucesso");

    }
}
