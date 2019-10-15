/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Vinícius
 */
@Entity
@SequenceGenerator(name = Pessoa.SEQUENCE_NAME,
        sequenceName = Pessoa.SEQUENCE_NAME,
        initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa implements Serializable {

    public static final String SEQUENCE_NAME = "SEQUENCE_PESSOA";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;
    
     @Column(length = 20)
     private String contato;

    @Column(length = 20)
    private Date dtaNascimento;

    @Column(length = 20)
    private String naturalidade;
    
    @Column(length = 20)
    private String tipo; 
    
    @Column(length = 20)
    private String user;
    
    @Column(length = 20)
    private String senha;
    

    /*
    @Column(nullable = false, unique = true, length = 20)
    private String cpf;
    
    @Column(length = 60)
    private String email;
    
    @Column(length = 20)
    private String telefone1;
    
    @Column(length = 20)
    private String telefone2;
    
    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;     
    
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Endereco.class)
    @JoinTable(name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id"))
    private List<Endereco> enderecos;
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
     */
}
