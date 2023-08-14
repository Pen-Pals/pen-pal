package com.example.penpal.domain.country.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "country_name")
    private String countryName;
    private double latitude;
    private double longitude;

    @Builder
    public Country(String countryName, double latitude, double longitude) {
        this.countryName = countryName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
