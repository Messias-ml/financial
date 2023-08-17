package com.messiasproject.financial.domain.repository;

import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>, JpaSpecificationExecutor<TransactionEntity> {
    @Transactional
    @Modifying
    @Query("delete from TransactionEntity t where t.uuid = :uuid")
    void deleteByUuid(String uuid);
    @Query("SELECT tr FROM TransactionEntity tr where tr.tag = :tag")
    List<TransactionEntity> findAllByTag(TagEntity tag);

    Optional<TransactionEntity> findTransactionByUuid(String uuid);


}
