package com.messiasproject.financial.domain.service.implementation.tags.microservices.delete;

import com.messiasproject.financial.api.model.tag.StatusTag;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.delete.TagDeletion;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchTagByUuid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TagDeletionImple implements TagDeletion {
    private final SearchTagByUuid searchTagByUuid;
    private final TagRepository tagRepository;

    @Override
    public void deleteByUuid(String uuid) {
        TagEntity tagEntity = searchTagByUuid.byTagActive(uuid);
        try {
            tagRepository.delete(tagEntity);
        } catch (DataIntegrityViolationException dt) {
            tagEntity.setStatus(StatusTag.INATIVO);
            tagRepository.save(tagEntity);
        }
    }
}
