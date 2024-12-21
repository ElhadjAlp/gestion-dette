package com.ism.view.Impl;

import com.ism.data.entites.Article;
import com.ism.data.entites.Client;
import com.ism.data.entites.Dette;
import com.ism.services.ArticleService;
import com.ism.services.ClientService;
import com.ism.services.DetteService;
import com.ism.services.PaymentService;
import com.ism.view.IDetteView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DetteView extends View<Dette> implements IDetteView {
    private final DetteService detteService;
    private final ClientService clientService;
    private final ArticleService articleService;
    private final PaymentService paymentService;
    private final Scanner scanner = new Scanner(System.in);

    public DetteView(DetteService detteService, ClientService clientService, ArticleService articleService, PaymentService paymentService) {
        this.detteService = detteService;
        this.clientService = clientService;
        this.articleService = articleService;
        this.paymentService = paymentService;
    }

    @Override
    public Dette saisir(ClientService clientService,ArticleService articleService) {
        System.out.print("Entrez l'ID du client : ");
        int clientId = scanner.nextInt();
        System.out.println(clientService.findAllClient());
        Client client = clientService.findById(clientId);

        if (client == null) {
            System.out.println("Client non trouvé !");
            return null;
        }

        System.out.print("Entrez le montant total de la dette : ");
        double montant = scanner.nextDouble();

        System.out.print("Entrez le montant versé initial : ");
        double montantVerser = scanner.nextDouble();

        List<Article> articles = new ArrayList<>();
        System.out.print("Combien d'articles dans la dette ? ");
        int nbArticles = scanner.nextInt();

        for (int i = 0; i < nbArticles; i++) {
            System.out.print("Entrez l'ID de l'article : ");
            int articleId = scanner.nextInt();
            Article article = articleService.findById(articleId);

            if (article != null) {
                System.out.print("Entrez la quantité vendue : ");
                int qteVendu = scanner.nextInt();

                System.out.print("Entrez le prix de vente : ");
                double prixVente = scanner.nextDouble();

                
                article.setQuantite(qteVendu);
                article.setPrixVente(prixVente);

                articles.add(article);
            } else {
                System.out.println("Article non trouvé !");
            }
        }

        Dette nouvelleDette = new Dette(client, montant, montantVerser, LocalDateTime.now(), articles);
        System.out.println("Dette ajoutée avec succès !");
        
       
        detteService.insert(nouvelleDette);

        return nouvelleDette;
    }

  
    
}
