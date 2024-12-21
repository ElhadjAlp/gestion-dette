package com.ism.data.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String libelle;
    private String reference;
    private int qteStock;
    private double prix;

    @Transient
    private static int nbr;

    @ManyToMany(mappedBy = "articles")
    private List<Detail> details;

    public Article() {
      this.id = ++nbr;
    }

    public void setQuantite(int quantite) {
        this.qteStock = quantite;
    }

    public void setPrixVente(double prixVente) {
        this.prix = prixVente;
    }
}
