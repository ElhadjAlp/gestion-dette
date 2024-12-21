package com.ism.view;

import java.util.List;

public interface IView<T> {
    void afficher(List<T> list);
}
