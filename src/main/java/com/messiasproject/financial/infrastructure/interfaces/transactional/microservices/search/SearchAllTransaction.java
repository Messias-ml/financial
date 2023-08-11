package com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.search;

import com.messiasproject.financial.api.model.transaction.SearchTransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchAllTransaction {
    Page<SearchTransactionDTO> find(Pageable pageable);
}
