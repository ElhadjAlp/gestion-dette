package com.ism.core.factory;

import com.ism.view.Impl.ClientView;
import com.ism.view.Impl.DetteView;
import com.ism.view.Impl.UserView;
import com.ism.view.Impl.ArticleView;

public interface FactoryView {
    ClientView getInstanceClientView();
    UserView getInstanceUserView();
    ArticleView getInstanceArticleView(); 
    DetteView getInstanceDetteView();
}

