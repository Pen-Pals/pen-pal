package com.example.penpal.web.letter.model;

import com.example.penpal.global.exception.letter.WrongTimeFormatException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeliveryTimeDto {

    private long days;
    private long hours;
    private long mins;

    public static DeliveryTimeDto from(String time){
        String[] splitTime = time.split(" ");

        if(splitTime[1].equals("day") || splitTime[1].equals("days")){
            return new DeliveryTimeDto(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[2]), 0);
        }
        else if(splitTime[1].equals("hour") || splitTime[1].equals("hours")){
            return new DeliveryTimeDto(0,Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[2]));
        }
        else if(splitTime[1].equals("min") || splitTime[1].equals("mins")){
            return new DeliveryTimeDto(0,0,Integer.parseInt(splitTime[0]));
        }
        else{
            throw new WrongTimeFormatException();
        }
    }
}
