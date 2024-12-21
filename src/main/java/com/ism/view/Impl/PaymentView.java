package com.ism.view.Impl;

import com.ism.data.entites.Payment;
import com.ism.view.IPaymentView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Format de date invalide, veuillez réessayer.");
            return null; 
        }

        Payment payment = new Payment();
        payment.setMontant(montant);
        payment.setDate(date.atStartOfDay()); 

        return payment;
    }
}
