package com.messiasproject.financial.domain.service;

import com.messiasproject.financial.api.model.tag.CreationTagDTO;
import com.messiasproject.financial.api.model.tag.TagDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.FindTagByUuid;
import com.messiasproject.financial.infrastructure.interfaces.tags.SearchTags;
import com.messiasproject.financial.infrastructure.specification.TagSpecification;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convert;
import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    private final SearchTags searchTags;

    public List<TagDTO> findAllTags(){
        return convertList(tagRepository.findAll(), TagDTO.class);
    }

    public void createTag(CreationTagDTO tagDTO){
        TagEntity tagEntity = convert(tagDTO, TagEntity.class);
        tagRepository.save(tagEntity);
    }

    public List<TagDTO> findTagsByName(String name) {
        return searchTags.findAllByName(name);
    }

    public TagDTO findTagByUuid(String uuid) {
        return searchTags.findTagByUuid(uuid);
    }

    @EventListener
    private void alterValueBalance(TransactionEntity transactionEntity){
        TagEntity tag = transactionEntity.getTag();
        BigDecimal balance = transactionEntity.getCurrentValueTag();
        tag.setBalance(balance);
        tagRepository.save(tag);
    }
}
