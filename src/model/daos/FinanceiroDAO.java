/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.beans.Financeiro;
import model.beans.views.MatriculasView;
import static model.daos.DAO.entityManager;
import util.DaoException;

/**
 *
 * @author Vinícius
 */
public class FinanceiroDAO extends DAO<Financeiro> {
    
    public FinanceiroDAO() {
        super(Financeiro.class);
    }
    
    public List<Financeiro> getFinanceiro(int id) throws DaoException {

        try {

            return entityManager.createQuery("SELECT f FROM Financeiro f WHERE f.matricula.id = " + id).getResultList();
        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }
    
     public BigInteger atualizarStatusFinanceiro() throws DaoException {
        try {
            Query query = entityManager.createNativeQuery("select count(*) from atualizar_status_financeiro()");
            BigInteger result = (BigInteger) query.getSingleResult();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro Ao Veirifcar Veículos na Manutenção");
        }

    }
}
