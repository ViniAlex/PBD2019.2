/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.beans.Media;
import model.daos.MediaDAO;
import util.DaoException;
import view.EmitirBoletim;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class EmitirBoletimControle implements ActionListener {

    private EmitirBoletim tl;
    private TelaPrincipal tlP;

    private Media m;
    private MediaDAO mDAO;

    private String nomeAluno;
    private int id;

    public EmitirBoletimControle(EmitirBoletim tl, TelaPrincipal tlP, String nome) {
        this.tl = tl;
        this.tlP = tlP;
        this.nomeAluno = nome;

        mDAO = new MediaDAO();
        popularTabelaBusca(this.nomeAluno);
        //buscaMediaNome(nomeAluno);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tl.getBtBoletim()) {

            gerarRelatorio(nomeAluno);
        }
        if (e.getSource() == tl.getBtVoltar()) {
            tl.dispose();

        }

    }

    public Media buscaMediaNome(String nome) {

        try {
            for (Media m : mDAO.buscaMediaAluno(nome)) {
                return m;
            }
        } catch (DaoException ex) {
            Logger.getLogger(EmitirBoletimControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void popularTabelaBusca(String nome) {

        DefaultTableModel model = new DefaultTableModel();

        this.tl.getTabelaNotas().setModel(model);

        model.addColumn("DISCIPLINA");
        model.addColumn("MÉDIA PACIAL");
        model.addColumn("RECUPERAÇÃO");
        model.addColumn("MÉDIA FINAL");
        model.addColumn("SITUAÇÃO");

        try {
            for (Media m : mDAO.buscaMediaAluno(nome)) {
                model.addRow(new Object[]{
                    m.getDisci().getNome(),
                    m.getMediaP(),
                    m.getRec(),
                    m.getMediaF(),
                    m.getSituacao()

                });

            }
        } catch (DaoException ex) {
            Logger.getLogger(PerfilControler.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gerarRelatorio(String nome) {

        Document doc = new Document();

        m = buscaMediaNome(nome);

        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Vinícius\\Desktop\\2019.2\\PBD 2019.2\\ARGUS\\src\\relatorios\\boletim " + nomeAluno + ".pdf"));

            doc.open();
            doc.setPageSize(PageSize.A4);

            doc.addTitle("Boletim");
            //doc.add(new Paragraph("Aluno: " + m.getAluno().getNome()));
            //doc.add(new Paragraph("Turma: " + m.getAluno().getTurma().getNome()));
           
            PdfPTable tabela = new PdfPTable(5);

            PdfPCell cabecalho1 = new PdfPCell(new Paragraph("Aluno: " + m.getAluno().getNome()));
            PdfPCell cabecalho2 = new PdfPCell(new Paragraph("Turma: " + m.getAluno().getTurma().getNome()));
            PdfPCell cabecalho = new PdfPCell(new Paragraph("BOLETIM"));

            cabecalho2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cabecalho1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            cabecalho1.setColspan(5);
            cabecalho2.setColspan(5);
            cabecalho.setColspan(5);
            
            tabela.addCell(cabecalho1);
            tabela.addCell(cabecalho2);
            tabela.addCell(cabecalho);
            tabela.addCell("DISCIPLINA");
            tabela.addCell("MÉDIA PACIAL");
            tabela.addCell("RECUPERAÇÃO");
            tabela.addCell("MÉDIA FINAL");
            tabela.addCell("SITUAÇÃO");

            try {
                for (Media m : mDAO.buscaMediaAluno(nome)) {
                    tabela.addCell(m.getDisci().getNome());
                    tabela.addCell(String.valueOf(m.getMediaP()));
                    tabela.addCell(String.valueOf(m.getRec()));
                    tabela.addCell(String.valueOf(m.getMediaF()));
                    tabela.addCell(m.getSituacao());

                }
            } catch (DaoException ex) {
                Logger.getLogger(EmitirBoletimControle.class.getName()).log(Level.SEVERE, null, ex);
            }

            doc.add(tabela);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AcPedagogicoControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AcPedagogicoControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            doc.close();
        }

        //
    }

}