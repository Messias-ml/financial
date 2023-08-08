package com.messiasproject.financial.infrastructure.interfaces.transactional;

import com.messiasproject.financial.api.model.transaction.CreateTransactionDTO;

public interface CreateTransaction {
    void create(CreateTransactionDTO transactionDTO);
}
