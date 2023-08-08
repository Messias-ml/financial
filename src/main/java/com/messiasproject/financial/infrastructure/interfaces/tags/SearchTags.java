package com.messiasproject.financial.infrastructure.interfaces.tags;

import com.messiasproject.financial.api.model.tag.TagDTO;

import java.util.List;

public interface SearchTags {
    List<TagDTO> findAllByName(String name);
    TagDTO findTagByUuid(String uuid);
}
