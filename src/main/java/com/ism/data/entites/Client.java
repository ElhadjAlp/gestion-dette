package com.ism.data.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@EqualsAndHashCode()
public class Client {
    private int id;
    private String surname;
    private String telephone;
    private String adresse;


    private User user;
}
