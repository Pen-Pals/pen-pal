package com.example.penpal.web.search.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchMemberListDto {

    private List<SearchMemberDto> searchMembers;

    public static SearchMemberListDto from(List<SearchMemberDto> searchMembers){
        SearchMemberListDto searchMemberListDto = new SearchMemberListDto();
        searchMemberListDto.searchMembers = searchMembers;
        return searchMemberListDto;
    }

}
