package com.ism.data.entites;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@Entity
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;

    private Double prixVente;
    private Integer qteVendu;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false) 
    private Article article;

    @ManyToOne
    @JoinColumn(name = "dette_id") 
    private Dette dette;

    
}
