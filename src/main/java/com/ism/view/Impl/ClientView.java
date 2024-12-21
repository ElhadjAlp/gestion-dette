package com.ism.view.Impl;

import java.util.Scanner;
import com.ism.data.entites.Client;
import com.ism.data.entites.User;
import com.ism.data.enums.RoleEnum;
import com.ism.services.UserService;
import com.ism.view.IClientView;

public class ClientView extends View<Client> implements IClientView {
    private UserService userService;
    public ClientView( UserService userService){
        this.userService = userService;
    }
    @Override
    public Client saisir(UserService userService) {
        Scanner scanner = new Scanner(System.in);
        
        // Créer une instance de Client avec le constructeur
        Client client = new Client();

        System.out.println("Veuillez entrer le nom de famille du client :");
        String surname = scanner.nextLine();
        client.setSurname(surname);
        System.out.println("Veuillez entrer le numéro de téléphone du client :");
        String telephone = scanner.nextLine();
        client.setTelephone(telephone);
        System.out.println("Veuillez entrer l'adresse du client :");
        String adresse = scanner.nextLine();
        client.setAdresse(adresse);
        System.out.println("Voulez-vous associer un compte utilisateur ? (o/n) :");
        char res = scanner.next().charAt(0);
        scanner.nextLine(); 

       
        if (res == 'o') {
            System.out.println("Veuillez entrer l'email de l'utilisateur :");
            String email = scanner.nextLine();

            System.out.println("Veuillez entrer le login de l'utilisateur :");
            String login = scanner.nextLine();

            System.out.println("Veuillez entrer le mot de passe de l'utilisateur :");
            String password = scanner.nextLine();

            
            User user = new User(email, login, password, RoleEnum.CLIENT);
            userService.createUser(user);
            client.setUser(user);
        }

        return client;
    }
}
