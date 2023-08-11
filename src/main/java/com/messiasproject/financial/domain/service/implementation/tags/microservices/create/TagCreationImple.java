package com.messiasproject.financial.domain.service.implementation.tags.microservices.create;

import com.messiasproject.financial.api.model.tag.CreationTagDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.create.TagCreation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convert;

@Component
@AllArgsConstructor
public class TagCreationImple implements TagCreation {

    private final TagRepository tagRepository;

    @Override
    public void create(CreationTagDTO tagDTO) {
        TagEntity tagEntity = convert(tagDTO, TagEntity.class);
        tagEntity.setInitialValue(tagEntity.getBalance());
        tagRepository.save(tagEntity);
    }
}
