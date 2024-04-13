package com.example.planisa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.planisa.entity.BenchmarkEntity;
import com.example.planisa.repository.BenchmarkRepository;

@Service
public class BenchmarkServiceImpl implements BenchmarkService{

    @Autowired
    private BenchmarkRepository benchmarkRepository;

    @Override
    public void newPostBenchmark(BenchmarkEntity benchmarkEntity) {
        // TODO Auto-generated method stub
        this.benchmarkRepository.save(benchmarkEntity);
    }

    /*
    private BenchmarkEntity benchmarkDtoTOEntity(BenchmarkDTO benchmarkDTO) {
        // TODO Auto-generated method stub
        BenchmarkEntity benchmarkEntity = new BenchmarkEntity();

        benchmarkEntity.setName(benchmarkDTO.getName());

        return benchmarkEntity;
    } */

}
