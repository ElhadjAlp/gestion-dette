package com.ism.data.entites;

import javax.persistence.*;
import com.ism.data.enums.EtatDette;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor 
@Entity
@Table(name = "dettes")
public class Dette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;

    private Double montant;
    private Double montantVerser;
    
    @Transient
    private Double montantRestant;

    private Date date; 

    @ManyToOne
    @JoinColumn(name = "client_id") 
    private Client client;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "detail_dette", 
        joinColumns = @JoinColumn(name = "dette_id"),
        inverseJoinColumns = @JoinColumn(name = "detail_id")
    )
    private List<Detail> details = new ArrayList<>();

    @OneToMany(mappedBy = "dette", cascade = CascadeType.ALL)
    private List<Payment> paiements = new ArrayList<>(); 

    @Enumerated(EnumType.STRING)
    private EtatDette etat; 

    public Dette(Client client, double montant, double montantVerser, Date date, List<Article> articles) {
        this.client = client;
        this.montant = montant;
        this.montantVerser = montantVerser;
        this.date = date;
        this.details = new ArrayList<>();
        this.etat = EtatDette.EN_COURS; 

        for (Article article : articles) {
            Detail detail = new Detail();
            detail.setArticle(article); 
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
            this.etat = EtatDette.SOLDEE; 
        } else if (montantVerser == 0) {
            this.etat = EtatDette.ANNULE; 
        } else {
            this.etat = EtatDette.EN_COURS; 
        }
    }
}

