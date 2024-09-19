package com.ism.repositories;

import com.ism.core.RepositoriesImpl;
import com.ism.entities.Account;
import com.ism.repositories.interfaces.IAccountRepository;

public class AccountRepository extends RepositoriesImpl<Account> implements IAccountRepository<Account> {

    @Override
    public Account findByLoginAndPassword(String login, String password) {
        return list.stream().filter(account -> account.getLogin().compareTo(login) == 0 && account.getPassword().compareTo(password) == 0).findFirst().orElse(null);
    }

    @Override
    public Account findById(int id) {
        return list.stream().filter(account -> account.getId() == id).findFirst().orElse(null);
    }
}
