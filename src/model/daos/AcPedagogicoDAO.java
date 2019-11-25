/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import java.util.List;
import javax.persistence.NoResultException;
import model.beans.AcPedagogico;
import model.beans.views.AcpedagogicoView;
import static model.daos.DAO.entityManager;
import util.DaoException;

/**
 *
 * @author Vinícius
 */
public class AcPedagogicoDAO extends DAO<AcPedagogico> {

    public AcPedagogicoDAO() {
        super(AcPedagogico.class);
    }

    public List<AcpedagogicoView> buscaPorNomeAluno(String nome) throws DaoException {

        try {
            return entityManager.createQuery("SELECT a FROM AcpedagogicoView a where a.aluno_nome like'%" + nome + "%'").getResultList();
            //SELECT ac.id, ac.data, ac.descricao, ac.status, a.nome FROM acpedagogico ac, aluno a where a.id = aluno_id and a.nome like '%ANA%';
        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }
    }

}
