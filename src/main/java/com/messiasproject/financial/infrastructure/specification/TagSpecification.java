package com.messiasproject.financial.infrastructure.specification;

import com.messiasproject.financial.domain.model.entity.TagEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class TagSpecification implements Specification<TagEntity> {
    private String nameTag;

    @Override
    public Predicate toPredicate(Root<TagEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), nameTag.toUpperCase() + "%");
    }
}
