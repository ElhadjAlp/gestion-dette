package com.ism.view;

import com.ism.data.entites.User;

public interface IUserView extends IView<User> {
    User saisir();
}