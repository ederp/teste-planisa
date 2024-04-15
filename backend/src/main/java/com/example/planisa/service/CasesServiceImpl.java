package com.example.planisa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.planisa.dto.CasesDTO;
import com.example.planisa.entity.CasesEntity;
import com.example.planisa.repository.CasesRepository;
import com.example.planisa.repository.CountryRepository;

@Service
public class CasesServiceImpl implements CasesService{

    @Autowired
    private CasesRepository casesRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ApiCovidCallService apiCovidCallService;

    @Override
    public List<CasesDTO> totalCases(String country, LocalDate initialDate, LocalDate finalDate) {
        // TODO Auto-generated method stub
        casesRepository.saveAll(this.apiCovidCallService.callApiCovidByCountry(country, "cases"));
        Long idCountry = this.countryRepository.findIdByName(country);
        List<CasesDTO> casesResult = 
            this.casesEntityToDTO(casesRepository.casesByCountry(idCountry, initialDate, finalDate), country);
            return casesResult;
    }

    @Override
    public List<CasesDTO> casesByDate(String country, LocalDate date) {
        // TODO Auto-generated method stub
        casesRepository.saveAll(this.apiCovidCallService.callApiCovidByDate(date.toString(), "cases"));
        Long idCountry = this.countryRepository.findIdByName(country);
        List<CasesDTO> casesResult = 
            this.casesEntityToDTO(casesRepository.casesByCountry(idCountry, date, date), country);
            return casesResult;
    }

    private List<CasesDTO> casesEntityToDTO(List<CasesEntity> casesByCountry, String nameCountry) {
        // TODO Auto-generated method stub
        List<CasesDTO> listCasesDTO = new ArrayList<>();

        casesByCountry.stream().forEach(c -> {
            CasesDTO casesDTO = new CasesDTO();
            casesDTO.setDate(c.getDate());
            casesDTO.setTotalCases(c.getTotal());
            casesDTO.setNewCases(c.getNewOccurrences());
            casesDTO.setCountry(nameCountry);

            listCasesDTO.add(casesDTO);
        });

        return listCasesDTO;
    }

}
