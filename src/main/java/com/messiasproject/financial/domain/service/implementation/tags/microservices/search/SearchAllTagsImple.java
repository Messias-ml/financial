package com.messiasproject.financial.domain.service.implementation.tags.microservices.search;

import com.messiasproject.financial.api.model.specification.TagFilterSpec;
import com.messiasproject.financial.api.model.tag.Status;
import com.messiasproject.financial.api.model.tag.TagDTO;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchAllTags;
import com.messiasproject.financial.infrastructure.specification.TagSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convertList;

@AllArgsConstructor
@Component
public class SearchAllTagsImple implements SearchAllTags {
    private final TagRepository tagRepository;

    @Override
    public Page<TagDTO> find(Pageable pageable) {
        TagFilterSpec tagFilterSpec = new TagFilterSpec();
        tagFilterSpec.setStatus(Status.ATIVO);
        TagSpecification tagSpecification = new TagSpecification(tagFilterSpec);
        List<TagDTO> tagDTOS = convertList(tagRepository.findAll(tagSpecification, pageable).getContent(), TagDTO.class);
        return new PageImpl<>(tagDTOS, pageable, tagDTOS.size());
    }
}
