package com.example.penpal.domain.letter.entity;

import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.web.letter.model.DeliveryTimeDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(nullable = false)
    private Long sendId;
    @Column(nullable = false)
    private Long receiveId;
    private String sendLocation;
    private String receiveLocation;
    private LocalDateTime sendDate;
    private LocalDateTime receiveDate;
    private LocalDateTime deliveryTime;
    @Lob
    @Column(nullable = false)
    private String content;
    private boolean isArrived;
    private boolean isRead;
    private boolean deletedBySender;
    private boolean deletedByReceiver;

    @Builder
    public Letter(Member member, Long sendId, Long receiveId, String sendLocation, String receiveLocation,
                  LocalDateTime sendDate, LocalDateTime receiveDate, LocalDateTime deliveryTime, String content) {
        this.member = member;
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.sendLocation = sendLocation;
        this.receiveLocation = receiveLocation;
        this.sendDate = sendDate;
        this.receiveDate = receiveDate;
        this.deliveryTime = deliveryTime;
        this.content = content;
        this.isArrived = false;
        this.isRead = false;
        this.deletedBySender = false;
        this.deletedByReceiver = false;
    }

    public void addDeliveryTime(DeliveryTimeDto deliveryTimeDto) {
        this.deliveryTime = this.deliveryTime.plusDays(deliveryTimeDto.getDays())
                .plusHours(deliveryTimeDto.getHours())
                .plusMinutes(deliveryTimeDto.getMins());
    }

}
