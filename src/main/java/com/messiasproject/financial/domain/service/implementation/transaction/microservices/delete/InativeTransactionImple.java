package com.messiasproject.financial.domain.service.implementation.transaction.microservices.delete;

import com.messiasproject.financial.api.model.tag.Status;
import com.messiasproject.financial.api.model.transaction.CreateTransactionDTO;
import com.messiasproject.financial.domain.model.TypeTransaction;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TransactionRepository;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.InativeTransaction;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.create.TransactionCreation;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.search.SearchTransactionByUuid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convert;

@Component
@AllArgsConstructor
public class InativeTransactionImple implements InativeTransaction {

    private final TransactionRepository repository;

    private final TransactionCreation transactionCreation;
    private final SearchTransactionByUuid searchTransactionByUuid;
    @Override
    public void inative(String uuid) {
        TransactionEntity transactionEntity = searchTransactionByUuid.find(uuid);
        transactionEntity.setStatus(Status.INATIVO);
        repository.save(transactionEntity);
        repository.flush();
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO();
        if (TypeTransaction.ADD_VALUE.equals(transactionEntity.getTypeTransaction())){
            convert(transactionEntity, createTransactionDTO);
            createTransactionDTO.setTypeTransaction(TypeTransaction.REMOVE_VALUE);
            createTransactionDTO.setDescription("REPOSITION VALUE Debit transaction: "
                    .concat(createTransactionDTO.getDescription()));
            createTransactionDTO.setDateTransaction(null);
            transactionCreation.create(createTransactionDTO);
        }else {
            convert(transactionEntity, createTransactionDTO);
            createTransactionDTO.setTypeTransaction(TypeTransaction.ADD_VALUE);
            createTransactionDTO.setDescription("REPOSITION VALUE Credit transaction: "
                    .concat(createTransactionDTO.getDescription()));
            createTransactionDTO.setDateTransaction(null);
            transactionCreation.create(createTransactionDTO);
        }
    }
}
