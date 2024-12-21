package com.ism.core.factory;

import com.ism.services.ArticleService;
import com.ism.services.ClientService;
import com.ism.services.DetteService;
import com.ism.services.PaymentService;
import com.ism.services.UserService;




public interface FactoryService {
    ClientService getInstanceClientService();
    UserService getInstanceUserService();
    ArticleService getInstanceArticleService();
    DetteService getInstanceDetteService();
    PaymentService getInstancePaymentService();
}



