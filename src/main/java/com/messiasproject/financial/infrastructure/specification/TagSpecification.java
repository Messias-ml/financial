package com.messiasproject.financial.infrastructure.specification;

import com.messiasproject.financial.api.model.specification.TagFilterSpec;
import com.messiasproject.financial.api.model.tag.StatusTag;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TagSpecification implements Specification<TagEntity> {
    private TagFilterSpec tagFilterSpec;

    @Override
    public Predicate toPredicate(Root<TagEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (tagFilterSpec.getName() != null){
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), tagFilterSpec.getName().toUpperCase() + "%"));
        }
        if (null != tagFilterSpec.getStatus()){
        predicates.add(criteriaBuilder.equal(root.get("status"), tagFilterSpec.getStatus()));
        }
        Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
        return criteriaBuilder.and(predicatesArray);
    }
}
