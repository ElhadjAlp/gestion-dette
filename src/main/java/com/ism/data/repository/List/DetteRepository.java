package com.ism.data.repository.List;


import java.util.ArrayList;
import java.util.List;

import com.ism.data.entites.Dette;

public class DetteRepository {

    private List<Dette> dettes; 

    public DetteRepository() {
        this.dettes = new ArrayList<>();
    }

    public void ajouterDette(Dette dette) {
        dettes.add(dette);
    }

    public List<Dette> obtenirToutesLesDettes() {
        return dettes;
    }

    public List<Dette> trouverDetteParMontant(double montant) {
        List<Dette> result = new ArrayList<>();
        for (Dette dette : dettes) {
            if (dette.getMontant() == montant) {
                result.add(dette);
            }
        }
        return result;
    }


    public void mettreAJourDette(Dette detteAMettreAJour) {
        for (int i = 0; i < dettes.size(); i++) {
            if (dettes.get(i).equals(detteAMettreAJour)) {
                dettes.set(i, detteAMettreAJour);
                break;
            }
        }
    }

    public void supprimerDette(Dette detteASupprimer) {
        dettes.remove(detteASupprimer);
    }

    public List<Dette> obtenirDettesNonSoldees() {
        List<Dette> dettesNonSoldees = new ArrayList<>();
        for (Dette dette : dettes) {
            if (dette.getMontant() > 0) {
                dettesNonSoldees.add(dette);
            }
        }
        return dettesNonSoldees;
    }

    public List<Dette> archiverDettesSoldees() {
        List<Dette> dettesSoldees = new ArrayList<>();
        for (Dette dette : dettes) {
            if (dette.getMontant() == 0) {
                dettesSoldees.add(dette);
            }
        }
        return dettesSoldees;
    }
}


