package com.ism.core.Repository.impl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.ism.core.Repository.Repository;



public class RepositoryJPAImp<T> implements Repository<T> {
    protected EntityManager em;
    private Class<T> type;
    protected String persistenceUnitName;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(getPersistenceUnitName());

    protected RepositoryJPAImp(Class<T> type) {
        getEntityManager();
        this.type = type;
    }

    public void getEntityManager() {
        if (em == null) {
            em = emf.createEntityManager();
        }
    }

    @SuppressWarnings("unchecked")
    public String getPersistenceUnitName() {
        String persistence = "";
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(new FileInputStream("config.yml"));
            Map<String, Object> configMap = (Map<String, Object>) config.get("config");
            persistence = (String) configMap.get("persistence-unit-name");
        } catch (FileNotFoundException e) {
            System.err.println("Erreur, ouverture dossier impossible: " + e);
        }
        return persistence;
    }

    @Override
    public void insert(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<T> selectAll() {
        System.out.println(persistenceUnitName);
        String sql = String.format("SELECT u FROM %s u", type.getName());
        return this.em
                .createQuery(sql, type)
                .getResultList();
    }

}




