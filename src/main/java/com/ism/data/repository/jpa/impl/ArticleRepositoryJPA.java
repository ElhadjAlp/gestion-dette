package com.ism.data.repository.jpa.impl;

import com.ism.core.Repository.impl.RepositoryJPAImp;
import com.ism.data.entites.Article;
import com.ism.data.repository.ArticleRepository;

public class ArticleRepositoryJPA extends RepositoryJPAImp<Article> implements ArticleRepository {

    public ArticleRepositoryJPA() {
        super(Article.class);
    }

    @Override
    public Article selectById(int id) {
        return findById(id); 
    }

    @Override
    public Article findByArticle(Article article) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByArticle'");
    }

    public Article findById(int id) {
        return null;
    }


}


