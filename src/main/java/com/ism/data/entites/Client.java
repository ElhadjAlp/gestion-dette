package com.ism.data.entites;

import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 25, unique = true)
    private String surname;

    @Column(length = 11, unique = true)
    private String telephone;

    @Column(length = 255, unique = false)
    private String adresse;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private User user;
    @Transient
      private static int nbr;

    public Client(String surname, String telephone, String adresse) {
        this.id = ++nbr;
        this.surname = surname;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public Client() {
        this.id = ++nbr;
    }
}

