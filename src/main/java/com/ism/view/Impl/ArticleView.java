package com.ism.view.Impl;

import com.ism.data.entites.Article;
import com.ism.services.ArticleService;
import com.ism.view.IArticleView;

public class ArticleView extends View<Article> implements IArticleView {

    @Override
    public Article saisir(ArticleService articleService) {
        Article article = new Article();
        System.out.print("Entrez le libellé : ");
        article.setLibelle(scanner.nextLine());
        System.out.print("Entrez la référence : ");
        article.setReference(scanner.nextLine());
        System.out.print("Entrez la quantité en stock : ");
        article.setQteStock(scanner.nextInt());
        System.out.print("Entrez le prix : ");
        article.setPrix(scanner.nextDouble());
        scanner.nextLine();
        return article;
    }
}
