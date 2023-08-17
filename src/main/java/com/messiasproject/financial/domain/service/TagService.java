package com.messiasproject.financial.domain.service;

import com.messiasproject.financial.api.model.tag.CreationTagDTO;
import com.messiasproject.financial.api.model.tag.TagDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.create.TagCreation;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.delete.TagDeletion;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchAllTags;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchTagByUuid;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchTagsByName;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.update.AlterValueBalance;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.update.TagUpdate;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    private final SearchTagByUuid searchTagByUuid;

    private final SearchTagsByName searchTagsByName;

    private final SearchAllTags searchAllTags;

    private final TagCreation tagCreation;

    private final TagUpdate updateTag;
    private final AlterValueBalance alterValueBalance;

    private final TagDeletion tagDeletion;

    public Page<TagDTO> findAllTags(Pageable pageable){
        return searchAllTags.find(pageable);
    }

    public Page<TagDTO> findTagsByName(String name, Pageable pageable) {
        return searchTagsByName.find(name, pageable);
    }

    public TagDTO findTagByUuid(String uuid) {
        return searchTagByUuid.findDTO(uuid);
    }

    public void createTag(CreationTagDTO tagDTO){
        tagCreation.create(tagDTO);
    }

    public void deleteTag(String uuid) {
        tagDeletion.deleteByUuid(uuid);
    }

    @EventListener
    private void alterValueBalance(TransactionEntity transactionEntity){
        alterValueBalance.update(transactionEntity);
    }

    public void updateTag(String uuid, CreationTagDTO tagDTO) {
        updateTag.update(uuid, tagDTO);
    }
}
