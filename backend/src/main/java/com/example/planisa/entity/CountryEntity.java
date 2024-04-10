package com.example.planisa.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="countries")
@Data
@NoArgsConstructor
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String country;

    @Column
    private String region;

    @ManyToOne
    @JoinColumn(name = "case_id", nullable = false, referencedColumnName = "id")
    private List<CasesEntity> cases;

    @ManyToOne
    @JoinColumn(name = "death_id", nullable = false, referencedColumnName = "id")
    private List<DeathsEntity> deaths;

}
