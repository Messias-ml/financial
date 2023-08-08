package com.messiasproject.financial.domain.repository;

import com.messiasproject.financial.domain.model.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long>, JpaSpecificationExecutor<TagEntity> {
    Optional<TagEntity> findTagByUuid(String uuidTag);
}
