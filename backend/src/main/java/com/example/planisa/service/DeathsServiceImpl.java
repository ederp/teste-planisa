package com.example.planisa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.planisa.dto.DeathsDTO;
import com.example.planisa.entity.DeathsEntity;
import com.example.planisa.repository.CountryRepository;
import com.example.planisa.repository.DeathsRepository;

@Service
public class DeathsServiceImpl implements DeathsService{

    @Autowired
    private DeathsRepository deathsRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ApiCovidCallService apiCovidCallService;

    @Override
    public List<DeathsDTO> totalDeaths(String country, LocalDate initialDate, LocalDate finalDate) {
        // TODO Auto-generated method stub
        deathsRepository.saveAll(this.apiCovidCallService.callApiCovidByCountry(country, "deaths"));
        Long idCountry = this.countryRepository.findIdByName(country);
        List<DeathsDTO> deathsResult = 
            this.deathsEntityToDTO(deathsRepository.deathsByCountry(idCountry, initialDate, finalDate), country);
            return deathsResult;
    }

    @Override
    public List<DeathsDTO> deathsByDate(String country, LocalDate date) {
        // TODO Auto-generated method stub
        deathsRepository.saveAll(this.apiCovidCallService.callApiCovidByDate(date.toString(), "deaths"));
        Long idCountry = this.countryRepository.findIdByName(country);
        List<DeathsDTO> deathsResult = 
            this.deathsEntityToDTO(deathsRepository.deathsByCountry(idCountry, date, date), country);
            return deathsResult;
    }

    private List<DeathsDTO> deathsEntityToDTO(List<DeathsEntity> casesByCountry, String nameCountry) {
        // TODO Auto-generated method stub
        List<DeathsDTO> listDeathsDTO = new ArrayList<>();

        casesByCountry.stream().forEach(c -> {
            DeathsDTO deathsDTO = new DeathsDTO();
            deathsDTO.setDate(c.getDate());
            deathsDTO.setTotalDeaths(c.getTotal());
            deathsDTO.setNewDeaths(c.getNewOccurrences());
            deathsDTO.setCountry(nameCountry);

            listDeathsDTO.add(deathsDTO);
        });

        return listDeathsDTO;
    }
}
