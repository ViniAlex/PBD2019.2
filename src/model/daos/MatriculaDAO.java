package model.daos;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.beans.Matricula;
import model.beans.views.Alunosturma;
import model.beans.views.MatriculasView;
import static model.daos.DAO.entityManager;
import util.DaoException;

/**
 *
 * @author Vinícius
 */
public class MatriculaDAO extends DAO<Matricula> {

    public MatriculaDAO() {
        super(Matricula.class);
    }

    public List<MatriculasView> getMatriculasNome(String nome) throws DaoException {

        try {

            return entityManager.createQuery("SELECT a FROM MatriculasView a WHERE a.turma_nome like'%" + nome + "%'").getResultList();
        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }

    public List<MatriculasView> getMatriculas() throws DaoException {

        try {

            return entityManager.createQuery("SELECT a FROM MatriculasView a ").getResultList();
        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }
    
  

}
