package com.messiasproject.financial.infrastructure.interfaces.tags;

import com.messiasproject.financial.domain.model.entity.TagEntity;

public interface FindTagByUuid {
    TagEntity findTagByUuid(String uuid);
}
