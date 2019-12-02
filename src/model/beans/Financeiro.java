/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Vinícius
 */
@Entity
@SequenceGenerator(name = Entidade.sequence, sequenceName = Financeiro.sequence, initialValue = 1, allocationSize = 1)
public class Financeiro extends Entidade {

    public static final String SEQUENCE_NAME = "SEQUENCE_ALUNO";
    private static final long serialVersionUID = 1L;

    @Column(length = 50)
    private double valorTotal;

    @Column(length = 50)
    private double valorMensalidade;

    @Column(length = 50)
    private double valorPago;

    @Column(length = 50)
    private String status;

    @Column(length = 50)
    private int qtdParcela;

    @Column(length = 20)
    @Temporal(TemporalType.DATE)
    private Date dtaVencimento;

    @OneToOne
    @JoinColumn(name = "MATRICULA_ID")
    private Matricula matricula;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQtdParcela() {
        return qtdParcela;
    }

    public void setQtdParcela(int qtdParcela) {
        this.qtdParcela = qtdParcela;
    }

    public Date getDtaVencimento() {
        return dtaVencimento;
    }

    public void setDtaVencimento(Date dtaVencimento) {
        this.dtaVencimento = dtaVencimento;
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

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    @Override
    public String detalhesEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
