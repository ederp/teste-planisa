package com.example.planisa.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.planisa.dto.CasesDTO;

@Service
public interface CasesService {

    List<CasesDTO> totalCases(String country, LocalDate initialDate, LocalDate finalDate);

    List<CasesDTO> casesByDate(String country, LocalDate date);
}
