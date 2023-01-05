package com.alex.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name="ksuid", strategy = "com.alex.utils.KsuidIdentiferGenerator")
    private String id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
