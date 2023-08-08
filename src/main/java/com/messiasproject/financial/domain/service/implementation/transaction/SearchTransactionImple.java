package com.messiasproject.financial.domain.service.implementation.transaction;

import com.messiasproject.financial.api.model.transaction.SearchTransactionDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TransactionRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.FindTagByUuid;
import com.messiasproject.financial.infrastructure.interfaces.tags.SearchTags;
import com.messiasproject.financial.infrastructure.interfaces.transactional.SearchTransaction;
import com.messiasproject.financial.infrastructure.specification.TransactionSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convertList;

@AllArgsConstructor
@Component
public class SearchTransactionImple implements SearchTransaction {

    private final TransactionRepository repository;

    private final FindTagByUuid findTagByUuid;

    @Override
    public Page<SearchTransactionDTO> findAll(Pageable pageable) {
        List<SearchTransactionDTO> searchTransactionDTOS = convertList(repository.findAll(pageable).getContent(), SearchTransactionDTO.class);
        return new PageImpl<>(searchTransactionDTOS, pageable, searchTransactionDTOS.size());
    }

    @Override
    public Page<SearchTransactionDTO> byTags(String uuidTag, Pageable pageable) {
        TagEntity tagByUuid = findTagByUuid.findTagByUuid(uuidTag);
        TransactionSpecification transactionSpecification = new TransactionSpecification(tagByUuid);
        Page<TransactionEntity> pageTransaction = repository.findAll(transactionSpecification, pageable);
        List<SearchTransactionDTO> searchTransactionDTOS = convertList(pageTransaction.getContent(), SearchTransactionDTO.class);
        return new PageImpl<>(searchTransactionDTOS, pageable, searchTransactionDTOS.size());
    }
}
