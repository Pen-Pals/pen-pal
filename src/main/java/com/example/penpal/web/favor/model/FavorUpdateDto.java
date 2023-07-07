package com.example.penpal.web.favor.model;

import com.example.penpal.domain.favor.entity.Favor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FavorUpdateDto {
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

    public Favor toEntity(boolean movie,
                          boolean language,
                          boolean book,
                          boolean coding,
                          boolean fantasy,
                          boolean sports,
                          boolean entertainment,
                          boolean music,
                          boolean fashion,
                          boolean art,
                          boolean travel) {
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
