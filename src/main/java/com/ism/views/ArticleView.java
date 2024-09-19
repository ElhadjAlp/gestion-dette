package com.ism.views;

import java.util.Scanner;

import com.ism.core.ViewsImpl;
import com.ism.entities.Article;

public class ArticleView extends ViewsImpl<Article> {
    @Override
    public Article instance() {
        Article article = new Article();
        System.out.println("Libellé de l'article :");
        article.setLabel(scanner.nextLine());
        System.out.println("Stock de l'article :");
        article.setStock(scanner.nextInt());
        scanner.nextLine();
        return article;
    }
    
}

