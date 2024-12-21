package com.ism.core.factory.impl;

import com.ism.core.factory.Factory;
import com.ism.core.factory.FactoryRepository;
import com.ism.core.factory.FactoryService;
import com.ism.core.factory.FactoryView;

public class FactoryImpl implements Factory {
    @Override
    public FactoryRepository getFactoryRepository() {
        return new FactoryRepositoryImpl();
    }

    @Override
    public FactoryService getFactoryService() {
        return new FactoryServiceImpl(getFactoryRepository());
    }

    @Override
    public FactoryView getFactoryView() {
       return new FactoryViewImpl(getFactoryService());
    }

    
}
