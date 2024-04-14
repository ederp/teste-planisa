package com.example.planisa.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.planisa.dto.CasesDTO;
import com.example.planisa.dto.DeathsDTO;
import com.example.planisa.entity.BenchmarkEntity;
import com.example.planisa.service.BenchmarkService;
import com.example.planisa.service.CasesService;
import com.example.planisa.service.DeathsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/benchmark")
public class BenchmarkController {

    @Autowired
    private BenchmarkService benchmarkService;

    @Autowired
    private CasesService casesService;

    @Autowired
    private DeathsService deathsService;

    @Operation(summary = "Comparar o total de casos entre dois países num determinado intervalo de tempo", description = "", tags={  })
    @ApiResponse(responseCode = "200", description = "Lista dos casos dos países")
    @GetMapping("cases/dates/{country_1}/{country_2}")
    public ResponseEntity<List<CasesDTO>> casesBetweenDates(@PathVariable("country_1") String country1,
        @PathVariable("country_2") String country2, @RequestParam LocalDate from, 
        @RequestParam LocalDate to){
            BenchmarkEntity benchmarkEntity = new BenchmarkEntity();
            benchmarkEntity.setName("cases/"+country1+"/"+country2+"?from="+from+"&to="+to);
            this.benchmarkService.newPostBenchmark(benchmarkEntity);

            List<CasesDTO> listsCasesDTOs = new ArrayList<>();
            listsCasesDTOs.addAll(this.casesService.totalCases(country1, from, to));
            listsCasesDTOs.addAll(this.casesService.totalCases(country2, from, to));

            return ResponseEntity.status(HttpStatus.OK).body(listsCasesDTOs);
    }

    @Operation(summary = "Comparar o total de casos entre dois países num determinado dia", description = "", tags={  })
    @ApiResponse(responseCode = "200", description = "Lista dos casos dos países")
    @GetMapping("cases/date/{country_1}/{country_2}")
    public ResponseEntity<List<CasesDTO>> casesByOneDate(@PathVariable("country_1") String country1,
    @PathVariable("country_2") String country2, @RequestParam LocalDate from){
        BenchmarkEntity benchmarkEntity = new BenchmarkEntity();
        benchmarkEntity.setName("cases/"+country1+"/"+country2+"?from="+from);
        this.benchmarkService.newPostBenchmark(benchmarkEntity);

        List<CasesDTO> listsCasesDTOs = new ArrayList<>();
        listsCasesDTOs.addAll(this.casesService.casesByDate(country1, from));
        listsCasesDTOs.addAll(this.casesService.casesByDate(country2, from));

        return ResponseEntity.status(HttpStatus.OK).body(listsCasesDTOs);
    }

    @Operation(summary = "Comparar o total de mortes entre dois países num determinado intervalo de tempo", description = "", tags={  })
    @ApiResponse(responseCode = "200", description = "Lista das mortes dos países")
    @GetMapping("deaths/dates/{country_1}/{country_2}")
    public ResponseEntity<List<DeathsDTO>> deathsBetweenDates(@PathVariable("country_1") String country1,
        @PathVariable("country_2") String country2, @RequestParam LocalDate from, 
        @RequestParam LocalDate to){
            BenchmarkEntity benchmarkEntity = new BenchmarkEntity();
            benchmarkEntity.setName("deaths/"+country1+"/"+country2+"?from="+from+"&to="+to);
            this.benchmarkService.newPostBenchmark(benchmarkEntity);

            List<DeathsDTO> listsDeathsDTOs = new ArrayList<>();
            listsDeathsDTOs.addAll(this.deathsService.totalDeaths(country1, from, to));
            listsDeathsDTOs.addAll(this.deathsService.totalDeaths(country2, from, to));

            return ResponseEntity.status(HttpStatus.OK).body(listsDeathsDTOs);
    }

    @Operation(summary = "Comparar o total de mortes entre dois países num determinado dia", description = "", tags={  })
    @ApiResponse(responseCode = "200", description = "Lista das mortes dos países")
    @GetMapping("deaths/date/{country_1}/{country_2}")
    public ResponseEntity<List<DeathsDTO>> deathsByOneDate(@PathVariable("country_1") String country1,
    @PathVariable("country_2") String country2, @RequestParam LocalDate from){
        BenchmarkEntity benchmarkEntity = new BenchmarkEntity();
        benchmarkEntity.setName("deaths/"+country1+"/"+country2+"?from="+from);
        this.benchmarkService.newPostBenchmark(benchmarkEntity);

        List<DeathsDTO> listsDeathsDTOs = new ArrayList<>();
        listsDeathsDTOs.addAll(this.deathsService.deathsByDate(country1, from));
        listsDeathsDTOs.addAll(this.deathsService.deathsByDate(country2, from));

        return ResponseEntity.status(HttpStatus.OK).body(listsDeathsDTOs);
    }
}
