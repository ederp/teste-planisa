package com.example.planisa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.planisa.entity.CasesEntity;

@Repository
public interface CasesRepository extends JpaRepository<CasesEntity, Long>{
@Query("select c from CasesEntity c where c.country.id = ?1 and c.date between ?2 and ?3")
    List<CasesEntity> casesByCountry(Long idCountry, LocalDate initialDate, LocalDate finalDate);
    
}
