package com.example.penpal.web.member.model;

import com.example.penpal.web.country.CountryRequestDto;
import com.example.penpal.web.favor.model.FavorRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SignUpDto {
    private MemberRequestDto member;
    private FavorRequestDto favor;
    private CountryRequestDto country;
}
