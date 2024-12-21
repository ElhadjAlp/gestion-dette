package com.ism.data.entites;

import javax.persistence.*;
import com.ism.data.enums.EtatDette;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "dettes")
public class Dette {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Double montant;
    private Double montantVerser;

    @Transient
    private Double montantRestant;

    private LocalDateTime date; 

    @ManyToOne
    private Client client;

    @ManyToMany(mappedBy = "dettes")
    private List<Detail> details = new ArrayList<>();

    @OneToMany(mappedBy = "dette")
    private List<Payment> paiements = new ArrayList<>(); 

    @Enumerated(EnumType.STRING)
    private EtatDette etat; 
    
    @Transient
    private static int nbr;

    public Dette(Client client, double montant, double montantVerser, LocalDateTime date, List<Article> articles) {
        this.client = client;
        this.montant = montant;
        this.montantVerser = montantVerser;
        this.date= date;
        this.details = new ArrayList<>();
        this.etat = EtatDette.EN_COURS; 

        for (Article article : articles) {
            
            Detail detail = new Detail(); 
            this.details.add(detail);
        }
    }

    public Double getMontantRestant() {
        return montant - montantVerser;
    }

    public void addPayment(Payment payment) {
        paiements.add(payment);
    }

    public void addDetail(Detail detail) {
        details.add(detail);
    }

    private void updateEtat() {
        if (montantVerser >= montant) {
            this.etat = EtatDette.SOLDEE; // Mettre à jour l'état à SOLDEE
        } else if (montantVerser == 0) {
            this.etat = EtatDette.ANNULE; // Optionnel : gérer les annulations
        } else {
            this.etat = EtatDette.EN_COURS; // Sinon, garder l'état en cours
        }
    }
    public Dette() {
        
        this.paiements = new ArrayList<>();
      }
}
