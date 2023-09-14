package com.example.penpal.web.country;

import com.example.penpal.domain.country.entity.Country;
import com.example.penpal.domain.favor.entity.Favor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CountryRequestDto {
    private String countryName;

    public Country toDto() {
        return Country.builder()
                .countryName(countryName)
                .build();
    }
}

