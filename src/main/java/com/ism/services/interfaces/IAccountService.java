package com.ism.services.interfaces;

import com.ism.core.IServices;
import com.ism.entities.Account;

public interface IAccountService extends IServices<Account> {
    Account fetchByLoginAndPassword(String login, String password);
    void changeState(Account object);
    Account fetchById(int object);
}

