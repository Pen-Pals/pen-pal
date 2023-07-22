package com.example.penpal.domain.profile.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.domain.member.repository.MemberRepository;
import com.example.penpal.domain.profile.entity.Profile;
import com.example.penpal.domain.profile.repository.ProfileRepository;
import com.example.penpal.global.security.SecurityUtil;
import com.example.penpal.web.profile.ProfileDto;
import com.example.penpal.web.profile.ProfileResponseDto;
import com.example.penpal.web.profile.ProfileUpdateDto;
import jakarta.validation.NoProviderFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;

    // 프로필 추가
    public ProfileResponseDto addProfile(ProfileUpdateDto profileUpdateDto) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(NoProviderFoundException::new);
        Profile profile = profileUpdateDto.toEntity(profileUpdateDto.getProfileUrl(), profileUpdateDto.getProfileText());
        profileRepository.save(profile);
        member.updateProfile(profile);

        return ProfileResponseDto.toDto(profile);
    }


}
