package com.messiasproject.financial.domain.service.implementation.transaction;

import com.messiasproject.financial.api.model.transaction.CreateTransactionDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TransactionRepository;
import com.messiasproject.financial.infrastructure.interfaces.events.NotifyTagEvent;
import com.messiasproject.financial.infrastructure.interfaces.tags.FindTagByUuid;
import com.messiasproject.financial.infrastructure.interfaces.transactional.CreateTransaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convert;
import static com.messiasproject.financial.domain.model.TypeTransaction.ADD_VALUE;

@Component
@AllArgsConstructor
public class CreateTransactionImple implements CreateTransaction {

    private final TransactionRepository repository;
    private final FindTagByUuid findTagByUuid;
    private final NotifyTagEvent notifyTagEvent;

    @Override
    public void create(CreateTransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = convert(transactionDTO, TransactionEntity.class);
        String uuidTag = transactionDTO.getUuidTag();
        TagEntity tagEntity = findTagByUuid.search(uuidTag);
        transactionEntity.setTag(tagEntity);
        if (ADD_VALUE.equals(transactionEntity.getTypeTransaction())){
            transactionEntity.setCurrentValueTag(tagEntity.getBalance().add(transactionDTO.getValue()));
        } else {
            transactionEntity.setCurrentValueTag(tagEntity.getBalance().subtract(transactionDTO.getValue()));
        }
        repository.save(transactionEntity);
        notifyTagEvent.eventTransaction(transactionEntity);
    }
}
