package com.ism.view.Impl;

import java.util.List;
import java.util.Scanner;
import com.ism.core.factory.Factory;
import com.ism.core.factory.FactoryService;
import com.ism.core.factory.FactoryView;
import com.ism.data.entites.Client;
import com.ism.data.entites.Dette;
import com.ism.data.entites.Payment;
import com.ism.data.entites.User;
import com.ism.data.enums.RoleEnum;
import com.ism.services.UserService;

public class Application {
    private final FactoryService factoryService;
    private final FactoryView factoryView;
    private final Scanner scanner;
    private final UserService userService;

    public Application(Factory factory, Scanner scanner) {
        this.factoryService = factory.getFactoryService();
        this.factoryView = factory.getFactoryView();
        this.userService = factory.getFactoryService().getInstanceUserService();
        this.scanner = scanner;
    }

    public void run() {
        // Initialisation des utilisateurs
        initialiserUtilisateurs();

        boolean continuerApp = true;
        while (continuerApp) {
            System.out.println("=== Connexion ===");
            System.out.print("Email : ");
            String email = scanner.nextLine();
            System.out.print("Login : ");
            String login = scanner.nextLine();
            System.out.print("Mot de passe : ");
            String password = scanner.nextLine();

            User user = userService.authenticate(login, password);
            if (user == null) {
                System.out.println("Identifiants incorrects !");
            } else {
                continuerApp = afficherMenuSelonRole(user);
            }
        }
        System.out.println("Application terminée.");
    }

    private void initialiserUtilisateurs() {
        userService.createUser(new User("breukh", "YAYA", "SOW", RoleEnum.CLIENT));
        userService.createUser(new User("toto", "p", "p", RoleEnum.ADMIN));
        userService.createUser(new User("tata", "T", "t", RoleEnum.BOUTIQUIER));
        System.out.println("Utilisateurs initialisés :");
        System.out.println(userService.findAllUser());
    }

    private boolean afficherMenuSelonRole(User user) {
        switch (user.getRole()) {
            case ADMIN:
                return runAdminMenu();
            case CLIENT:
                return runClientMenu();
            case BOUTIQUIER:
                return runBoutiquierMenu();
            default:
                System.out.println("Rôle inconnu !");
                return false;
        }
    }

