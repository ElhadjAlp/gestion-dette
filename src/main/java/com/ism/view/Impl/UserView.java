package com.ism.view.Impl;


import com.ism.data.entites.User;
import com.ism.view.IUserView;

public class UserView extends View<User> implements IUserView {
    public User saisir() {
        User user = new User();
        System.out.print("Entrez le login : ");
        user.setLogin(scanner.nextLine());
        System.out.print("Entrez le mot de passe : ");
        user.setPassword(scanner.nextLine());
        System.out.print("Entrez le role: ");
        user.setPassword(scanner.nextLine());
        return user;
    }
}
