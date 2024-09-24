package com.ism.view;

import java.util.List;
import java.util.Scanner;

import com.ism.core.factory.Factory;
import com.ism.data.entites.Client;
import com.ism.data.entites.User;
import com.ism.data.enums.RoleEnum;
import com.ism.services.ClientService;
import com.ism.services.UserService;
import com.ism.services.Impl.ClientServiceImpl;
import com.ism.services.Impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        int choix;
        Scanner scanner = new Scanner(System.in);


        ClientService clientServiceImpl = new ClientServiceImpl(
                Factory.getInstanceClientRepository());

        UserService userServiceImpl = new UserServiceImpl(Factory.getInstanceUserRepository());

        Client client;
        do {
            System.out.println("1-Creer Client");
            System.out.println("2-Lister Client");
            System.out.println("3-Rechercher  Client Par Telephone");
            System.out.println("4-Creer un Compte User");
            System.out.println("5-Lister les comptes User");
            System.out.println("6-Quitter");
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    // Entite
                    client = new Client();
                    System.out.println("Entrer le surnom");
                    String surname = scanner.nextLine();
                    if (clientServiceImpl.searchClientBySurname(surname) != null) {
                        System.out.println("Le surname esxiste deja");
                    } else {
                        client.setSurname(surname);
                        System.out.println("Entrer le Telephone");
                        client.setTelephone(scanner.nextLine());
                        System.out.println("Entrer l'adresse");
                        client.setAdresse(scanner.nextLine());
                        System.out.println("Voulez vous associez un compte a ce client O/N");
                        char res = scanner.next().charAt(0);
                        scanner.nextLine();
                        if (res == 'O') {
                            User user = new User();
                            System.out.println("Entrer Nom ");
                            user.setNom(scanner.nextLine());
                            System.out.println("Entrer le Prenom");
                            user.setPrenom(scanner.nextLine());
                            System.out.println("Entrer le Login");
                            user.setLogin(scanner.nextLine());
                            System.out.println("Entrer le Password");
                            user.setPassword(scanner.nextLine());
                            user.setRole(RoleEnum.CLIENT);
                            client.setUser(user);
                        }

                        clientServiceImpl.createClient(client);

                    }
                    break;
                case 2:
                    List<Client> list = clientServiceImpl.findAllClient();
                    list.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Entrer le Telephone");
                    String tel = scanner.nextLine();
                    client = clientServiceImpl.searchClient(tel);
                    if (client == null) {
                        System.out.println("Pas de client");
                    } else {
                        System.out.println(client);
                    }

                    break;
                case 4:
                    break;

                case 5:
                    List<User> listUsers = userServiceImpl.findAllUser();
                    listUsers.forEach(System.out::println);
                    break;

                default:
                    break;
            }

        } while (choix != 6);

        scanner.close();
    }
}
