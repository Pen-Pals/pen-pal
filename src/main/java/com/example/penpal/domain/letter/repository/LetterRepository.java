package com.example.penpal.domain.letter.repository;

import com.example.penpal.domain.letter.entity.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    Page<Letter> findBySendIdAndReceiveId(Long sendId, Long receiveId, Pageable pageable);


}
