package com.example.springsecurity.repository;

import com.example.springsecurity.entity.NickNames;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NickNamesRepository extends JpaRepository<NickNames, String> {
}
