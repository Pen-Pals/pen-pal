package com.example.penpal.web.search;

import com.example.penpal.domain.search.SearchService;
import com.example.penpal.web.search.model.SearchMemberListDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "닉네임 검색", description = "입력한 검색어로 시작하는 닉네임을 가진 유저의 정보를 닉네임의 길이가 짧은 순서대로 최대 10개 반환")
    @ApiResponse(responseCode = "200", description = "검색 성공")
    @GetMapping("/{query}")
    public ResponseEntity<SearchMemberListDto> search(@PathVariable String query){
        SearchMemberListDto searchMemberListDto = searchService.searchMembers(query);
        return ResponseEntity.ok(searchMemberListDto);
    }
}
