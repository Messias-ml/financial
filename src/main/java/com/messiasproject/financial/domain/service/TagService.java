package com.messiasproject.financial.domain.service;

import com.messiasproject.financial.api.model.specification.TagFilterSpec;
import com.messiasproject.financial.api.model.tag.CreationTagDTO;
import com.messiasproject.financial.api.model.tag.StatusTag;
import com.messiasproject.financial.api.model.tag.TagDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.FindTagByUuid;
import com.messiasproject.financial.infrastructure.interfaces.tags.SearchTags;
import com.messiasproject.financial.infrastructure.specification.TagSpecification;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convert;
import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    private final FindTagByUuid findTagByUuid;

    private final SearchTags searchTags;

    public Page<TagDTO> findAllTags(Pageable pageable){
        TagFilterSpec tagFilterSpec = new TagFilterSpec();
        TagSpecification tagSpecification = new TagSpecification(tagFilterSpec);
        List<TagDTO> tagDTOS = convertList(tagRepository.findAll(tagSpecification, pageable).getContent(), TagDTO.class);
        return new PageImpl<>(tagDTOS, pageable, tagDTOS.size());
    }

    public void createTag(CreationTagDTO tagDTO){
        TagEntity tagEntity = convert(tagDTO, TagEntity.class);
        tagEntity.setInitialValue(tagEntity.getBalance());
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

    public void deleteTag(String uuid) {
        TagEntity tagEntity = findTagByUuid.byTagActive(uuid);
        try {
            tagRepository.delete(tagEntity);
        } catch (DataIntegrityViolationException dt) {
            tagEntity.setStatus(StatusTag.INATIVO);
            tagRepository.save(tagEntity);
        }
    }
}
