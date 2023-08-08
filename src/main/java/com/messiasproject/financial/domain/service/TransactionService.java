package com.messiasproject.financial.domain.service;

import com.messiasproject.financial.api.model.transaction.CreateTransactionDTO;
import com.messiasproject.financial.api.model.transaction.SearchTransactionDTO;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.infrastructure.interfaces.transactional.CreateTransaction;
import com.messiasproject.financial.infrastructure.interfaces.transactional.SearchTransaction;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private final SearchTransaction searchTransaction;
    private final CreateTransaction createTransaction;

    public Page<SearchTransactionDTO> findAllTransactions(Pageable pageable) {
        return searchTransaction.findAll(pageable);
    }

    public void createTransaction(CreateTransactionDTO transactionDTO) {
        createTransaction.create(transactionDTO);
    }

    public Page<SearchTransactionDTO> findAllTransactionsByTag(String uuidTag, Pageable pageable) {
        return searchTransaction.byTags(uuidTag, pageable);
    }
}
