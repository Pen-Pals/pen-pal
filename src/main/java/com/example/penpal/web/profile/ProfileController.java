package com.example.penpal.web.profile;


import com.example.penpal.domain.profile.service.ProfileService;
import com.example.penpal.global.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<ProfileResponseDto> addProfile(
            @RequestPart String fileType,
            @RequestPart List<MultipartFile> multipartFiles,
            @RequestPart String profileText
    ) {
        List<ProfileDto> profileDtoList = s3Service.uploadFile(fileType, multipartFiles);
        ProfileDto profileDto = profileDtoList.get(0);
        ProfileUpdateDto profileUpdateDto = new ProfileUpdateDto(profileDto.getUploadFileUrl(), profileText);
        ProfileResponseDto profileResponseDto = profileService.addProfile(profileUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(profileResponseDto);
    }


}
