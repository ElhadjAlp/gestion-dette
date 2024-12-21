package com.ism.data.repository.List;


import com.ism.data.entites.Payment;
import com.ism.data.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentRepositoryList implements PaymentRepository {
    private final List<Payment> payments = new ArrayList<>();
    private int currentId = 1; 

    @Override
    public void insert(Payment payment) {
        payment.setId(currentId++);
        payments.add(payment);
    }

    @Override
    public void update(Payment payment) {
        Payment existingPayment = selectById(payment.getId());
        if (existingPayment != null) {
            existingPayment.setMontant(payment.getMontant());
            existingPayment.setDate(payment.getDate());
            existingPayment.setDette(payment.getDette());
        } else {
            System.out.println("Payment not found with ID: " + payment.getId());
        }
    }

    @Override
    public void delete(int id) {
        Payment payment = selectById(id);
        if (payment != null) {
            payments.remove(payment);
        } else {
            System.out.println("Payment not found with ID: " + id);
        }
    }

    @Override
    public Payment selectById(int id) {
        return payments.stream()
                .filter(payment -> payment.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Payment> selectAll() {
        return new ArrayList<>(payments);
    }

    @Override
    public List<Payment> findDetteById(int detteId) {
        List<Payment> paiementsPourDette = payments.stream()
                .filter(payment -> payment.getDette() != null && payment.getDette().getId() == detteId)
                .collect(Collectors.toList());
        
        return paiementsPourDette;
    }

}


