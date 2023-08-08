package com.messiasproject.financial.domain.model.entity;

import com.messiasproject.financial.domain.model.TypeTransaction;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "Transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;

    @ManyToOne
    @JoinColumn(name = "id_tag", nullable = false)
    private TagEntity tag;

    @Column(scale = 2, nullable = false)
    private BigDecimal currentValueTag;

    @Column(nullable = false, scale = 2)
    private BigDecimal valueTransaction;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dateTransaction;

    @PrePersist
    private void generateUuid(){
         setUuid(UUID.randomUUID().toString());
    }
}
