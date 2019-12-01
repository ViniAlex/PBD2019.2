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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Vinícius
 */
@Entity
@SequenceGenerator(name = Entidade.sequence, sequenceName = Disciplina.sequence, initialValue = 1, allocationSize = 1)
public class Disciplina extends Entidade {

    public static final String SEQUENCE_NAME = "SEQUENCE_DISCIPLINA";
    private static final long serialVersionUID = 1L;

    @Column(length = 100)
    private String nome;

    @Column(length = 20)
    private int ch;    

    @ManyToOne(cascade = {CascadeType.ALL})
    private Curriculo curriculo;

    @OneToOne
    @JoinColumn(name = "PESSOA_ID")
    private Pessoa pessoa;

    

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCh() {
        return ch;
    }

    public void setCh(int ch) {
        this.ch = ch;
    }

    @Override
    public String detalhesEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Disciplina{" + "nome=" + nome + ", ch=" + ch + ", curriculo=" + curriculo + ", pessoa=" + pessoa + '}';
    }
    
    

}
