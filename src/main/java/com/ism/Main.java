package com.ism;

import java.util.Scanner;

import com.ism.core.IRepositories;
import com.ism.entities.Account;
import com.ism.entities.Article;
import com.ism.entities.Client;
import com.ism.entities.Debt;
import com.ism.entities.Payment;
import com.ism.entities.Role;
import com.ism.repositories.interfaces.IAccountRepository;
import com.ism.repositories.interfaces.IArticleRepository;
import com.ism.repositories.interfaces.IClientRepository;
import com.ism.repositories.interfaces.IDebtRepository;
import com.ism.repositories.AccountRepository;
import com.ism.repositories.PaymentRepository;
import com.ism.repositories.DebtRepository;
import com.ism.repositories.ClientRepository;
import com.ism.repositories.ArticleRepository;
import com.ism.services.AccountService;
import com.ism.services.ArticleService;
import com.ism.services.ClientService;
import com.ism.services.DebtService;
import com.ism.services.PaymentService;
import com.ism.views.AccountView;
import com.ism.views.ArticleView;
import com.ism.views.ClientView;
import com.ism.views.DebtView;
import com.ism.views.PaymentView;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        IAccountRepository<Account> accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        AccountView accountView = new AccountView();

        IClientRepository<Client> clientRepository = new ClientRepository();
        ClientService clientService = new ClientService(clientRepository);
        ClientView clientView = new ClientView();

        IDebtRepository<Debt> debtRepository = new DebtRepository();
        DebtService debtService = new DebtService(debtRepository);
        DebtView debtView = new DebtView();

        IRepositories<Payment> paymentRepository = new PaymentRepository();
        PaymentService paymentService = new PaymentService(paymentRepository);
        PaymentView paymentView = new PaymentView();

        IArticleRepository<Article> articleRepository = new ArticleRepository();
        ArticleService articleService = new ArticleService(articleRepository);
        ArticleView articleView = new ArticleView();

        accountService.save(new Account("YAYA", "SOW", Role.SHOPKEEPER));
        accountService.save(new Account("P", "T", Role.ADMIN));
        

        int choice;

        Account a;

        do {
            System.out.println("Enter your login:");
            String login = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            a = accountService.fetchByLoginAndPassword(login, password);

            if (a == null) {

                System.out.println("\n[User not found...]\n");

            } else if (a.getRole() == Role.SHOPKEEPER) {

                do {
                    switch (choice = menu(scanner)) {
                        case 1 -> {
                            Client client = clientView.instance();
                            if (accountView.askAccountCreate()) {
                                Account account = accountView.instance();
                                client.setAccount(account);
                                account.setClient(client);
                                accountService.save(account);
                                System.out.println("\n[Account created!]");
                            }
                            clientService.save(client);
                            System.out.println("\n[Client saved!]\n");
                        }
                        case 2 -> {
                            System.out.println("\n[All clients:]\n");
                            clientService.show().forEach(System.out::println);
                        }
                        case 3 -> {
                            Client client = clientService.fetchByPhone(clientView.askPhoneNumber());
                            if (client == null) {
                                System.out.println("\n[Client not found...]\n");
                            } else {
                                System.out.println(client);
                            }
                        }
                        case 4 -> {
                            Client client = clientService.fetchByPhone(clientView.askPhoneNumber());
                            if (client == null) {
                                System.out.println("\n[Client not found...]\n");
                            } else {
                                Debt debt = debtView.instance();
                                client.addDebt(debt);
                                debt.setClient(client);
                                debtService.save(debt);
                                System.out.println("\n[Debt saved!]\n");
                            }
                        }
                        case 5 -> {
                            Client client = clientService.fetchByPhone(clientView.askPhoneNumber());
                            if (client == null) {
                                System.out.println("\n[Client not found...]\n");
                            } else {
                                Debt d = debtService.fetchById(client.getDebtList(), debtView.askId());
                                if (d == null) {
                                    System.out.println("\n[Debt not found...]\n");
                                } else {
                                    Payment payment = paymentView.instance();
                                    Debt clientDebt = client.getDebtList().get(d.getId() - 1);
                                    payment.setDebt(clientDebt);
                                    clientDebt.addPayment(payment);
                                    clientDebt.setAmountSent(clientDebt.getAmountSent() + payment.getAmount());
                                    clientDebt.setAmountMissing(clientDebt.getAmountSent() - clientDebt.getAmount());
                                    System.out.println("\n[Payment done!]\n");
                                }
                            }
                        }
                        case 6 -> {
                            Client client = clientService.fetchByPhone(clientView.askPhoneNumber());
                            if (client == null) {
                                System.out.println("\n[Client not found...]\n");
                            } else {
                                client.getDebtList().stream().filter(debt -> debt.getAmountMissing() > 0)
                                        .forEach(System.out::println);
                            }
                        }
                    }
                } while (choice != 7);

            } else if (a.getRole() == Role.ADMIN) {

                do {

                    switch (choice = menuAdmin(scanner)) {
                        case 1 -> {
                            Client client = clientService.fetchByPhone(clientView.askPhoneNumber());
                            if (client == null) {
                                System.out.println("\n[Client not found...]\n");
                            } else if (client.getAccount() != null) {
                                System.out.println("\n[Client already has an account...]\n");
                            } else {
                                Account account = accountView.instance();
                                client.setAccount(account);
                                account.setClient(client);
                                accountService.save(account);
                                System.out.println("\n[Account created!]");
                            }
                        }
                        case 2 -> {
                            Account account = accountView.instance();
                            if (accountView.askAccountRole()) {
                                account.setRole(Role.ADMIN);
                                accountService.save(account);
                            } else {
                                account.setRole(Role.SHOPKEEPER);
                                accountService.save(account);
                            }
                            System.out.println("\n[Account created!]");
                        }
                        case 3 -> {
                            Account account = accountService.fetchById(accountView.askId());
                            if (account == null) {
                                System.out.println("\n[Account not found...]\n");
                            } else {
                                System.out.println(account.getState());
                                accountService.changeState(account);
                                System.out.println("\n[Account status changed]\n");
                            }
                        }
                        case 4 -> {
                            if (accountView.askAccountRole()) {
                                accountService.show().forEach(e -> {
                                    if (e.getRole() == Role.ADMIN) {
                                        System.out.println(e);
                                    }
                                });
                            } else {
                                accountService.show().forEach(e -> {
                                    if (e.getRole() == Role.SHOPKEEPER) {
                                        System.out.println(e);
                                    }
                                });
                            }
                        }
                        case 5 -> {
                            articleService.save(articleView.instance());
                            System.out.println("\n[Article saved!]\n");
                        }
                        case 6 -> {
                            articleService.show().forEach(article -> {
                                if (article.getStock() > 0) {
                                    System.out.println(article);
                                }
                            });
                        }
                    }

                } while (choice != 8);

            }

        } while (a == null);
    }

    public static int menu(Scanner scanner) {
        System.out.println("1- Créer un client");
        System.out.println("2- Lister tous les clients");
        System.out.println("3- Trouver un client par son numéro de téléphone");
        System.out.println("4- Créer une dette");
        System.out.println("5- Effectuer un paiement");
        System.out.println("6- Afficher les dettes non payées");
        System.out.println("7- Quitter");
        return scanner.nextInt();
    }
    
   
    public static int menuAdmin(Scanner scanner) {
        // System.out.println("1- Créer un compte pour un client");
        System.out.println("2- Créer d'autres utilisateurs (admin/boutiquier)");
        System.out.println("3- Activer/Désactiver un compte");
        System.out.println("4- Afficher les comptes utilisateurs par rôle");
        System.out.println("5- Créer un article");
        System.out.println("6- Lister les articles disponibles");
        System.out.println("7- Mettre à jour le stock d'un article");
        System.out.println("8- Quitter");
        return scanner.nextInt();
    }
    
}
