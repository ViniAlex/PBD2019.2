/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Vinícius
 */
@Entity
@SequenceGenerator(name = Entidade.sequence, sequenceName = Endereco.sequence, initialValue = 1, allocationSize = 1)
public class Endereco extends Entidade{  

    @Column(length = 80, nullable = false)
    private String cidade;
    @Column(length = 20, nullable = false)
    private String estado;
    @Column(length = 15, nullable = false)
    private String cep;
    @Column(length = 50, nullable = false)
    private String bairro;
    @Column(length = 80, nullable = false)
    private String rua;
    @Column(length = 30, nullable = false)
    private String numero;

    private static final long serialVersionUID = 1L;

    protected static final String sequence = "endereco_sequence";

    public Endereco() {

    }   

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String detalhesEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
