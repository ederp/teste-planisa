package com.example.planisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.planisa.entity.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long>{

    @Query("select c.id from CountryEntity c where lower(c.country) =lower(?1)")
    Long findIdByName(String nameCountry);

    @Query("select c from CountryEntity c where lower(country) =lower(?1)")
    CountryEntity findByName(String nameCountry);
}
