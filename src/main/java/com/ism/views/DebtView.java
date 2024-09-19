package com.ism.views;

import java.util.Scanner;

import com.ism.core.ViewsImpl;
import com.ism.entities.Debt;


public class DebtView extends ViewsImpl<Debt> {
    @Override
    public Debt instance() {
        Debt debt = new Debt();
        System.out.println("Date de la dette :");
        debt.setDate(scanner.nextLine());
        System.out.println("Montant de la dette :");
        debt.setAmount(scanner.nextInt());
        debt.setAmountMissing(debt.getAmount());
        debt.setAmountSent(0);
        return debt;
    }
    
}


