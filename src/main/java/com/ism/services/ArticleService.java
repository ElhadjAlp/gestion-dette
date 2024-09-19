package com.ism.services;



import java.util.List;

import com.ism.core.IRepositories;
import com.ism.core.IServices;
import com.ism.entities.Article;

public class ArticleService implements IServices<Article> {
    private IRepositories<Article> articleRepository;

    public ArticleService() {
    }

    public ArticleService(IRepositories<Article> articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void save(Article article) {
        articleRepository.insert(article);
    }

    @Override
    public List<Article> show() {
        return articleRepository.selectAll();
    }
}
