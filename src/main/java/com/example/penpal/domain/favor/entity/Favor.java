package com.example.penpal.domain.favor.entity;

import com.example.penpal.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Favor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favor_id")
    private Long id;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Favor(boolean movie,
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
        this.movie = false;
        this.language = false;
        this.book = false;
        this.coding = false;
        this.fantasy = false;
        this.sports = false;
        this.entertainment = false;
        this.music = false;
        this.fashion = false;
        this.art = false;
        this.travel = false;
    }


}
