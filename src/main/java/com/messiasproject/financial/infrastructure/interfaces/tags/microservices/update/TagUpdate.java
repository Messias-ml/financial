package com.messiasproject.financial.infrastructure.interfaces.tags.microservices.update;

import com.messiasproject.financial.api.model.tag.CreationTagDTO;

public interface TagUpdate {

    void update(String uuid, CreationTagDTO tag);
}
