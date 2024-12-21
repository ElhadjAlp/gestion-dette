package com.ism.core.factory.impl;

import com.ism.core.factory.FactoryService;
import com.ism.core.factory.FactoryView;
import com.ism.view.Impl.ArticleView;
import com.ism.view.Impl.ClientView;
import com.ism.view.Impl.DetteView;
import com.ism.view.Impl.UserView;

public class FactoryViewImpl implements FactoryView {
    private ClientView clientView;
    private UserView userView;
    private ArticleView articleView;
    private DetteView detteView;
    private final FactoryService factoryServiceImpl;

    public FactoryViewImpl(FactoryService factoryServiceImpl) {
        this.factoryServiceImpl = factoryServiceImpl;
    }

    @Override
    public ClientView getInstanceClientView() {
        if (clientView == null) {
            clientView = new ClientView(factoryServiceImpl.getInstanceUserService());
        }
        return clientView;
    }

    @Override
    public UserView getInstanceUserView() {
        if (userView == null) {
            userView = new UserView();
        }
        return userView; 
    }

    @Override
    public ArticleView getInstanceArticleView() {
        if (articleView == null) {
            articleView = new ArticleView();
        }
        return articleView; 
    }

    @Override
    public DetteView getInstanceDetteView() {
        if (detteView == null) {
            detteView = new DetteView(factoryServiceImpl.getInstanceDetteService(), 
                                      factoryServiceImpl.getInstanceClientService(), 
                                      factoryServiceImpl.getInstanceArticleService(), 
                                      factoryServiceImpl.getInstancePaymentService());
        }
        return detteView; 
    }
}
