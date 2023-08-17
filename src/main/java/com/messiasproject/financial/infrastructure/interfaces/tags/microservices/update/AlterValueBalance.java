package com.messiasproject.financial.infrastructure.interfaces.tags.microservices.update;

import com.messiasproject.financial.domain.model.entity.TransactionEntity;

public interface AlterValueBalance {
    void update(TransactionEntity transactionEntity);
}
