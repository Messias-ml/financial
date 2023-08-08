package com.messiasproject.financial.domain.service.implementation.events;

import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.infrastructure.interfaces.events.NotifyTagEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TagEventTransactionImple implements NotifyTagEvent {

    private final ApplicationEventPublisher publisher;

    @Override
    public void eventTransaction(TransactionEntity transactionEntity) {
        publisher.publishEvent(transactionEntity);
    }
}
