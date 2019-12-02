/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import util.Backup;
import view.BackupTela;
import view.CadastroSucesso;
import view.CaminhoArquivo;
import view.Erro;
import view.TelaPrincipal;

/**
 *
 * @author Vinícius
 */
public class BackupControl extends Thread implements ActionListener {
    
    private LocalTime update;//h attt
    private LocalTime atual;//h atual
    private int hora;
    
    private boolean backup = true;
    
    private BackupTela tl;
    private TelaPrincipal tlP;
    
    public BackupControl(BackupTela tl, TelaPrincipal tlP) {
        
        this.tl = tl;
        this.tlP = tlP;
        
       

        //this.update = LocalTime.of(hora, 0);
        this.update = LocalTime.of(12, 0);
        this.atual = LocalTime.now();
        
         tl.getTxtHora().setText(update+"");
        
        start();
    }
    
    public int getHora() {
        return hora;
    }
    
    public void setHora(int hora) {
        this.hora = hora;
    }
    
    @Override
    public void run() {
        super.run();
        
        while (true) {
            
            if (backup && atual.isAfter(update)) {
                try {
                    Backup.backup(new File("..").getCanonicalPath());
                    backup = false;
                    stop();
                } catch (IOException ex) {
                    Logger.getLogger(BackupControl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == tl.getBtBackup()) {
            
            try {
                Backup.backup(tl.getTxtDir().getText());
                telaSucesso();
                
            } catch (IOException ex) {
                telaErro();
                Logger.getLogger(BackupControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if (e.getSource() == tl.getBtBuscar()) {
            CaminhoArquivo tela = new CaminhoArquivo(tlP);
            tlP.getInternoFrame().add(tela);
            tl.getTxtDir().setEditable(false);
            //tela.show();

            //FileChooser tlDir = new FileChooser();
            int valor = tela.getjFileChooser1().showOpenDialog(tlP);
            
            if (valor == JFileChooser.APPROVE_OPTION) {
                
                File diretorio = tela.getjFileChooser1().getSelectedFile();
                tl.getTxtDir().setText(diretorio.toString());
            }
            
        }
        
        if (e.getSource() == tl.getBtVoltar()) {
            tl.dispose();
            
        }
        
    }
    
    public void telaSucesso() {
        
        CadastroSucesso sucesso = new CadastroSucesso();
        sucesso.show();
        sucesso.getMsg().setText("Seu BACKUP foi realizado com sucesso");
    }
    
    public void telaErro() {
        Erro tlErro = new Erro();
        tlErro.show();
        
    }
}
