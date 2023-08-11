package com.messiasproject.financial.domain.service.implementation.tags.microservices.search;

import com.messiasproject.financial.api.model.specification.TagFilterSpec;
import com.messiasproject.financial.api.model.tag.TagDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchTagByUuid;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchTagsByName;
import com.messiasproject.financial.infrastructure.specification.TagSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convert;
import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convertList;

@Component
@AllArgsConstructor
public class SearchTagsByNameImple implements SearchTagsByName {

    private final TagRepository tagRepository;

    private final SearchTagByUuid searchTagByUuid;

    @Override
    public Page<TagDTO> find(String name, Pageable pageable) {
        TagFilterSpec tagFilterSpec = new TagFilterSpec();
        tagFilterSpec.setName(name);
        TagSpecification tagSpecification = new TagSpecification(tagFilterSpec);
        Page<TagEntity> tagEntityList = tagRepository.findAll(tagSpecification, pageable);
        List<TagDTO> tagDTOS = convertList(tagEntityList.getContent(), TagDTO.class);
        return new PageImpl<>(tagDTOS, pageable, tagDTOS.size());
    }

}
