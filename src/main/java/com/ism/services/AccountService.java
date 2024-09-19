package com.ism.services;




import java.util.List;

import com.ism.entities.Account;
import com.ism.entities.State;
import com.ism.repositories.interfaces.IAccountRepository;
import com.ism.services.interfaces.IAccountService;

public class AccountService implements IAccountService {
    private IAccountRepository<Account> accountRepository;

    public AccountService() {
    }

    public AccountService(IAccountRepository<Account> accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void save(Account account) {
        accountRepository.insert(account);
    }

    @Override
    public List<Account> show() {
        return accountRepository.selectAll();
    }

    @Override
    public Account fetchByLoginAndPassword(String login, String password) {
        return accountRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public void changeState(Account account) {
        if (account.getState() == State.ENABLED) {
            account.setState(State.DISABLED);
        } else {
            account.setState(State.ENABLED);
        }
    }

    @Override
    public Account fetchById(int id) {
        return accountRepository.selectAll().stream().filter(account -> account.getId() == id).findFirst().orElse(null);
    }
}