package com.ism.views;

import java.util.Scanner;

import com.ism.core.ViewsImpl;
import com.ism.entities.Payment;



public class PaymentView extends ViewsImpl<Payment> {
    @Override
    public Payment instance() {
        Payment payment = new Payment();
        System.out.println("Date du paiement :");
        payment.setDate(scanner.nextLine());
        System.out.println("Montant du paiement :");
        payment.setAmount(scanner.nextInt());
        return payment;
    }
    
}

