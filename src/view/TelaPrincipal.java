/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.omg.CORBA.BAD_CONTEXT;
import view.AcPedagogicoTela;

/**
 *
 * @author Vinícius
 */
public class TelaPrincipal extends javax.swing.JFrame {

    private boolean admON = true;

    public TelaPrincipal() {
        initComponents();

        setLocationRelativeTo(null);

        if (admON) {
            Solicitacoes soli = new Solicitacoes(this);
            internoFrame.add(soli);
            soli.show();
            soli.setResizable(false);
        }
    }

    public boolean isAdmON() {
        return admON;
    }

    public void setAdmON(boolean admON) {
        this.admON = admON;
    }

    public JMenuItem getItemAlterarSenha() {
        return itemAlterarSenha;
    }

    public void setItemAlterarSenha(JMenuItem itemAlterarSenha) {
        this.itemAlterarSenha = itemAlterarSenha;
    }

    public JMenuItem getItemCdPessoa() {
        return itemCdPessoa;
    }

    public void setItemCdPessoa(JMenuItem itemCdPessoa) {
        this.itemCdPessoa = itemCdPessoa;
    }

    public JMenuItem getItemCdTurma() {
        return itemCdTurma;
    }

    public void setItemCdTurma(JMenuItem itemCdTurma) {
        this.itemCdTurma = itemCdTurma;
    }

    public JMenu getItemListarAcPedag() {
        return menuAcPedagogo;
    }

    public void setItemListarAcPedag(JMenu itemListarAcPedag) {
        this.menuAcPedagogo = itemListarAcPedag;
    }

    public JMenuItem getItemListarAlunos() {
        return itemListarAlunos;
    }

    public void setItemListarAlunos(JMenuItem itemListarAlunos) {
        this.itemListarAlunos = itemListarAlunos;
    }

    public JMenuItem getItemListarPessoas() {
        return itemListarPessoas;
    }

    public void setItemListarPessoas(JMenuItem itemListarPessoas) {
        this.itemListarPessoas = itemListarPessoas;
    }

    public JMenuItem getItemListarTurmas() {
        return itemListarPerfCur;
    }

    public void setItemListarTurmas(JMenuItem itemListarTurmas) {
        this.itemListarPerfCur = itemListarTurmas;
    }

    public JMenu getMenuDisciplina() {
        return menuDisciplina;
    }

    public void setMenuDisciplina(JMenu menuDisciplina) {
        this.menuDisciplina = menuDisciplina;
    }

    public JMenu getMenuMatricula() {
        return menuMatricula;
    }

    public void setMenuMatricula(JMenu menuMatricula) {
        this.menuMatricula = menuMatricula;
    }

    public JMenu getMenuPessoa() {
        return menuPessoa;
    }

    public void setMenuPessoa(JMenu menuPessoa) {
        this.menuPessoa = menuPessoa;
    }

    public JMenu getMenuTurma() {
        return menuTurma;
    }

    public void setMenuTurma(JMenu menuTurma) {
        this.menuTurma = menuTurma;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        internoFrame = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        nomeUsuarioLabel = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        imAlterarSenha = new javax.swing.JMenu();
        itemAlterarSenha = new javax.swing.JMenuItem();
        imSair = new javax.swing.JMenuItem();
        menuPessoa = new javax.swing.JMenu();
        itemCdPessoa = new javax.swing.JMenuItem();
        itemListarPessoas = new javax.swing.JMenuItem();
        itemListarAlunos = new javax.swing.JMenuItem();
        menuAluno = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        menuMatricula = new javax.swing.JMenu();
        imListarMatriculas = new javax.swing.JMenuItem();
        menuDisciplina = new javax.swing.JMenu();
        imCadastrarDisciplina = new javax.swing.JMenuItem();
        imLIstarDisciplinas = new javax.swing.JMenuItem();
        menuTurma = new javax.swing.JMenu();
        itemCdTurma = new javax.swing.JMenuItem();
        itemTurmas = new javax.swing.JMenuItem();
        itemListarPerfCur = new javax.swing.JMenuItem();
        menuAcPedagogo = new javax.swing.JMenu();
        imListarAcPed = new javax.swing.JMenuItem();
        menuReport = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuSetings = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        internoFrame.setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        nomeUsuarioLabel.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        nomeUsuarioLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/man-user.png"))); // NOI18N
        nomeUsuarioLabel.setText("Usuário:");

        lbUsuario.setText("LUIZ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nomeUsuarioLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUsuario)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeUsuarioLabel)
                    .addComponent(lbUsuario))
                .addContainerGap())
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/fundo.png"))); // NOI18N

