package com.ism.data.repository.List;

import com.ism.core.Repository.impl.RepositoryListImpl;
import com.ism.data.entites.Dette;
import com.ism.data.repository.DetteRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DetteRepositoryList extends RepositoryListImpl<Dette> implements DetteRepository {

    @Override
    public Dette selectById(int id) {
        return selectAll().stream()
                .filter(dette -> dette.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateDette(Dette updatedDette) {
        List<Dette> dettes = selectAll();
        for (int i = 0; i < dettes.size(); i++) {
            Dette dette = dettes.get(i);
            if (dette.getId() == updatedDette.getId()) {
                dette.setMontant(updatedDette.getMontant());
                dette.setMontantVerser(updatedDette.getMontantVerser());
                dette.setClient(updatedDette.getClient());
                dettes.set(i, dette);
                break; 
            }
        }
    }

    @Override
    public void deleteDette(int id) {
        List<Dette> dettes = selectAll();
        dettes = dettes.stream()
                .filter(dette -> dette.getId() != id)
                .collect(Collectors.toList());
        this.setData(dettes);
    }
}






