package com.messiasproject.financial.infrastructure.interfaces.events;

import com.messiasproject.financial.domain.model.entity.TransactionEntity;

public interface NotifyTagEvent {
    void eventTransaction(TransactionEntity transactionEntity);
}
