package com.ism.services;



import java.util.List;

import com.ism.core.IRepositories;
import com.ism.core.IServices;
import com.ism.entities.Payment;

public class PaymentService implements IServices<Payment> {
    private IRepositories<Payment> paymentRepository;

    public PaymentService() {
    }

    public PaymentService(IRepositories<Payment> paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.insert(payment);
    }

    @Override
    public List<Payment> show() {
        return paymentRepository.selectAll();
    }
}