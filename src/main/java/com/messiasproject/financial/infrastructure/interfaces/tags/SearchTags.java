package com.messiasproject.financial.infrastructure.interfaces.tags;

import com.messiasproject.financial.api.model.tag.TagDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchTags {
    Page<TagDTO> findAllByName(String name, Pageable pageable);
    TagDTO findTagByUuid(String uuid);
}
