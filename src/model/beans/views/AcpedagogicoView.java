/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans.views;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author Vinícius
 */
@Entity
@Immutable
@Subselect("SELECT ac.id AS acp_id, \n"
        + "ac.data AS acp_data,\n"
        + "ac.descricao AS acp_desc,\n"
        + "ac.status AS acp_status,\n"
        + "a.nome AS aluno_nome\n"
        + "  FROM acpedagogico ac, aluno a\n"
        + "  where a.id = aluno_id")
public class AcpedagogicoView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "acp_id")
    private int acp_id;

    @Column(length = 100)
    @Temporal(TemporalType.DATE)
    private Date acp_data;

    @Column(length = 1000)
    private String acp_desc;

    @Column(length = 100)
    private String acp_status;

    @Column(length = 80)
    private String aluno_nome;

    public int getAcp_id() {
        return acp_id;
    }

    public void setAcp_id(int acp_id) {
        this.acp_id = acp_id;
    }

    public Date getAcp_data() {
        return acp_data;
    }

    public void setAcp_data(Date acp_data) {
        this.acp_data = acp_data;
    }

    public String getAcp_desc() {
        return acp_desc;
    }

    public void setAcp_desc(String acp_desc) {
        this.acp_desc = acp_desc;
    }

    public String getAcp_status() {
        return acp_status;
    }

    public void setAcp_status(String acp_status) {
        this.acp_status = acp_status;
    }

    public String getAluno_nome() {
        return aluno_nome;
    }

    public void setAluno_nome(String aluno_nome) {
        this.aluno_nome = aluno_nome;
    }

}
