package com.ism.data.entites;

import java.util.Date;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode()
@Entity
@Table(name = "paiements")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date date;
    private Double montant;

    @ManyToOne
    private Dette dette; 
    
    @Transient
    private static int nbr;

    public Payment() {
        this.id = ++nbr;
      }

}
     

    
