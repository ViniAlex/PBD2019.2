/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import java.util.List;
import javax.persistence.NoResultException;
import model.beans.Disciplina;
import static model.daos.DAO.entityManager;
import util.DaoException;

public class DisciplinaDAO extends DAO<Disciplina> {

    public DisciplinaDAO() {
        super(Disciplina.class);
    }

    public List<Disciplina> buscaDisciplina(String nome) throws DaoException {

        try {
            return entityManager.createQuery("SELECT d.nome FROM " + classe.getName() + " d, Curriculo c where c.id = d.curriculo and c.nome ='" + nome + "'").getResultList();
//        select d.nome, c.nome from disciplina d, curriculo c where d.curriculo_id = c.id and c.nome = 'Ensino Médio - 1° A';
//        return entityManager.createQuery("SELECT d FROM disciplina d where d.nome ='"+nome+"'").getResultList();
        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }

    public List<Disciplina> buscaDisciplina2(String nome, String nome2) throws DaoException {

        try {
            return entityManager.createQuery("SELECT d FROM " + classe.getName() + " d, Curriculo c where c.id = d.curriculo and c.nome ='" + nome + "' and d.nome = '"+nome2+"'").getResultList();
//select d.nome from disciplina d, curriculo c where d.curriculo_id = c.id and d.nome = 'GEOGRAFIA' and c.nome = 'Ensino Médio - 1° A';

        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }

}
