/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Vinícius
 */
@Entity
public class Aluno extends Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 100)
    private String pai;
    
     @Column(length = 20)
    private String cttPai;
    
     @Column(length = 100)
    private String Mae;
    
    @Column(length = 20)
    private String cttMae;
    
    @Column(length = 100)
    private String respFinan;
    
    @Column(length = 20)
    private String cpfRespFinan;
    
}
