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
@Subselect("SELECT a.id AS aluno_id,\n"
        + "    a.nome AS aluno_nome,\n"
        + "    t.nome AS turma_nome\n"
        + "   FROM aluno a,\n"
        + "    turma t\n"
        + "  WHERE a.turma_id = t.id")
public class Alunosturma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "aluno_id")
    private int aluno_id;

    @Column(length = 20)
    private String turma_nome;

    @Column(length = 80)
    private String aluno_nome;

    public String getTurma_nome() {
        return turma_nome;
    }

    public void setTurma_nome(String turma_nome) {
        this.turma_nome = turma_nome;
    }

    public String getAluno_nome() {
        return aluno_nome;
    }

    public void setAluno_nome(String aluno_nome) {
        this.aluno_nome = aluno_nome;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

   

}
