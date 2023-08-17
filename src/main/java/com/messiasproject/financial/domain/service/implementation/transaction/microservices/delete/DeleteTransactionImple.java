package com.messiasproject.financial.domain.service.implementation.transaction.microservices.delete;

import com.messiasproject.financial.api.model.transaction.CreateTransactionDTO;
import com.messiasproject.financial.domain.model.TypeTransaction;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.domain.repository.TransactionRepository;
import com.messiasproject.financial.domain.service.implementation.tags.microservices.update.AlterValueBalanceImple;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.update.AlterValueBalance;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.DeleteTransaction;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.create.TransactionCreation;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.search.SearchTransactionByUuid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convert;

@Component
@AllArgsConstructor
public class DeleteTransactionImple implements DeleteTransaction {

    private final TransactionRepository repository;

    private final TransactionCreation transactionCreation;
    private final SearchTransactionByUuid searchTransactionByUuid;
    @Override
    public void delete(String uuid) {
        TransactionEntity transactionEntity = searchTransactionByUuid.find(uuid);
        repository.deleteByUuid(uuid);
        repository.flush();
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO();
        if (TypeTransaction.ADD_VALUE.equals(transactionEntity.getTypeTransaction())){
            convert(transactionEntity, createTransactionDTO);
            createTransactionDTO.setTypeTransaction(TypeTransaction.REMOVE_VALUE);
            createTransactionDTO.setDescription("TRANSACTION EXCLUSION Debit more: "
                    .concat(createTransactionDTO.getValue().toString()));
            transactionCreation.create(createTransactionDTO);
        }else {
            convert(transactionEntity, createTransactionDTO);
            createTransactionDTO.setTypeTransaction(TypeTransaction.ADD_VALUE);
            createTransactionDTO.setDescription("TRANSACTION EXCLUSION Credit more: "
                    .concat(createTransactionDTO.getValue().toString()));
            transactionCreation.create(createTransactionDTO);
        }
    }
}
