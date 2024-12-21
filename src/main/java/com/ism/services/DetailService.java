package com.ism.services;

import com.ism.data.entites.Detail;

import java.util.List;

public interface DetailService {
    Detail getDetailById(int id);             
    List<Detail> getAllDetails();               
    void addDetail(Detail detail);              
    void removeDetail(int id);                  
}
 