        internoFrame.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        internoFrame.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout internoFrameLayout = new javax.swing.GroupLayout(internoFrame);
        internoFrame.setLayout(internoFrameLayout);
        internoFrameLayout.setHorizontalGroup(
            internoFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internoFrameLayout.createSequentialGroup()
                .addContainerGap(3249, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(internoFrameLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        internoFrameLayout.setVerticalGroup(
            internoFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internoFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 925, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        imAlterarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/man-user.png"))); // NOI18N
        imAlterarSenha.setText("Perfil");

        itemAlterarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/forgot.png"))); // NOI18N
        itemAlterarSenha.setText("Alterar Senha");
        itemAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAlterarSenhaActionPerformed(evt);
            }
        });
        imAlterarSenha.add(itemAlterarSenha);

        imSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icon.png"))); // NOI18N
        imSair.setText("Sair");
        imSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imSairActionPerformed(evt);
            }
        });
        imAlterarSenha.add(imSair);

        jMenuBar1.add(imAlterarSenha);

        menuPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/multiple-users-silhouette.png"))); // NOI18N
        menuPessoa.setText("Pessoa");

        itemCdPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/add-group.png"))); // NOI18N
        itemCdPessoa.setText("Cadastrar Pessoa");
        itemCdPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCdPessoaActionPerformed(evt);
            }
        });
        menuPessoa.add(itemCdPessoa);

        itemListarPessoas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/list.png"))); // NOI18N
        itemListarPessoas.setText("Listar Pessoas");
        itemListarPessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListarPessoasActionPerformed(evt);
            }
        });
        menuPessoa.add(itemListarPessoas);

        itemListarAlunos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/list.png"))); // NOI18N
        itemListarAlunos.setText("Listar Alunos");
        itemListarAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListarAlunosActionPerformed(evt);
            }
        });
        menuPessoa.add(itemListarAlunos);

        jMenuBar1.add(menuPessoa);

        menuAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/students-cap.png"))); // NOI18N
        menuAluno.setText("Aluno");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/test.png"))); // NOI18N
        jMenuItem5.setText("Notas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuAluno.add(jMenuItem5);

        jMenuBar1.add(menuAluno);

        menuMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/plus-sign-in-a-black-circle.png"))); // NOI18N
        menuMatricula.setText("Matricula");

        imListarMatriculas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/list.png"))); // NOI18N
        imListarMatriculas.setText("Listar Matriculas");
        imListarMatriculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imListarMatriculasActionPerformed(evt);
            }
        });
        menuMatricula.add(imListarMatriculas);

        jMenuBar1.add(menuMatricula);

        menuDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/book-and-bookmark.png"))); // NOI18N
        menuDisciplina.setText("Disciplina");

        imCadastrarDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/plus-sign-in-a-black-circle.png"))); // NOI18N
        imCadastrarDisciplina.setText("Cadastrar Disciplina");
        imCadastrarDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imCadastrarDisciplinaActionPerformed(evt);
            }
        });
        menuDisciplina.add(imCadastrarDisciplina);

        imLIstarDisciplinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/list.png"))); // NOI18N
        imLIstarDisciplinas.setText("Listar Disciplinas");
        imLIstarDisciplinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imLIstarDisciplinasActionPerformed(evt);
            }
        });
        menuDisciplina.add(imLIstarDisciplinas);

        jMenuBar1.add(menuDisciplina);

        menuTurma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/classroomrd.png"))); // NOI18N
        menuTurma.setText("Turma");

        itemCdTurma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/plus-sign-in-a-black-circle.png"))); // NOI18N
        itemCdTurma.setText("Cadastrar Turma");
        itemCdTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCdTurmaActionPerformed(evt);
            }
        });
        menuTurma.add(itemCdTurma);

        itemTurmas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/list.png"))); // NOI18N
        itemTurmas.setText("Listar Turmas");
        itemTurmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTurmasActionPerformed(evt);
            }
        });
        menuTurma.add(itemTurmas);

        itemListarPerfCur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/list.png"))); // NOI18N
        itemListarPerfCur.setText("Listar Perfs. Curriculares");
        itemListarPerfCur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListarPerfCurActionPerformed(evt);
            }
        });
        menuTurma.add(itemListarPerfCur);

        jMenuBar1.add(menuTurma);

        menuAcPedagogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/notebook.png"))); // NOI18N
        menuAcPedagogo.setText("Ac. Pedagogico");

        imListarAcPed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/list.png"))); // NOI18N
        imListarAcPed.setText("Listar Acompanhamentos");
        imListarAcPed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imListarAcPedActionPerformed(evt);
            }
        });
        menuAcPedagogo.add(imListarAcPed);

        jMenuBar1.add(menuAcPedagogo);

        menuReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/report.png"))); // NOI18N
        menuReport.setText("Relatorios");
        menuReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportActionPerformed(evt);
            }
        });

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/jornal.png"))); // NOI18N
        jMenuItem7.setText("Abrir Relatório");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuReport.add(jMenuItem7);

        jMenuBar1.add(menuReport);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/saco-de-dinheiro-com-o-simbolo-do-dolar.png"))); // NOI18N
        jMenu1.setText("Financeiro");
        jMenuBar1.add(jMenu1);

        menuSetings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/settings-gears.png"))); // NOI18N
        menuSetings.setText("Configurações");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/backup.png"))); // NOI18N
        jMenuItem2.setText("Backup");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuSetings.add(jMenuItem2);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/files.png"))); // NOI18N
        jMenuItem4.setText("Log");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuSetings.add(jMenuItem4);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/hand-with-money-gear.png"))); // NOI18N
        jMenuItem3.setText("Valores da Matricula");
        menuSetings.add(jMenuItem3);

        jMenuBar1.add(menuSetings);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(internoFrame)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(internoFrame)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void itemListarAlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListarAlunosActionPerformed
        Alunos tlAlunos = new Alunos(this);
        internoFrame.add(tlAlunos);
        tlAlunos.show();

    }//GEN-LAST:event_itemListarAlunosActionPerformed

    private void imLIstarDisciplinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imLIstarDisciplinasActionPerformed
        Disciplinas tlDisci = new Disciplinas(this);
        internoFrame.add(tlDisci);
        tlDisci.show();


    }//GEN-LAST:event_imLIstarDisciplinasActionPerformed

    private void itemAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAlterarSenhaActionPerformed
        AlterarSenha alterP = new AlterarSenha(this);
        internoFrame.add(alterP);
        alterP.show();

    }//GEN-LAST:event_itemAlterarSenhaActionPerformed

    private void itemCdPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCdPessoaActionPerformed
        NovaPessoa tlCliente = new NovaPessoa();
        internoFrame.add(tlCliente);
        tlCliente.show();
    }//GEN-LAST:event_itemCdPessoaActionPerformed

    private void imListarMatriculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imListarMatriculasActionPerformed
        Matriculas tlMatriculas = new Matriculas(this);
        internoFrame.add(tlMatriculas);
        tlMatriculas.show();

    }//GEN-LAST:event_imListarMatriculasActionPerformed

    private void imCadastrarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imCadastrarDisciplinaActionPerformed

        NovaDisciplina tlDisciplina = new NovaDisciplina();
        internoFrame.add(tlDisciplina);
        tlDisciplina.show();
    }//GEN-LAST:event_imCadastrarDisciplinaActionPerformed

    private void itemCdTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCdTurmaActionPerformed
        NovoPerfilCurricular tlPerf = new NovoPerfilCurricular();
        internoFrame.add(tlPerf);
        tlPerf.show();
    }//GEN-LAST:event_itemCdTurmaActionPerformed

    private void itemListarPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListarPessoasActionPerformed
        Pessoas p = new Pessoas(this);
        internoFrame.add(p);
        p.show();
    }//GEN-LAST:event_itemListarPessoasActionPerformed

    private void itemListarPerfCurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListarPerfCurActionPerformed
        PerfisCurriculares pC = new PerfisCurriculares(this);
        internoFrame.add(pC);
        pC.show();
    }//GEN-LAST:event_itemListarPerfCurActionPerformed

    private void imListarAcPedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imListarAcPedActionPerformed
        AcPedagogicoTela tela = new AcPedagogicoTela(this);
        internoFrame.add(tela);
        tela.show();
    }//GEN-LAST:event_imListarAcPedActionPerformed

    private void imSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imSairActionPerformed
        System.exit(0);      // TODO add your handling code here:
    }//GEN-LAST:event_imSairActionPerformed

    private void itemTurmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTurmasActionPerformed
        Turmas t = new Turmas(this);
        internoFrame.add(t);
        t.show();
    }//GEN-LAST:event_itemTurmasActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed

        AlunoNotas alN = new AlunoNotas(this);
        internoFrame.add(alN);
        alN.show();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        TelaLog log = new TelaLog(this);
        internoFrame.add(log);
        log.show();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        BackupTela bck = new BackupTela(this);
        internoFrame.add(bck);
        bck.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportActionPerformed


    }//GEN-LAST:event_menuReportActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        AbrirReport slF = new AbrirReport(this);
        internoFrame.add(slF);
        //slF.show();

        int valor = slF.getjFileChooser1().showOpenDialog(this);

        if (valor == JFileChooser.APPROVE_OPTION) {
            System.out.println("view.TelaPrincipal.jMenuItem7ActionPerformed()");

            File diretorio = slF.getjFileChooser1().getSelectedFile();
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();           
            
            
            try {
                desktop.open(diretorio);
            } catch (IOException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {

                Process p = Runtime.getRuntime().exec("cmd.exe /C " + diretorio);
                System.out.println("cmd.exe /C " + diretorio);
            } catch (IOException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    public JMenu getMenuAcPedagogo() {
        return menuAcPedagogo;
    }

    public void setMenuAcPedagogo(JMenu menuAcPedagogo) {
        this.menuAcPedagogo = menuAcPedagogo;
    }

    public JMenu getMenuAluno() {
        return menuAluno;
    }

    public void setMenuAluno(JMenu menuAluno) {
        this.menuAluno = menuAluno;
    }

    public JMenu getMenuReport() {
        return menuReport;
    }

    public void setMenuReport(JMenu menuReport) {
        this.menuReport = menuReport;
    }

    public JMenu getMenuSetings() {
        return menuSetings;
    }

    public void setMenuSetings(JMenu menuSetings) {
        this.menuSetings = menuSetings;
    }

    /*
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
     */
    public JDesktopPane getInternoFrame() {
        return internoFrame;
    }

    public JLabel getNomeUser() {
        return lbUsuario;
    }

    public void setNomeUser(JLabel nomeUser) {
        this.lbUsuario = nomeUser;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu imAlterarSenha;
    private javax.swing.JMenuItem imCadastrarDisciplina;
    private javax.swing.JMenuItem imLIstarDisciplinas;
    private javax.swing.JMenuItem imListarAcPed;
    private javax.swing.JMenuItem imListarMatriculas;
    private javax.swing.JMenuItem imSair;
    private javax.swing.JDesktopPane internoFrame;
    private javax.swing.JMenuItem itemAlterarSenha;
    private javax.swing.JMenuItem itemCdPessoa;
    private javax.swing.JMenuItem itemCdTurma;
    private javax.swing.JMenuItem itemListarAlunos;
    private javax.swing.JMenuItem itemListarPerfCur;
    private javax.swing.JMenuItem itemListarPessoas;
    private javax.swing.JMenuItem itemTurmas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JMenu menuAcPedagogo;
    private javax.swing.JMenu menuAluno;
    private javax.swing.JMenu menuDisciplina;
    private javax.swing.JMenu menuMatricula;
    private javax.swing.JMenu menuPessoa;
    private javax.swing.JMenu menuReport;
    private javax.swing.JMenu menuSetings;
    private javax.swing.JMenu menuTurma;
    private javax.swing.JLabel nomeUsuarioLabel;
    // End of variables declaration//GEN-END:variables

}
