package com.ism.services;

import com.ism.data.entites.Article;
import java.util.List;

public interface ArticleService {

    void insert(Article article);

    List<Article> findAll(); 

    Article findById(int id); 

    Article findByArticle(Article article); 
}

