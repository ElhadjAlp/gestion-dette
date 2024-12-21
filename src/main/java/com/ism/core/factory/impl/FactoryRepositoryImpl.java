package com.ism.core.factory.impl;

import com.ism.core.factory.FactoryRepository;
import com.ism.data.repository.ArticleRepository;
import com.ism.data.repository.ClientRepository;
import com.ism.data.repository.DetailRepository;
import com.ism.data.repository.DetteRepository;
import com.ism.data.repository.PaymentRepository;
import com.ism.data.repository.UserRepository;
import com.ism.data.repository.List.ClientRepositoryList;
import com.ism.data.repository.List.DetteRepositoryList;
import com.ism.data.repository.List.UserRepositoryList;
import com.ism.data.repository.bd.ArticleRepositoryBD;
import com.ism.data.repository.bd.DetailRepositoryBD;
import com.ism.data.repository.bd.DetteRepositoryBD;
import com.ism.data.repository.bd.PaymentRepositoryBD;
import com.ism.data.repository.List.ArticleRepositoryList;
import com.ism.data.repository.List.PaymentRepositoryList;
import com.ism.data.repository.List.DetailRepositoryList;

public class FactoryRepositoryImpl implements FactoryRepository {
    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;
    private DetteRepository detteRepository;
    private PaymentRepository paymentRepository;
    private DetailRepository detailRepository;

    @Override
    public ClientRepository getInstanceClientRepository() {
        if (clientRepository == null) {
            clientRepository = new ClientRepositoryList();
        }
        return clientRepository;
    }

    @Override
    public UserRepository getInstanceUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryList();
        }
        return userRepository;
    }

    @Override
    public ArticleRepository getInstanceArticleRepository() {
        if (articleRepository == null) {
            articleRepository = new ArticleRepositoryList();
        }
        return articleRepository;
    }

    @Override
    public DetteRepository getInstanceDetteRepository() {
        if (detteRepository == null) {
            detteRepository = new DetteRepositoryList();
        }
        return detteRepository;
    }

    @Override
    public PaymentRepository getInstancePaymentRepository() {
        if (paymentRepository == null) {
            paymentRepository = new PaymentRepositoryList();
        }
        return paymentRepository;
    }

    @Override
    public DetailRepository getInstanceDetailRepository() {
        if (detailRepository == null) {
            detailRepository = new DetailRepositoryList(); 
        }
        return detailRepository;
    }
}


