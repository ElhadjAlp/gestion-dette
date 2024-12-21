package com.ism.view;

import com.ism.data.entites.Dette;
import com.ism.services.ArticleService;
import com.ism.services.ClientService;

public interface IDetteView extends IView<Dette> {
    Dette saisir(ClientService clientService,ArticleService articleService); 
}

