package com.ism.data.repository.List;

import com.ism.core.Repository.impl.RepositoryListImpl;
import com.ism.data.entites.Article;
import com.ism.data.repository.ArticleRepository;

public class ArticleRepositoryList extends RepositoryListImpl<Article> implements ArticleRepository {

    @Override
    public Article selectById(int id) {
        return list.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Article findByArticle(Article article) {

        return list.stream()
                .filter(a -> a.getLibelle().equals(article.getLibelle()))
                .findFirst()
                .orElse(null);
    }
}

