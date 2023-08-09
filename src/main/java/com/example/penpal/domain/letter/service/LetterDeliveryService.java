package com.example.penpal.domain.letter.service;

import com.example.penpal.domain.letter.repository.LetterRepository;
import com.example.penpal.domain.member.repository.MemberRepository;
import com.example.penpal.global.exception.member.NotExistLocationInfoException;
import com.example.penpal.global.security.SecurityUtil;
import com.example.penpal.web.letter.model.DeliveryTimeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LetterDeliveryService {

    private final MemberRepository memberRepository;

    public DeliveryTimeDto calculateDeliveryTime(Long receiveId) {
        Long sendId = SecurityUtil.getCurrentMemberId();

        double send_latitude = memberRepository.findLatitudeById(sendId).orElseThrow(NotExistLocationInfoException::new);
        double send_longitude = memberRepository.findLongitudeById(sendId).orElseThrow(NotExistLocationInfoException::new);

        double receive_latitude = memberRepository.findLatitudeById(receiveId).orElseThrow(NotExistLocationInfoException::new);
        double receive_longitude = memberRepository.findLongitudeById(receiveId).orElseThrow(NotExistLocationInfoException::new);

        double distance = haversine(send_latitude, send_longitude, receive_latitude, receive_longitude);

        return DeliveryTimeDto.from(distance);
    }

    private double haversine(double send_lat, double send_lon, double receive_lat, double receive_lon) {
        double radius = 6371;
        double toRadian = Math.PI / 180;

        double deltaLatitude = Math.abs(send_lat - receive_lat) * toRadian;
        double deltaLongitude = Math.abs(send_lon - receive_lon) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(
                sinDeltaLat * sinDeltaLat +
                        Math.cos(send_lat * toRadian) * Math.cos(receive_lat * toRadian) * sinDeltaLng * sinDeltaLng);

        double distance = 2 * radius * Math.asin(squareRoot);

        return distance;
    }

}