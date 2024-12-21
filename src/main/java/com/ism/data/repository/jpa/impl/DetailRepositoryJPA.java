package com.ism.data.repository.jpa.impl;

import com.ism.core.Repository.impl.RepositoryJPAImp;
import com.ism.data.entites.Detail;
import com.ism.data.repository.DetailRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;



public class DetailRepositoryJPA extends RepositoryJPAImp<Detail> implements DetailRepository {
    @PersistenceContext
    private  EntityManager entityManager;

    public DetailRepositoryJPA() {
        super(Detail.class);
    }

    @Override
    public Detail selectById(int id) {
        return entityManager.find(Detail.class, id);
    }

    @Override
    public void delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Detail detail = entityManager.find(Detail.class, id);
            if (detail != null) {
                entityManager.remove(detail);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}

