package com.ism.services;

import com.ism.data.entites.Article;
import com.ism.data.entites.Client;
import com.ism.data.entites.Dette;
import com.ism.data.entites.Payment;

import java.util.Date;
import java.util.List;

public interface DetteService {
    void insert(Dette dette); 
    List<Dette> findAll(); 
    Dette findById(int id); 
    void updateDette(Dette dette); 
    void deleteDette(int id); 
    List<Dette> findByClientId(int clientId);
    void effectuerPaiement(int detteId, double montant);
    List<Dette> listerDettesNonSolde(Client client);
    List<Dette> listerDemandesDette(String etat);
    List<Payment> verifierPaiementsParDette(int detteId);
    boolean estDetteSoldee(int detteId);
    void createDetteForClientWithArticlesAndPayment(Client client, List<Article> articles, Double montant, Double montantVerser, Date date, Double montantPaiement, Double prixVente, Integer qteVendu );
}

