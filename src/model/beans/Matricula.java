/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Vinícius
 */
@Entity
@SequenceGenerator(name = Entidade.sequence, sequenceName = Matricula.sequence, initialValue = 1, allocationSize = 1)
public class Matricula extends Entidade {

    public static final String SEQUENCE_NAME = "SEQUENCE_MATRICULA";
    private static final long serialVersionUID = 1L;
    
    @Column(length = 50)
    private double valorTotal;

    @Column(length = 50)
    private double valorMensalidade;

//    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @ManyToOne
    @JoinColumn(name = "ALUNO_ID")
    private Aluno aluno;
    
    //@OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @ManyToOne
    @JoinColumn(name = "TURMA_ID")
    private Turma turma;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    
    

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

  
    @Override
    public String detalhesEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
