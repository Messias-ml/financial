package com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search;

import com.messiasproject.financial.api.model.tag.TagDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchTagsByName {
    Page<TagDTO> find(String name, Pageable pageable);
}
