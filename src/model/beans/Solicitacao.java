/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Vinícius
 */
@Entity
@SequenceGenerator(name = Entidade.sequence, sequenceName = Solicitacao.sequence, initialValue = 1, allocationSize = 1)
public class Solicitacao extends Entidade {

    public static final String SEQUENCE_NAME = "SEQUENCE_SOLICITACAO";
    private static final long serialVersionUID = 1L;

    @Column(length = 100)
    private String nome;

    @Column(length = 50)
    private String tipoSolicitacao;

    @Column(length = 50)
    private String status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(String tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String detalhesEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
