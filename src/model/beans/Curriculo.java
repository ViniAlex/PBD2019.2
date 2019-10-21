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
import javax.persistence.OneToMany;
import org.hibernate.mapping.Collection;

/**
 *
 * @author Vinícius
 */
@Entity
public class Curriculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 20)
    private String nome;
    
    public Curriculo(){
        
    }
    
    /*
    @OneToMany(mappedBy = "curriculo", targetEntity = Turma.class, cascade = CascadeType.ALL)
    private Collection turmas;
    
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
