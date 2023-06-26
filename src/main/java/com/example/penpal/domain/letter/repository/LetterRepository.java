package com.example.penpal.domain.letter.repository;

import com.example.penpal.domain.letter.entity.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    //기능 수정 필요
    Page<Letter> findBySendIdAndReceiveId(Long sendId, Long receiveId, Pageable pageable);

    @Query("select l from Letter l join fetch Member m where l.receiveId = :userId and l.isArrived = true")
    List<Letter> findByIsArrived(@Param("userId") Long userId);

}
