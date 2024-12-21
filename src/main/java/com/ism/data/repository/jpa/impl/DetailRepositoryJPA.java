package com.ism.data.repository.jpa.impl;

import com.ism.data.entites.Detail;
import com.ism.data.repository.DetailRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DetailRepositoryJPA implements DetailRepository {
    private final EntityManager entityManager;

    public DetailRepositoryJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Detail selectById(int id) {
        return entityManager.find(Detail.class, id);
    }

    @Override
    public List<Detail> selectAll() {
        return entityManager.createQuery("SELECT d FROM Detail d", Detail.class).getResultList();
    }

    @Override
    public void insert(Detail detail) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(detail);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
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

