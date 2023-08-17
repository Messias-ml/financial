package com.messiasproject.financial.domain.service.implementation.transaction.microservices.search;

import com.messiasproject.financial.domain.exception.ThereIsNotRecordException;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TransactionRepository;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.search.SearchTransactionByUuid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SearchTransactionByUuidImple implements SearchTransactionByUuid {
    private final TransactionRepository repository;

    @Override
    public TransactionEntity find(String uuid) {
        return repository.findTransactionByUuid(uuid)
                .orElseThrow(() -> new ThereIsNotRecordException(new Object[]{"de transaction com o uuid " + uuid}));
    }
}
