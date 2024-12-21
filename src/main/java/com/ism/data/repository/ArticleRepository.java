package com.ism.data.repository;

import com.ism.core.Repository.Repository;
import com.ism.data.entites.Article;

public interface ArticleRepository extends Repository<Article> {
    
    Article selectById(int id); 
    Article findByArticle(Article article); 
}


