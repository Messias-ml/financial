package com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.search;

import com.messiasproject.financial.domain.model.entity.TransactionEntity;

public interface SearchTransactionByUuid {
    TransactionEntity find(String uuid);
}
