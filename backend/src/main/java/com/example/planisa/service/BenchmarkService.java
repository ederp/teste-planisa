package com.example.planisa.service;

import org.springframework.stereotype.Service;

import com.example.planisa.entity.BenchmarkEntity;

@Service
public interface BenchmarkService {

    void newPostBenchmark(BenchmarkEntity benchmarkEntity);

}
