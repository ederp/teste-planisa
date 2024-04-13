package com.example.planisa.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.planisa.dto.DeathsDTO;

@Service
public interface DeathsService {

    List<DeathsDTO> totalDeaths(String country, LocalDate initialDate, LocalDate finalDate);

    List<DeathsDTO> deathsByDate(String country, LocalDate date);
}
