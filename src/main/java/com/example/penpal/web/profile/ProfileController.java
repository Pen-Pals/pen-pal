package com.example.penpal.web.profile;


import com.example.penpal.domain.profile.service.ProfileService;
import com.example.penpal.global.exception.model.BusinessExceptionResponse;
import com.example.penpal.global.s3.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Upload Profile Image, Text", description = "프로필 사진, 메세지 변경")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "프로필 사진 업로드 성공"),
            @ApiResponse(responseCode = "404", description = "특정 유저가 존재하지 않음",
                    content = @Content(schema = @Schema(implementation = BusinessExceptionResponse.class)))
    })
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
