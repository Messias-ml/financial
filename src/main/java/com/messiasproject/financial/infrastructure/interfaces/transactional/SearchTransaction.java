package com.messiasproject.financial.infrastructure.interfaces.transactional;

import com.messiasproject.financial.api.model.transaction.SearchTransactionDTO;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchTransaction {
    Page<SearchTransactionDTO> findAll(Pageable pageable);

    Page<SearchTransactionDTO> byTags(String uuidTag, Pageable pageable);
}
