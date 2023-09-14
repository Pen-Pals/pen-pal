package com.example.penpal.web.favor.model;

import com.example.penpal.domain.favor.entity.Favor;
import com.example.penpal.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FavorRequestDto {
    private boolean movie;
    private boolean language;
    private boolean book;
    private boolean coding;
    private boolean fantasy;
    private boolean sports;
    private boolean entertainment;
    private boolean music;
    private boolean fashion;
    private boolean art;
    private boolean travel;

    public Favor toDto() {
        return Favor.builder()
                .movie(movie)
                .language(language)
                .book(book)
                .coding(coding)
                .fantasy(fantasy)
                .sports(sports)
                .entertainment(entertainment)
                .music(music)
                .fashion(fashion)
                .art(art)
                .travel(travel)
                .build();
    }
}
