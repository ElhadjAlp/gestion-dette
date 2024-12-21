package com.ism.services.Impl;

import com.ism.data.entites.Detail;
import com.ism.data.repository.DetailRepository;
import com.ism.services.DetailService;

import java.util.List;

public class DetailServiceImpl implements DetailService {
    private final DetailRepository detailRepository;

    public DetailServiceImpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public Detail getDetailById(int id) {
        return detailRepository.selectById(id);
    }

    @Override
    public List<Detail> getAllDetails() {
        return detailRepository.selectAll();
    }

    @Override
    public void addDetail(Detail detail) {
        detailRepository.insert(detail);
    }

    @Override
    public void removeDetail(int id) {
        detailRepository.delete(id);
    }
}

