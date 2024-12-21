 package com.ism.data.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;

import com.ism.data.enums.RoleEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(length = 25, unique = true)
    private String login;
    
    private String prenom;
    private String nom;
    private String password;
    @Transient
    private static int nbr;
    
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    
    @ColumnDefault(value = "true")
    private boolean etat;

    
    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "clients_id", nullable = true)
    private Client client;
  
    public User(String email, String login, String password, RoleEnum role) {
        this.id = ++nbr;
        this.login = login;
        this.password = password;
        this.role = role;
        this.etat = true; 
    }
    public User() {
        this.id = ++nbr;
  }
}
