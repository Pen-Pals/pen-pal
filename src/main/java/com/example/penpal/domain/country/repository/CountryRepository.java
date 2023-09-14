package com.example.penpal.domain.country.repository;

import com.example.penpal.domain.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, String> {
    Optional<Country> findByCountryName(String countryName);
}
