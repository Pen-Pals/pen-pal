package com.example.penpal.web.letter.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryTimeDtoTest {

    @Test
    void test(){
        DeliveryTimeDto from = DeliveryTimeDto.from(1);
        System.out.println("from.getDays() = " + from.getDays());
        System.out.println("from.getHours() = " + from.getHours());
        System.out.println("from.getMins() = " + from.getMins());
    }

}