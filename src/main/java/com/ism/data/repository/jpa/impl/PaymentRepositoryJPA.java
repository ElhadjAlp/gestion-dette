package com.ism.data.repository.jpa.impl;

import com.ism.core.Repository.impl.RepositoryJPAImp;
import com.ism.data.entites.Payment;
import com.ism.data.repository.PaymentRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class PaymentRepositoryJPA extends RepositoryJPAImp<Payment> implements PaymentRepository {
     @PersistenceContext
    private  EntityManager entityManager;

    public PaymentRepositoryJPA() {
        super(Payment.class);
    }

    @Override
    public void update(Payment payment) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(payment); // merge pour les mises à jour
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.err.println("Erreur lors de la mise à jour du paiement : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            entityManager.getTransaction().begin();
            Payment payment = selectById(id);
            if (payment != null) {
                entityManager.remove(payment);
            } else {
                System.out.println("Paiement non trouvé avec l'ID : " + id);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.err.println("Erreur lors de la suppression du paiement : " + e.getMessage());
        }
    }

    @Override
    public Payment selectById(int id) {
        return entityManager.find(Payment.class, id);
    }

    @Override
    public List<Payment> findDetteById(int detteId) {
        TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p WHERE p.dette.id = :detteId", Payment.class);
        query.setParameter("detteId", detteId);
        return query.getResultList();
    }
}

