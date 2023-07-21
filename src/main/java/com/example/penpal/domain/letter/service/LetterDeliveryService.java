package com.example.penpal.domain.letter.service;

import com.example.penpal.domain.letter.repository.LetterRepository;
import com.example.penpal.domain.member.repository.MemberRepository;
import com.example.penpal.global.exception.member.NotFoundMemberException;
import com.example.penpal.global.security.SecurityUtil;
import com.example.penpal.web.letter.model.DeliveryTimeDto;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.TravelMode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LetterDeliveryService {

    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;
    @Value("${google.map.key}")
    private String key;


    public DeliveryTimeDto calculateDeliveryTime(Long receiveId){
        Long sendId = SecurityUtil.getCurrentMemberId();
        String sendAddress = memberRepository.findAddressById(sendId).orElseThrow(NotFoundMemberException::new);
        String receiveAddress = memberRepository.findAddressById(receiveId).orElseThrow(NotFoundMemberException::new);

        GeoApiContext context = new GeoApiContext.Builder().apiKey(key).build();
        DistanceMatrixRow[] rows = DistanceMatrixApi.newRequest(context)
                .origins(sendAddress)
                .destinations(receiveAddress)
                .mode(TravelMode.TRANSIT)
                .awaitIgnoreError().rows;

        String deliveryTime = "";

        for (DistanceMatrixElement element : rows[0].elements) {
            deliveryTime = element.duration.humanReadable;
        }
        return DeliveryTimeDto.from(deliveryTime);
    }

}