    private boolean runAdminMenu() {
        int choice;
        do {
            choice = menuAdmin();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    factoryService.getInstanceClientService().createClient(factoryView.getInstanceClientView().saisir(factoryService.getInstanceUserService()));
                    break;
                case 2:
                    factoryView.getInstanceClientView().afficher(factoryService.getInstanceClientService().findAllClient());
                    break;
                case 3:
                    factoryService.getInstanceUserService().createUser(factoryView.getInstanceUserView().saisir());
                    break;
                case 4:
                    factoryView.getInstanceUserView().afficher(factoryService.getInstanceUserService().findAllUser());
                    break;
                case 5:
                    factoryService.getInstanceArticleService().insert(factoryView.getInstanceArticleView().saisir(factoryService.getInstanceArticleService()));
                    break;
                case 6:
                    factoryView.getInstanceArticleView().afficher(factoryService.getInstanceArticleService().findAll());
                    break;
                case 7:
                    Dette dette = factoryView.getInstanceDetteView().saisir(factoryService.getInstanceClientService(), factoryService.getInstanceArticleService());
                    if (dette != null) {
                        factoryService.getInstanceDetteService().insert(dette);
                    }
                    break;
                case 8:
                    factoryView.getInstanceDetteView().afficher(factoryService.getInstanceDetteService().findAll());
                    break;
                case 9:
                    System.out.print("Entrez l'ID de la dette pour voir les détails : ");
                    int detteId = scanner.nextInt();
                    afficherDetailDette(detteId);
                    break;
                case 10:
                    System.out.println("Déconnexion...");
                    return true;
                default:
                    System.out.println("Choix invalide !");
                    break;
            }
        } while (choice != 10);
        return false;
    }

    private boolean runClientMenu() {
        int choice;
        do {
            choice = menuClient();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Entrez l'ID du client : ");
                    int clientId = scanner.nextInt();
                    scanner.nextLine();
                    Client client = factoryService.getInstanceClientService().findById(factoryService.getInstanceClientService().findAllClient(), clientId);
                    if (client != null) {
                        factoryView.getInstanceClientView().afficher(client);
                    } else {
                        System.out.println("Client non trouvé !");
                    }
                    break;
                case 2:
                    Dette detteClient = factoryView.getInstanceDetteView().saisir(factoryService.getInstanceClientService(), factoryService.getInstanceArticleService());
                    if (detteClient != null) {
                        factoryService.getInstanceDetteService().insert(detteClient);
                    }
                    break;
                case 3:
                    factoryView.getInstanceDetteView().afficher(factoryService.getInstanceDetteService().findAll());
                    break;
                case 4:
                    System.out.print("Entrez l'ID de la dette : ");
                    int detteId = scanner.nextInt();
                    System.out.print("Entrez le montant du paiement : ");
                    double montant = scanner.nextDouble();
                    factoryService.getInstanceDetteService().effectuerPaiement(detteId, montant);
                    break;
                case 5:
                    System.out.print("Entrez l'ID de la dette pour voir les détails : ");
                    int clientDetteId = scanner.nextInt();
                    afficherDetailDette(clientDetteId);
                    break;
                case 6:
                    System.out.println("Déconnexion...");
                    return true;
                default:
                    System.out.println("Choix invalide !");
                    break;
            }
        } while (choice != 6);
        return false;
    }

    private boolean runBoutiquierMenu() {
        int choice;
        do {
            choice = menuBoutiquier();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    factoryView.getInstanceDetteView().afficher(factoryService.getInstanceDetteService().findAll());
                    break;
                case 2:
                    System.out.println("Veuillez entrer l'ID de la dette pour vérifier les paiements :");
                int detteId = scanner.nextInt();
                List<Payment> paiements = factoryService.getInstanceDetteService().verifierPaiementsParDette(detteId);
                
                if (paiements == null ||paiements.isEmpty()) {
                    System.out.println("Aucun paiement enregistré pour cette dette.");
                } else {
                    System.out.println("Liste des paiements pour la dette ID " + detteId + " :");
                    for (Payment paiement : paiements) {
                        System.out.println("Montant : " + paiement.getMontant() + ", Date : " + paiement.getDate());
                    }
                }

                
                if (factoryService.getInstanceDetteService().estDetteSoldee(detteId)) {
                    System.out.println("Cette dette est totalement réglée.");
                } else {
                    System.out.println("Cette dette n'est pas encore soldée.");
                }
                break;
                   
                case 3:
                    System.out.println("Déconnexion...");
                    return true;
                default:
                    System.out.println("Choix invalide !");
                    break;
            }
        } while (choice != 3);
        return false;
    }

    private int menuAdmin() {
        System.out.println("=== Menu Admin ===");
        System.out.println("1- Créer un client");
        System.out.println("2- Lister les clients");
        System.out.println("3- Créer un utilisateur");
        System.out.println("4- Lister les utilisateurs");
        System.out.println("5- Créer un article");
        System.out.println("6- Lister les articles");
        System.out.println("7- Créer une dette");
        System.out.println("8- Lister les dettes");
        System.out.println("9- Voir les détails d'une dette");
        System.out.println("10- Déconnexion");
        System.out.print("Faites votre choix : ");
        return scanner.nextInt();
    }

    private int menuClient() {
        System.out.println("=== Menu Client ===");
        System.out.println("1- Voir mes informations");
        System.out.println("2- Créer une dette");
        System.out.println("3- Lister mes dettes");
        System.out.println("4- Effectuer un paiement");
        System.out.println("5- Voir les détails d'une dette");
        System.out.println("6- Déconnexion");
        System.out.print("Faites votre choix : ");
        return scanner.nextInt();
    }

    private int menuBoutiquier() {
        System.out.println("=== Menu Boutiquier ===");
        System.out.println("1- Lister les dettes");
        System.out.println("2- Vérifier les paiements");
        System.out.println("3- Déconnexion");
        System.out.print("Faites votre choix : ");
        return scanner.nextInt();
    }

    private void afficherDetailDette(int detteId) {
        Dette dette = factoryService.getInstanceDetteService().findById(detteId);
        if (dette != null) {
            System.out.println("Détails de la dette :");
            System.out.println("ID : " + dette.getId());
            System.out.println("Client : " + dette.getClient().getId() + " " + dette.getClient().getSurname());
            System.out.println("Montant : " + dette.getMontant());
            System.out.println("Montant versé : " + dette.getMontantVerser());
            System.out.println("Montant restant : " + dette.getMontantRestant());
            System.out.println("Date : " + dette.getDate());
        } else {
            System.out.println("Dette non trouvée !");
        }
    }
}
