package com.messiasproject.financial.domain.service.implementation.tags;

import com.messiasproject.financial.api.model.tag.StatusTag;
import com.messiasproject.financial.domain.exception.RecordNotFoundException;
import com.messiasproject.financial.domain.exception.ThereIsNotRecordException;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.FindTagByUuid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindTagByUuidImple implements FindTagByUuid {
    private final TagRepository tagRepository;
    @Override
    public TagEntity search(String uuid) {
        return tagRepository.findTagByUuid(uuid)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{"do uuid da tag: "+uuid}));
    }

    @Override
    public TagEntity byTagActive(String uuid) {
        TagEntity tagEntity = search(uuid);
        if (StatusTag.INATIVO.equals(tagEntity.getStatus())){
            throw new ThereIsNotRecordException(new Object[]{"de tag com o uuid " + uuid});
        }
        return tagEntity;
    }
}
