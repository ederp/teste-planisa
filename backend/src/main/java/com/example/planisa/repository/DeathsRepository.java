package com.example.planisa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.planisa.entity.DeathsEntity;

@Repository
public interface DeathsRepository extends JpaRepository<DeathsEntity, Long>{

    @Query("select d from DeathsEntity d where d.country.id  = ?1 and d.date between ?2 and ?3")
    List<DeathsEntity> deathsByCountry(Long idCountry, LocalDate initialDate, LocalDate finalDate);
}
