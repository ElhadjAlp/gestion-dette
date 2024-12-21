package com.ism.data.repository.jpa.impl;

import com.ism.data.entites.Dette;
import com.ism.data.repository.DetteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class DetteRepositoryJPA implements DetteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Dette selectById(int id) {
        return entityManager.find(Dette.class, id);
    }

    @Override
    public List<Dette> selectAll() {
        TypedQuery<Dette> query = entityManager.createQuery("SELECT d FROM Dette d", Dette.class);
        return query.getResultList();
    }

    @Override
    public void insert(Dette dette) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(dette);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void updateDette(Dette dette) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(dette); 
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteDette(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Dette dette = selectById(id);
            if (dette != null) {
                entityManager.remove(dette);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
