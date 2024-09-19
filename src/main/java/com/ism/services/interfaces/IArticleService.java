package com.ism.services.interfaces;


import java.util.List;

import com.ism.core.IServices;
import com.ism.entities.Article;

public interface IArticleService extends IServices<Article> {
    Article fetchById(int object);
}
