package com.ism.data.repository.List;

import com.ism.data.entites.Detail;
import com.ism.data.repository.DetailRepository;

import java.util.ArrayList;
import java.util.List;


public class DetailRepositoryList implements DetailRepository {
    private final List<Detail> details = new ArrayList<>();
    private static int currentId = 1;
    @Override
    public Detail selectById(int id) {
        return details.stream()
                .filter(detail -> detail.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Detail> selectAll() {
        return new ArrayList<>(details); 
    }

    @Override
    public void insert(Detail detail) {
        if (detail.getId() == 0) {
            detail.setId(currentId++); 
        }
        details.add(detail); 
    }

    @Override
    public void delete(int id) {
        details.removeIf(detail -> detail.getId() == id); 
    }

}

