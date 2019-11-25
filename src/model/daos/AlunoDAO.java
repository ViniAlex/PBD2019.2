/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import java.util.List;
import javax.persistence.NoResultException;
import model.beans.Aluno;
import model.beans.views.Alunosturma;
import static model.daos.DAO.entityManager;
import util.DaoException;

//
public class AlunoDAO extends DAO<Aluno> {

    public AlunoDAO() {
        super(Aluno.class);
    }

    public List<Alunosturma> getAlunosturma(String nome) throws DaoException {

        try {

            return entityManager.createQuery("SELECT a FROM Alunosturma a WHERE a.turma_nome like'%" + nome + "%'").getResultList();
        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }

}
