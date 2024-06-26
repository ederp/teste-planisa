package com.example.planisa.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class DeathsDTO {

    private LocalDate date;
    private String totalDeaths;
    private String newDeaths;
    private String country;
}
