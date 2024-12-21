package com.ism.services;

import com.ism.data.entites.Payment;
import java.util.List;

public interface PaymentService {
    
    void ajouterPaiement(Payment payment);

    void mettreAJourPaiement(Payment payment);

    void supprimerPaiement(int id);

    Payment obtenirPaiementParId(int id);

    List<Payment> obtenirTousLesPaiements();
}
