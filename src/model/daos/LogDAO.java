/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import java.util.List;
import javax.persistence.NoResultException;
import model.beans.Entidade;
import model.beans.Log;
import model.beans.Media;
import static model.daos.DAO.entityManager;
import util.DaoException;

/**
 *
 * @author Vinícius
 */
public class LogDAO extends DAO<Entidade> {

    public LogDAO() {
        super(Entidade.class);
    }

    public List<Log> buscaLog() throws DaoException {

        try {
            return entityManager.createQuery("SELECT m FROM Log m ").getResultList();

        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }
    }

}
