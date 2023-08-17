package com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.create;

import com.messiasproject.financial.api.model.transaction.CreateTransactionDTO;
import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;

public interface TransactionCreation {
    void create(CreateTransactionDTO transactionDTO);
    void registerTransaction(CreateTransactionDTO transactionDTO, TransactionEntity transactionEntity, TagEntity tagEntity);
}
