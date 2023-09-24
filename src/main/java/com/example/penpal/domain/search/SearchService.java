package com.example.penpal.domain.search;

import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.domain.member.repository.MemberRepository;
import com.example.penpal.web.search.model.SearchMemberDto;
import com.example.penpal.web.search.model.SearchMemberListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchService {

    private final MemberRepository memberRepository;

    public SearchMemberListDto searchMembers(String query){
        PageRequest pageRequest = PageRequest.of(0,10);
        List<SearchMemberDto> SearchMemberList = memberRepository.findMembersWithProfile(query)
                .stream()
                .map(m -> SearchMemberDto.from(m))
                .collect(Collectors.toList());
        return SearchMemberListDto.from(SearchMemberList);
    }
}
