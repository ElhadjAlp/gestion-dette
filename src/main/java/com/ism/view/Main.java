package com.ism.view;

import java.util.Scanner;

import com.ism.core.factory.Factory;
import com.ism.core.factory.impl.FactoryImpl;
import com.ism.view.Impl.Application;
import com.ism.view.Impl.View;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        View.setScanner(scanner);
        Factory factory = new FactoryImpl();
        Application app = new Application(factory, scanner);
        app.run();
    }
}
