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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.mapping.Collection;

/**
 *
 * @author Vinícius
 */
@Entity
@SequenceGenerator(name = Entidade.sequence, sequenceName = Aluno.sequence, initialValue = 1, allocationSize = 1)

public class Aluno extends Pessoa {

    public static final String SEQUENCE_NAME = "SEQUENCE_ALUNO";
    private static final long serialVersionUID = 1L;

    @Column(length = 100)
    private String pai;

    @Column(length = 100)
    private String mae;

    @Column(length = 100)
    private String respFinan;

    @Column(length = 20)
    private String cpfRespFinan;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Turma turma;

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }   

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getRespFinan() {
        return respFinan;
    }

    public void setRespFinan(String respFinan) {
        this.respFinan = respFinan;
    }

    public String getCpfRespFinan() {
        return cpfRespFinan;
    }

    public void setCpfRespFinan(String cpfRespFinan) {
        this.cpfRespFinan = cpfRespFinan;
    }

    @Override
    public String toString() {
        return "Aluno{" + "pai=" + pai + ", mae=" + mae + ", respFinan=" + respFinan + ", cpfRespFinan=" + cpfRespFinan + ", turma=" + turma + '}';
    }

}
