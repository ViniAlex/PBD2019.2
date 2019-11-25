/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans.views;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author Vinícius
 */
@Entity
@Immutable
@Subselect("SELECT s.nome,\n"
        + "    s.id AS solici_id,\n"
        + "    s.status AS status_solicitacao,\n"
        + "    s.tiposolicitacao AS tipsolicitacao\n"
        + "   FROM solicitacao s\n"
        + "  WHERE s.status = 'Pendente'")
public class Solicipendencias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column
    private int solici_id;

    @Column(length = 80)
    private String nome;

    @Column(length = 50)
    private String status_solicitacao;

    @Column(length = 50)
    private String tipsolicitacao;

    public int getSolici_id() {
        return solici_id;
    }

    public void setSolici_id(int solici_id) {
        this.solici_id = solici_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus_solicitacao() {
        return status_solicitacao;
    }

    public void setStatus_solicitacao(String status_solicitacao) {
        this.status_solicitacao = status_solicitacao;
    }

    public String getTipsolicitacao() {
        return tipsolicitacao;
    }

    public void setTipsolicitacao(String tipsolicitacao) {
        this.tipsolicitacao = tipsolicitacao;
    }

}
