package com.messiasproject.financial.domain.repository;

import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>, JpaSpecificationExecutor<TransactionEntity> {
    @Query("SELECT tr FROM TransactionEntity tr where tr.tag = :tag")
    List<TransactionEntity> FindAllByTag(TagEntity tag);
}
