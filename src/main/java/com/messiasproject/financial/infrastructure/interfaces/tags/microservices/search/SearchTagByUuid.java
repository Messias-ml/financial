package com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search;

import com.messiasproject.financial.api.model.tag.TagDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;

public interface SearchTagByUuid {
    TagEntity findEntity(String uuid);

    TagEntity byTagActive(String uuid);

    TagDTO findDTO(String uuid);
}
