package com.ism.views;

import java.util.Scanner;

import com.ism.core.ViewsImpl;
import com.ism.entities.Client;



public class ClientView extends ViewsImpl<Client> {
    @Override
    public Client instance() {
        Client client = new Client();
        System.out.println("Nom du client :");
        client.setSurname(scanner.nextLine());
        System.out.println("Numéro de téléphone du client :");
        client.setPhone(askPhoneNumber());
        System.out.println("Adresse du client :");
        client.setAddress(scanner.nextLine());
        return client;
    }
    public String askPhoneNumber() {
        System.out.println("Saisissez un numéro de téléphone :");
        return scanner.nextLine();
    }
    
    
}

