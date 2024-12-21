package com.ism.core.factory.impl;

import com.ism.core.factory.FactoryRepository;
import com.ism.data.repository.ArticleRepository;
import com.ism.data.repository.ClientRepository;
import com.ism.data.repository.DetailRepository;
import com.ism.data.repository.DetteRepository;
import com.ism.data.repository.PaymentRepository;
import com.ism.data.repository.UserRepository;
import com.ism.data.repository.jpa.impl.ArticleRepositoryJPA;
import com.ism.data.repository.jpa.impl.ClientRepositoryJPA;
import com.ism.data.repository.jpa.impl.DetailRepositoryJPA;
import com.ism.data.repository.jpa.impl.DetteRepositoryJPA;
import com.ism.data.repository.jpa.impl.PaymentRepositoryJPA;
import com.ism.data.repository.jpa.impl.UserRepositoryJPA;

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
            clientRepository = new ClientRepositoryJPA();
        }
        return clientRepository;
    }

    @Override
    public UserRepository getInstanceUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryJPA();
        }
        return userRepository;
    }

    @Override
    public ArticleRepository getInstanceArticleRepository() {
        if (articleRepository == null) {
            articleRepository = new ArticleRepositoryJPA();
        }
        return articleRepository;
    }

    @Override
    public DetteRepository getInstanceDetteRepository() {
        if (detteRepository == null) {
            detteRepository = new DetteRepositoryJPA();
        }
        return detteRepository;
    }

    @Override
    public PaymentRepository getInstancePaymentRepository() {
        if (paymentRepository == null) {
            paymentRepository = new PaymentRepositoryJPA();
        }
        return paymentRepository;
    }

    @Override
    public DetailRepository getInstanceDetailRepository() {
        if (detailRepository == null) {
            detailRepository = new DetailRepositoryJPA(); 
        }
        return detailRepository;
    }
}


