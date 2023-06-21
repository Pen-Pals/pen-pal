package com.example.penpal.domain.letter.repository;

import com.example.penpal.domain.letter.entity.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    @Query("select l from Letter l where l.sendId = :sendId and  l.receiveId = :receiveId")
    Page<Letter> findLettersByReceiveId(Long sendId, Long receiveId, Pageable pageable);
}
