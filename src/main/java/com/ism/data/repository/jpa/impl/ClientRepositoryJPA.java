package com.ism.data.repository.jpa.impl;

import com.ism.core.Repository.impl.RepositoryJPAImp;
import com.ism.data.entites.Client;
import com.ism.data.entites.Dette;
import com.ism.data.repository.ClientRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ClientRepositoryJPA extends RepositoryJPAImp<Client> implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ClientRepositoryJPA() {
        super(Client.class);
    }

    @Override
    public Client selectBySurname(String value) {
        return selectAll().stream()
                .filter(cl -> cl.getSurname().equals(value))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Client selectByTelephone(String telephone) {
        TypedQuery<Client> query = entityManager.createQuery(
                "SELECT c FROM Client c WHERE c.telephone = :telephone", Client.class);
        query.setParameter("telephone", telephone);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public void updateDette(Dette dette) {
        try {
            entityManager.getTransaction().begin();
            Dette existingDette = entityManager.find(Dette.class, dette.getId());
            if (existingDette != null) {
                existingDette.setMontant(dette.getMontant());
                existingDette.setMontantVerser(dette.getMontantVerser());
                existingDette.setClient(dette.getClient());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDette(int id) {
        try {
            entityManager.getTransaction().begin();
            Dette dette = entityManager.find(Dette.class, id);
            if (dette != null) {
                entityManager.remove(dette);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
public Client selectById(int clientId) {
    return selectAll().stream()
            .filter(client -> client.getId() == clientId) 
            .findFirst() 
            .orElse(null);
}

}



