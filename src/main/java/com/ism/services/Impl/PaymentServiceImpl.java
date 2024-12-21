package com.ism.services.Impl;

import com.ism.data.entites.Payment;
import com.ism.data.repository.PaymentRepository;
import com.ism.services.PaymentService;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void ajouterPaiement(Payment payment) {
        paymentRepository.insert(payment);
    }

    @Override
    public void mettreAJourPaiement(Payment payment) {
        paymentRepository.update(payment);
    }

    @Override
    public void supprimerPaiement(int id) {
        paymentRepository.delete(id);
    }

    @Override
    public Payment obtenirPaiementParId(int id) {
        return paymentRepository.selectById(id);
    }

    @Override
    public List<Payment> obtenirTousLesPaiements() {
        return paymentRepository.selectAll();
    }
}
