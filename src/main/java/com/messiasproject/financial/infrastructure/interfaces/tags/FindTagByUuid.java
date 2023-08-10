package com.messiasproject.financial.infrastructure.interfaces.tags;

import com.messiasproject.financial.domain.model.entity.TagEntity;

public interface FindTagByUuid {
    TagEntity search(String uuid);

    TagEntity byTagActive(String uuid);
}
