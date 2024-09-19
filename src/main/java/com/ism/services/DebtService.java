package com.ism.services;



import java.util.List;

import com.ism.entities.Debt;
import com.ism.repositories.interfaces.IDebtRepository;
import com.ism.services.interfaces.IDebtService;

public class DebtService implements IDebtService {
    private IDebtRepository<Debt> debtRepository;

    public DebtService() {
    }

    public DebtService(IDebtRepository<Debt> debtRepository) {
        this.debtRepository = debtRepository;
    }

    @Override
    public void save(Debt debt) {
        debtRepository.insert(debt);
    }

    @Override
    public List<Debt> show() {
        return debtRepository.selectAll();
    }

    @Override
    public Debt fetchById(List<Debt> list, int id) {
        return list.stream().filter(debt -> debt.getId() == id).findFirst().orElse(null);
    }
}

