package com.example.penpal.web.letter.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeliveryTimeDto {

    private int days;
    private int hours;

    private int mins;

    public static DeliveryTimeDto from(double distance) {
        double deliveryTime = Math.round(distance) / 100.00; // 시속 100KM
        int days = (int) deliveryTime / 24;
        deliveryTime -= days * 24;
        int hours = (int) deliveryTime;
        deliveryTime -= hours;
        int mins = (int) (Math.ceil(deliveryTime * 60));
        return new DeliveryTimeDto(days, hours, mins);
    }
}
