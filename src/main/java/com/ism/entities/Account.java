package com.ism.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Account {
    private static int counter;
    private int id;
    private String login;
    private String password;

    private Role role;
    private State state;

    private Client client;

    public Account() {
        id = ++counter;
    }

    public Account(String login, String password, Role role) {
        id = ++counter;
        this.login = login;
        this.password = password;
        this.role = role;
        this.state = State.ENABLED;
    }
}