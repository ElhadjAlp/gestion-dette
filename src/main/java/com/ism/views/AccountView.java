package com.ism.views;

import java.util.Scanner;

import com.ism.core.ViewsImpl;
import com.ism.entities.Account;
import com.ism.entities.Role;
import com.ism.entities.State;

public class AccountView extends ViewsImpl<Account> {
    @Override
    public Account instance() {
        Account account = new Account();
        System.out.println("Créez un identifiant :");
        account.setLogin(scanner.nextLine());
        System.out.println("Créez un mot de passe :");
        account.setPassword(scanner.nextLine());
        account.setRole(Role.CLIENT);
        account.setState(State.ENABLED);
        return account;
    }
    public boolean askAccountCreate() {
        System.out.println("Attribuer un compte ?\n1- Oui\n2- Non");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice == 1;
    }
    public boolean askAccountRole() {
        System.out.println("Admin ou Boutiquier ?\n1- Admin\n2- Boutiquier");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice == 1;
    }
    
    
    
}
