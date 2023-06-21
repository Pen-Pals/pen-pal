package com.example.penpal.domain.letter.repository;

import com.example.penpal.domain.letter.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {
}
