package com.messiasproject.financial.domain.service.implementation.tags.microservices.search;

import com.messiasproject.financial.api.model.tag.StatusTag;
import com.messiasproject.financial.api.model.tag.TagDTO;
import com.messiasproject.financial.domain.exception.ThereIsNotRecordException;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchTagByUuid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convert;

@Component
@AllArgsConstructor
public class SearchTagByUuidImple implements SearchTagByUuid {
    private final TagRepository tagRepository;
    @Override
    public TagEntity findEntity(String uuid) {
        return tagRepository.findTagByUuid(uuid)
                .orElseThrow(() -> new ThereIsNotRecordException(new Object[]{"de tag com o uuid " + uuid}));
    }

    @Override
    public TagEntity byTagActive(String uuid) {
        TagEntity tagEntity = findEntity(uuid);
        if (StatusTag.INATIVO.equals(tagEntity.getStatus())){
            throw new ThereIsNotRecordException(new Object[]{"de tag com o uuid " + uuid});
        }
        return tagEntity;
    }

    @Override
    public TagDTO findDTO(String uuid) {
        TagEntity tagEntity = findEntity(uuid);
        return convert(tagEntity, TagDTO.class);
    }
}
