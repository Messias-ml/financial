package com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.create;

import com.messiasproject.financial.api.model.transaction.CreateTransactionDTO;

public interface TransactionCreation {
    void create(CreateTransactionDTO transactionDTO);
}
