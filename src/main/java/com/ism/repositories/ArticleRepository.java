package com.ism.repositories;

import com.ism.core.RepositoriesImpl;
import com.ism.entities.Article;
import com.ism.repositories.interfaces.IArticleRepository;

public class ArticleRepository extends RepositoriesImpl<Article> implements IArticleRepository<Article> {
    @Override
    public Article findById(int id) {
        return list.stream().filter(debt -> debt.getId() == id).findFirst().orElse(null);
    }
}