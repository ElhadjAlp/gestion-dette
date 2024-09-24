package com.ism.data.entites;

import com.ism.data.enums.RoleEnum;

import lombok.Data;

@Data
public class User {
    private int id;
    private String login;
    private String prenom;
    private String nom;
    private String password;
    private RoleEnum role;
    private boolean etat;

    private Client client;

}
