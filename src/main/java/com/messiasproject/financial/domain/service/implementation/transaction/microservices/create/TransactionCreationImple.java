package com.messiasproject.financial.domain.service.implementation.transaction.microservices.create;

import com.messiasproject.financial.api.model.transaction.CreateTransactionDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TransactionRepository;
import com.messiasproject.financial.infrastructure.interfaces.events.NotifyTagEvent;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.search.SearchTagByUuid;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.create.TransactionCreation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convert;
import static com.messiasproject.financial.domain.model.TypeTransaction.ADD_VALUE;

@Component
@AllArgsConstructor
public class TransactionCreationImple implements TransactionCreation {

    private final TransactionRepository repository;
    private final SearchTagByUuid searchTagByUuid;
    private final NotifyTagEvent notifyTagEvent;

    @Override
    public void create(CreateTransactionDTO transactionDTO) {
        if (transactionDTO.getDateTransaction() == null){
            transactionDTO.setDateTransaction(LocalDateTime.now());
        }
        TransactionEntity transactionEntity = convert(transactionDTO, TransactionEntity.class);
        String uuidTag = transactionDTO.getUuidTag();
        TagEntity tagEntity = searchTagByUuid.byTagActive(uuidTag);
        transactionEntity.setTag(tagEntity);
        registerTransaction(transactionDTO, transactionEntity, tagEntity);
    }

    @Override
    public void registerTransaction(CreateTransactionDTO transactionDTO, TransactionEntity transactionEntity, TagEntity tagEntity) {
        if (ADD_VALUE.equals(transactionEntity.getTypeTransaction())){
            transactionEntity.setCurrentValueTag(tagEntity.getBalance().add(transactionDTO.getValue()));
        } else {
            transactionEntity.setCurrentValueTag(tagEntity.getBalance().subtract(transactionDTO.getValue()));
        }
        repository.save(transactionEntity);
        notifyTagEvent.eventTransaction(transactionEntity);
    }
}
