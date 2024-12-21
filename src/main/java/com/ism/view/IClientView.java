package com.ism.view;

import com.ism.data.entites.Client;
import com.ism.services.UserService;

public interface IClientView extends IView<Client> {
    Client saisir(UserService userService);
}
