/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import java.util.List;
import javax.persistence.NoResultException;
import model.beans.Disciplina;
import model.beans.Matricula;
import model.beans.Media;
import static model.daos.DAO.entityManager;
import util.DaoException;

/**
 *
 * @author Vinícius
 */
public class MediaDAO extends DAO<Media> {

    public MediaDAO() {
        super(Media.class);
    }

    public List<Media> buscaMedia(String nome, String nome2) throws DaoException {

        try {
            return entityManager.createQuery("SELECT m FROM " + classe.getName() + " m, Aluno a, Disciplina d where a.id = m.aluno and m.disci = d.id and a.nome ='" + nome + "' and d.nome = '" + nome2 + "'").getResultList();

        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }
    
    public List<String> buscaMediaSituacao(String nome, String nome2) throws DaoException {

        try {
            return entityManager.createQuery("SELECT m.disci.nome FROM " + classe.getName() + " m, Aluno a, Disciplina d where a.id = m.aluno and m.disci = d.id and a.nome ='" + nome + "' and m.situacao = '" + nome2 + "'").getResultList();

        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }
    
    public List<Media> buscaMediaAluno(String nome) throws DaoException {

        try {
            return entityManager.createQuery("SELECT m FROM " + classe.getName() + " m, Aluno a where a.id = m.aluno and a.nome ='" + nome + "'").getResultList();

        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }
    
    public List<Media> buscaMediaPorAluno(String nome) throws DaoException {

        try {
            return entityManager.createQuery("SELECT m FROM " + classe.getName() + " m, Aluno a where a.id = m.aluno and a.nome ='" + nome + "'").getResultList();

        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }

}
