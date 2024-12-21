package com.ism.data.entites;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name = "paiements") 
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;

    private LocalDateTime date; 
    private Double montant;

    @ManyToOne
    @JoinColumn(name = "dette_id", nullable = false) 
    private Dette dette;

    
    public Payment() {
       
    }

    
    public Payment(LocalDateTime date, Double montant, Dette dette) {
        this.date = date;
        this.montant = montant;
        this.dette = dette;
    }


    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
