package com.ism.repositories;


import com.ism.core.RepositoriesImpl;
import com.ism.entities.Debt;
import com.ism.repositories.interfaces.IDebtRepository;

import java.util.ArrayList;
import java.util.List;


public class DebtRepository extends RepositoriesImpl<Debt> implements IDebtRepository<Debt> {
    @Override
    public Debt findById(int id) {
        return list.stream().filter(debt -> debt.getId() == id).findFirst().orElse(null);
    }
}
