package com.messiasproject.financial.domain.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "rotulo")
@Data
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(scale = 2, nullable = false)
    private BigDecimal initialValue;

    @Column(scale = 2, nullable = false)
    private BigDecimal balance;

    @PrePersist
    private void generateUuid(){
        setUuid(UUID.randomUUID().toString());
    }
}
