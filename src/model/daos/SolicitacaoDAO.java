/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import java.util.List;
import javax.persistence.NoResultException;
import model.beans.Aluno;
import model.beans.Solicitacao;
import model.beans.views.Alunosturma;
import model.beans.views.Solicipendencias;
import static model.daos.DAO.entityManager;
import util.DaoException;

/**
 *
 * @author Vinícius
 */
public class SolicitacaoDAO extends DAO <Solicitacao> {
    
    public SolicitacaoDAO() {
        super(Solicitacao.class);
    }
    
    public List<Solicipendencias> getSolicitacoes() throws DaoException {

        try {

            return entityManager.createQuery("SELECT a FROM Solicipendencias a").getResultList();
        } catch (NoResultException n) {
            n.printStackTrace();
            throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
        }

    }
}
