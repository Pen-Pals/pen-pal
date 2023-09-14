package com.example.penpal.domain.favor.repository;

import com.example.penpal.domain.favor.entity.Favor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FavorRepository extends JpaRepository<Favor, Long> {
}
