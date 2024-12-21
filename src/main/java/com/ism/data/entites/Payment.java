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
    @JoinColumn(name = "dette_id")
    private Dette dette; 
    

}
     

    
