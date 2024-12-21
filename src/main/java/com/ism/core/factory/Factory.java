package com.ism.core.factory;

public interface Factory {
    FactoryRepository getFactoryRepository();
    FactoryService getFactoryService();
    FactoryView getFactoryView();
}