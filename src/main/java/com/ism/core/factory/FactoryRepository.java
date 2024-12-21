package com.ism.core.factory;

import com.ism.data.repository.ArticleRepository;
import com.ism.data.repository.ClientRepository;
import com.ism.data.repository.DetteRepository;
import com.ism.data.repository.PaymentRepository;
import com.ism.data.repository.UserRepository;
import com.ism.data.repository.DetailRepository; 

public interface FactoryRepository {
    ClientRepository getInstanceClientRepository();
    UserRepository getInstanceUserRepository();
    ArticleRepository getInstanceArticleRepository();
    DetteRepository getInstanceDetteRepository();
    PaymentRepository getInstancePaymentRepository();
    DetailRepository getInstanceDetailRepository();
}


