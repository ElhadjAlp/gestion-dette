package com.ism.core.factory.impl;

import com.ism.core.factory.FactoryRepository;
import com.ism.core.factory.FactoryService;
import com.ism.services.ArticleService;
import com.ism.services.ClientService;
import com.ism.services.DetailService;
import com.ism.services.DetteService;
import com.ism.services.PaymentService;
import com.ism.services.UserService;
import com.ism.services.Impl.ArticleServiceImpl;
import com.ism.services.Impl.ClientServiceImpl;
import com.ism.services.Impl.DetteServiceImpl;
import com.ism.services.Impl.PaymentServiceImpl;
import com.ism.services.Impl.DetailServiceImpl;
import com.ism.services.Impl.UserServiceImpl;

public class FactoryServiceImpl implements FactoryService {
    private ClientService clientService;
    private UserService userService;
    private ArticleService articleService;
    private DetteService detteService;
    private PaymentService paymentService;
    private DetailService detailService;
    private FactoryRepository factoryRepository;

    public FactoryServiceImpl(FactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }

    @Override
    public ClientService getInstanceClientService() {
        if (clientService == null) {
            clientService = new ClientServiceImpl(factoryRepository.getInstanceClientRepository());
        }
        return clientService;
    }

    @Override
    public UserService getInstanceUserService() {
        if (userService == null) {
            userService = new UserServiceImpl(factoryRepository.getInstanceUserRepository());
        }
        return userService;
    }

    @Override
    public ArticleService getInstanceArticleService() {
        if (articleService == null) {
            articleService = new ArticleServiceImpl(factoryRepository.getInstanceArticleRepository());
        }
        return articleService;
    }

    @Override
    public DetteService getInstanceDetteService() {
        if (detteService == null) {
            detteService = new DetteServiceImpl(factoryRepository.getInstanceDetteRepository(), factoryRepository.getInstancePaymentRepository(), factoryRepository.getInstanceDetailRepository());
        }
        return detteService;
    }

    

    @Override
    public PaymentService getInstancePaymentService() {
         if (paymentService == null) {
            paymentService = new PaymentServiceImpl(factoryRepository.getInstancePaymentRepository());
        }
        return paymentService;
     }

    // @Override
    // public DetailService getInstanceDetailService() {
    //     if (detailService == null) {
    //         detailService = new DetailServiceImpl(factoryRepository.getInstanceDetailRepository());
    //     }
    //     return detailService;
    // }
}
