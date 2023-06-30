package com.example.penpal.web.letter.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CorrespondentListDto {

    private List<CorrespondentDto>  correspondents;

    public static CorrespondentListDto from(List<CorrespondentDto> correspondents){
        CorrespondentListDto correspondentListDto = new CorrespondentListDto();
        correspondentListDto.correspondents = correspondents;
        return correspondentListDto;
    }
}
