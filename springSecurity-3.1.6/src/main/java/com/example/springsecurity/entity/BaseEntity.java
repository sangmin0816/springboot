package com.example.springsecurity.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Data
public abstract class BaseEntity {

    @CreatedDate
    @Column(name="createAt", updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name="updateAt")
    private LocalDateTime updateAt;
}