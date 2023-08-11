package com.messiasproject.financial.domain.service;

import com.messiasproject.financial.api.model.transaction.CreateTransactionDTO;
import com.messiasproject.financial.api.model.transaction.SearchTransactionDTO;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.create.CreateTransaction;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.search.SearchAllTransaction;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.search.SearchTransactionByName;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private final SearchAllTransaction searchAllTransaction;
    private final SearchTransactionByName searchTransactionByName;
    private final CreateTransaction createTransaction;

    public Page<SearchTransactionDTO> findAllTransactions(Pageable pageable) {
        return searchAllTransaction.find(pageable);
    }

    public void createTransaction(CreateTransactionDTO transactionDTO) {
        createTransaction.create(transactionDTO);
    }

    public Page<SearchTransactionDTO> findAllTransactionsByTag(String uuidTag, Pageable pageable) {
        return searchTransactionByName.find(uuidTag, pageable);
    }
}
