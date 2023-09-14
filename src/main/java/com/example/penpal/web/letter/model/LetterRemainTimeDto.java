package com.example.penpal.web.letter.model;

import com.example.penpal.domain.letter.entity.Letter;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LetterRemainTimeDto {

    private String nickname;
    private Long sendId;
    private Long receiveId;
    private LocalDateTime sendDate;
    private String content;
    private Long remainDays;
    private Long remainHours;
    private Long remainMinutes;

    private static long calculateRemainingTime(Letter letter, String time){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deliveryTime = letter.getDeliveryTime();
        if(time.equals("MINUTES")) {
            return ChronoUnit.MINUTES.between(now, deliveryTime) % 60;
        }
        if(time.equals("HOURS")){
            return ChronoUnit.HOURS.between(now, deliveryTime) % 24;
        }
        if(time.equals("DAYS")){
            return ChronoUnit.DAYS.between(now, deliveryTime);
        }
        return 0L;
    }

    public static LetterRemainTimeDto from(Letter letter) {
        return LetterRemainTimeDto.builder()
                .nickname(letter.getMember().getNickname())
                .sendId(letter.getSendId())
                .receiveId(letter.getReceiveId())
                .sendDate(letter.getSendDate())
                .content(letter.getContent())
                .remainDays(calculateRemainingTime(letter, "DAYS"))
                .remainHours(calculateRemainingTime(letter,"HOURS"))
                .remainMinutes(calculateRemainingTime(letter,"MINUTES"))
                .build();
    }

}
