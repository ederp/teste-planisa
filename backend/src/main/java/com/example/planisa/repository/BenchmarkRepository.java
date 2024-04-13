package com.example.planisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.planisa.entity.BenchmarkEntity;

@Repository
public interface BenchmarkRepository extends JpaRepository<BenchmarkEntity, Long>{

}
