package com.example.springsecurity.repository;

import com.example.springsecurity.entity.BoardGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<BoardGroup, Integer> {
}
