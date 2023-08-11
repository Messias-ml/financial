package com.messiasproject.financial.infrastructure.interfaces.tags.microservices.create;

import com.messiasproject.financial.api.model.tag.CreationTagDTO;

public interface TagCreation {
    void create(CreationTagDTO tagDTO);
}
