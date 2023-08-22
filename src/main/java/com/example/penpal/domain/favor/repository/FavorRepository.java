package com.example.penpal.domain.favor.repository;

import com.example.penpal.domain.favor.entity.Favor;
import com.example.penpal.web.letter.model.UnreadCountInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FavorRepository extends JpaRepository<Favor, Long> {

}
