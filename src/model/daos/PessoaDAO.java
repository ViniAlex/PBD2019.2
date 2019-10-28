/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import model.beans.Pessoa;

/**
 *
 * @author Vinícius
 */
public class PessoaDAO extends DAO <Pessoa> {
    
    public PessoaDAO() {
        super(Pessoa.class);
    }
    
}
