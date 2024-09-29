package com.ism.core.Repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ism.core.Repository.Repository;




public class RepositoryJpaImpl<T> implements Repository<T> {

    protected EntityManager em;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DETTES");

    public RepositoryJpaImpl() {
        if (em == null) {
            em = emf.createEntityManager();
        }

    }

    @Override
    public void insert(T data) {
        try {
            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
     public List<T> selectAll() {

        return em.createQuery("SELECT t FROM " + getEntityClass().getSimpleName() + " t", getEntityClass())
            .getResultList();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getEntityClass() {
        return (Class<T>) ((java.lang.reflect.ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }


}

