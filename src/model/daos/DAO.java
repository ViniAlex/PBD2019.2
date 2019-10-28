package model.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import model.beans.Entidade;
import util.DaoException;

/**
 *
 * @author Vinícius
 */

//classe genérica 
public class DAO<T extends Entidade> {

    protected static EntityManager entityManager = getEntityManager();
    protected Class<T> classe;

    public DAO(Class<T> classe) {
        this.classe = classe;
    }

    //iniciando a conexão 
    protected static EntityManager getEntityManager() {
         
        if (entityManager == null) { 
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ARGUS-UP");
            entityManager = factory.createEntityManager();
            entityManager.clear();
        }

        return entityManager;
    }

    public void create(T entidade) throws DaoException {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entidade);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new DaoException("Erro ao Salvar " + classe.getSimpleName() + " - " + e.getMessage());
        }
    }

    
    public T search(int id) throws DaoException {
        try {
            return entityManager.find(classe, id);
        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro Ao Procurar Por " + classe.getSimpleName());
        }
    }

   
    public void remove(int id) throws DaoException {

        try {
            entityManager.getTransaction().begin();
            T entidade = entityManager.find(classe, id);
            entityManager.remove(entidade);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new DaoException("Erro ao Remover " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }

    
    public void update(T entidade) throws DaoException {

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entidade);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new DaoException("Erro ao Atualizar " + classe.getSimpleName() + " - " + e.getMessage());
        }
    }


    public static void closeConnection() {
        entityManager.flush();
        entityManager.close();
    }
    
    
}