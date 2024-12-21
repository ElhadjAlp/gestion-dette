package com.ism.view.Impl;

import com.ism.data.entites.Payment;
import com.ism.view.IPaymentView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PaymentView implements IPaymentView {
    private final Scanner scanner;

    public PaymentView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Payment saisir() {
        System.out.println("Veuillez saisir les détails du paiement :");

        System.out.print("Montant du paiement : ");
        double montant = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.print("Date du paiement (format: YYYY-MM-DD) : ");
        String dateString = scanner.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Format de date invalide, veuillez réessayer.");
            return null; 
        }

        Payment payment = new Payment();
        payment.setMontant(montant);
        payment.setDate(date); 

        return payment;
    }
}
