package com.ism.services.Impl;

import com.ism.data.entites.Article;
import com.ism.data.repository.ArticleRepository;
import com.ism.services.ArticleService;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void insert(Article article) {
        articleRepository.insert(article);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.selectAll(); 
    }

    @Override
    public Article findById(int id) {
        return articleRepository.selectById(id); 
    }

    @Override
    public Article findByArticle(Article article) {
        return articleRepository.findByArticle(article);
    }
}

