/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

public class DaoException extends Exception {
    
    private String c;
    
    public DaoException(String c){       
        super(c);//a calasse pai que trata o erro        
    }
}
