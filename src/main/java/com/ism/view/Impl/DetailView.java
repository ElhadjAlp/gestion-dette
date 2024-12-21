package com.ism.view.Impl;

import com.ism.data.entites.Detail;
import com.ism.data.entites.Article; 
import com.ism.data.entites.Dette; 
import com.ism.services.DetailService;
import com.ism.view.IDetailView;

import java.util.Scanner;

public class DetailView implements IDetailView {
    private final DetailService detailService; 
    private final Scanner scanner; 

    public DetailView(DetailService detailService) {
        this.detailService = detailService; 
        this.scanner = new Scanner(System.in); 
    }

    @Override
    public Detail saisir() {
        Detail detail = new Detail(); 

        System.out.print("Entrez le prix de vente : ");
        detail.setPrixVente(scanner.nextDouble());
        
        System.out.print("Entrez la quantité vendue : ");
        detail.setQteVendu(scanner.nextInt());
        
        System.out.print("Entrez l'ID de l'article : ");
        int articleId = scanner.nextInt();
        Article article = new Article();
        article.setId(articleId); 
        detail.setArticle(article); 
        
        System.out.print("Entrez l'ID de la dette : ");
        int detteId = scanner.nextInt();
        Dette dette = new Dette(null, detteId, detteId, null, null); 
        dette.setId(detteId); 
        detail.setDette(dette); 

        return detail; 
    }
}

