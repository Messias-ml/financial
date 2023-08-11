package com.messiasproject.financial.domain.service.implementation.transaction.microservices.search;

import com.messiasproject.financial.api.model.transaction.SearchTransactionDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TransactionRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchTagByUuid;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.search.SearchTransactionByName;
import com.messiasproject.financial.infrastructure.specification.TransactionSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convertList;

@Component
@AllArgsConstructor
public class SearchTransactionByNameImple implements SearchTransactionByName {
    private final SearchTagByUuid searchTagByUuid;

    private final TransactionRepository repository;
    @Override
    public Page<SearchTransactionDTO> find(String uuidTag, Pageable pageable) {
        TagEntity tagByUuid = searchTagByUuid.findEntity(uuidTag);
        TransactionSpecification transactionSpecification = new TransactionSpecification(tagByUuid);
        Page<TransactionEntity> pageTransaction = repository.findAll(transactionSpecification, pageable);
        List<SearchTransactionDTO> searchTransactionDTOS = convertList(pageTransaction.getContent(), SearchTransactionDTO.class);
        return new PageImpl<>(searchTransactionDTOS, pageable, searchTransactionDTOS.size());
    }
}
