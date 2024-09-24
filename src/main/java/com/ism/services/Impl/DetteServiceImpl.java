package com.ism.services.Impl;

import com.ism.data.entites.Dette;
import com.ism.data.repository.List.DetteRepository;

import java.util.List;

public class DetteServiceImpl {

    private DetteRepository detteRepository;

    public DetteServiceImpl(DetteRepository detteRepository) {
        this.detteRepository = detteRepository;
    }

    public void ajouterNouvelleDette(Dette dette) {
        detteRepository.ajouterDette(dette);
    }

    public List<Dette> obtenirToutesLesDettes() {
        return detteRepository.obtenirToutesLesDettes();
    }

    public List<Dette> trouverDetteParMontant(double montant) {
        return detteRepository.trouverDetteParMontant(montant);
    }


    public void mettreAJourDette(Dette dette) {
        detteRepository.mettreAJourDette(dette);
    }

  
    public void supprimerDette(Dette dette) {
        detteRepository.supprimerDette(dette);
    }


    public List<Dette> obtenirDettesNonSoldees() {
        return detteRepository.obtenirDettesNonSoldees();
    }


    public List<Dette> archiverDettesSoldees() {
        return detteRepository.archiverDettesSoldees();
    }


    public boolean estDetteSoldee(Dette dette) {
        return dette.isPaid();
    }
}

