package com.example.penpal.web.letter.model;

import com.example.penpal.domain.letter.entity.Letter;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class SendLetterResponse {

    private Long id;
    private Member member;
    private Long sendId;
    private Long receiveId;
    private String sendLocation;
    private String receiveLocation;
    private LocalDateTime sendDate;
    private LocalDateTime receiveDate;
    private LocalDateTime deliveryTime;
    private String content;
    private boolean isArrived;
    private boolean isRead;


    public static SendLetterResponse from(Letter letter){
        return SendLetterResponse.builder()
                .id(letter.getId())
                .member(letter.getMember())
                .sendId(letter.getSendId())
                .receiveId(letter.getReceiveId())
                .sendLocation(letter.getSendLocation())
                .receiveLocation(letter.getReceiveLocation())
                .sendDate(letter.getSendDate())
                .receiveDate(letter.getReceiveDate())
                .deliveryTime(letter.getDeliveryTime())
                .content(letter.getContent())
                .isArrived(false)
                .isRead(false)
                .build();
    }
}
