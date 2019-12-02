/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Vinícius
 */
@Entity
@SequenceGenerator(name = Entidade.sequence, sequenceName = Media.sequence, initialValue = 1, allocationSize = 1)
public class Media extends Entidade {

    public static final String SEQUENCE_NAME = "SEQUENCE_MEDIA";
    private static final long serialVersionUID = 1L;

    @Column(length = 100)
    private String situacao;

    @Column(length = 100)
    private double mediaP;
    
    @Column(length = 100)
    private double rec;

    @Column(length = 100)
    private double mediaF;

    @OneToOne
    @JoinColumn(name = "ALUNO_ID")
    private Aluno aluno;

    @OneToOne
    @JoinColumn(name = "DISCIPLINA_ID")
    private Disciplina disci;

    public double getRec() {
        return rec;
    }

    public void setRec(double rec) {
        this.rec = rec;
    }
    
    

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public double getMediaP() {
        return mediaP;
    }

    public void setMediaP(double mediaP) {
        this.mediaP = mediaP;
    }

    public double getMediaF() {
        return mediaF;
    }

    public void setMediaF(double mediaF) {
        this.mediaF = mediaF;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisci() {
        return disci;
    }

    public void setDisci(Disciplina disci) {
        this.disci = disci;
    }

    @Override
    public String detalhesEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Media{" + "situacao=" + situacao + ", mediaP=" + mediaP + ", mediaF=" + mediaF + ", aluno=" + aluno + ", disci=" + disci + '}';
    }
    
    
}
