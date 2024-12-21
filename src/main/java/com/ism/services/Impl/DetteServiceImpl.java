package com.ism.services.Impl;

import com.ism.data.entites.Article;
import com.ism.data.entites.Client;
import com.ism.data.entites.Detail;
import com.ism.data.entites.Dette;
import com.ism.data.entites.Payment;
import com.ism.data.enums.EtatDette;
import com.ism.data.repository.DetailRepository;
import com.ism.data.repository.DetteRepository;
import com.ism.data.repository.PaymentRepository;
import com.ism.services.DetteService;

import java.time.LocalDate; // Utilisation de LocalDate
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DetteServiceImpl implements DetteService {

    private final DetteRepository detteRepository;
    private final PaymentRepository paymentRepository;
    private final DetailRepository detailRepository;

    public DetteServiceImpl(DetteRepository detteRepository, PaymentRepository paymentRepository, DetailRepository detailRepository) {
        this.detteRepository = detteRepository;
        this.paymentRepository = paymentRepository;
        this.detailRepository = detailRepository;
    }

    @Override
    public void insert(Dette dette) {
        dette.setMontantRestant(dette.getMontant() - dette.getMontantVerser());
        dette.setEtat(EtatDette.EN_COURS);
        detteRepository.insert(dette);
    }

    @Override
    public List<Dette> findAll() {
        return detteRepository.selectAll();
    }

    @Override
    public Dette findById(int id) {
        return detteRepository.selectById(id);
    }

    @Override
    public void updateDette(Dette dette) {
        if (findById(dette.getId()) != null) {
            dette.setMontantRestant(dette.getMontant() - dette.getMontantVerser());
            detteRepository.updateDette(dette);
        } else {
            System.out.println("Dette not found with ID: " + dette.getId());
        }
    }

    @Override
    public void deleteDette(int id) {
        Dette dette = findById(id);
        if (dette != null) {
            dette.setEtat(EtatDette.ANNULE);
            detteRepository.updateDette(dette);
            detteRepository.deleteDette(id);
        } else {
            System.out.println("Dette not found with ID: " + id);
        }
    }

    @Override
    public List<Dette> findByClientId(int clientId) {
        return detteRepository.selectAll().stream()
                .filter(dette -> dette.getClient() != null && dette.getClient().getId() == clientId)
                .collect(Collectors.toList());
    }

    @Override
    public void effectuerPaiement(int detteId, double montant) {
        Optional<Dette> optionalDette = Optional.ofNullable(findById(detteId));
        if (optionalDette.isPresent()) {
            Dette dette = optionalDette.get();
            double montantRestant = dette.getMontantRestant();

            if (montant > montantRestant) {
                System.out.println("Le montant à payer dépasse le montant restant !");
                return;
            }

            double nouveauMontantRestant = montantRestant - montant;
            dette.setMontantRestant(nouveauMontantRestant);
            dette.setMontantVerser(dette.getMontantVerser() + montant);

            if (nouveauMontantRestant == 0) {
                dette.setEtat(EtatDette.SOLDEE);
            }

            updateDette(dette);
            System.out.println("Paiement effectué avec succès !");
        } else {
            System.out.println("Dette non trouvée !");
        }
    }

    @Override
    public List<Dette> listerDettesNonSolde(Client client) {
        List<Dette> dettes = findByClientId(client.getId());
        return dettes.stream()
                .filter(dette -> dette.getMontantRestant() != 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Dette> listerDemandesDette(String etat) {
        return detteRepository.selectAll().stream()
                .filter(dette -> dette.getEtat().name().equals(etat)) 
                .collect(Collectors.toList());
    }

    public void createDetteForClientWithArticlesAndPayment(Client client, List<Article> articles, Double montant, Double montantVerser, LocalDate date, Double montantPaiement, Double prixVente, Integer qteVendu) {

        Dette dette = new Dette();
        dette.setMontant(montant);
        dette.setMontantVerser(montantVerser);
        dette.setDate(LocalDateTime.now());
        dette.setClient(client);
        dette.setEtat(EtatDette.EN_COURS);

        for (Article article : articles) {
            Detail detail = new Detail();
            detail.setPrixVente(prixVente);
            detail.setQteVendu(qteVendu);
            detail.setDette(dette);
            detail.setArticle(article);
            dette.addDetail(detail);
            detailRepository.insert(detail);
        }

        detteRepository.insert(dette);

        Payment paiement = new Payment();
        paiement.setMontant(montantPaiement);
        paiement.setDate(LocalDateTime.now()); 
        paiement.setDette(dette);

        paymentRepository.insert(paiement);
        dette.getPaiements().add(paiement);
    }

    public List<Payment> verifierPaiementsParDette(int detteId) {
        Dette dette = findById(detteId);
        if (dette != null) {
            List<Payment> paiements = paymentRepository.findDetteById(detteId);
            return paiements != null ? paiements : List.of(); 
        } else {
            System.out.println("Dette non trouvée avec ID: " + detteId);
            return List.of();
        }
    }

    public boolean estDetteSoldee(int detteId) {
        Dette dette = findById(detteId);
        if (dette != null) {
            return dette.getMontantRestant() == 0;
        } else {
            System.out.println("Dette non trouvée avec ID: " + detteId);
            return false;
        }
    }

    
}
