package com.ism.view.Impl;

import java.util.List;
import java.util.Scanner;

import com.ism.view.IView;

public abstract class View<T> implements IView<T> {
    protected static Scanner scanner;

    public static void setScanner(Scanner scanner) {
        View.scanner = scanner;
    }

    @Override
    public void afficher(List<T> dataList) {
        dataList.forEach(System.out::println);
    }
    public void afficher(T data) {
        System.out.println(data);
    }
}
