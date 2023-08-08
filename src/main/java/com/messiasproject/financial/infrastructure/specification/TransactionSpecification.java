package com.messiasproject.financial.infrastructure.specification;

import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class TransactionSpecification implements Specification<TransactionEntity> {
    private TagEntity tagEntity;

    @Override
    public Predicate toPredicate(Root<TransactionEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        /*Subquery<TagEntity> tagEntitySubquery = query.subquery(TagEntity.class);
        Root<TagEntity> rootTag = tagEntitySubquery.from(TagEntity.class);*/
        return criteriaBuilder.equal(root.get("tag"), tagEntity);
    }
}
