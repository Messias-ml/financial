package com.messiasproject.financial.domain.service.implementation.tags.microservices.update;

import com.messiasproject.financial.api.model.tag.CreationTagDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchTagByUuid;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.update.TagUpdate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convert;

@Component
@AllArgsConstructor
public class TagUpdateImple implements TagUpdate {

    private final SearchTagByUuid searchTagByUuid;
    private final TagRepository tagRepository;

    @Override
    public void update(String uuid, CreationTagDTO tag) {
        TagEntity tagEntity = searchTagByUuid.findEntity(uuid);
        convert(tag, tagEntity);
        tagRepository.save(tagEntity);
    }
}
