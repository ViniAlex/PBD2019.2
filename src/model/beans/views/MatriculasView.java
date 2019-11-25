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
@Subselect("SELECT m.id AS matri_id,	\n"
        + "	a.nome AS aluno_nome,\n"
        + "	t.nome AS turma_nome	\n"
        + "FROM matricula m, aluno a, turma t\n"
        + "WHERE a.id = m.aluno_id\n"
        + "AND t.id = m.turma_id")
public class MatriculasView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "matri_id")
    private int matri_id;

    @Column(length = 80)
    private String aluno_nome;

    @Column(length = 100)
    private String turma_nome;

    public int getMatri_id() {
        return matri_id;
    }

    public void setMatri_id(int matri_id) {
        this.matri_id = matri_id;
    }

    public String getAluno_nome() {
        return aluno_nome;
    }

    public void setAluno_nome(String aluno_nome) {
        this.aluno_nome = aluno_nome;
    }

    public String getTurma_nome() {
        return turma_nome;
    }

    public void setTurma_nome(String turma_nome) {
        this.turma_nome = turma_nome;
    }

}
