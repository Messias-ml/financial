package com.messiasproject.financial.domain.service.implementation.transaction.microservices.search;

import com.messiasproject.financial.api.model.transaction.SearchTransactionDTO;
import com.messiasproject.financial.domain.repository.TransactionRepository;
import com.messiasproject.financial.infrastructure.interfaces.transactional.microservices.search.SearchAllTransaction;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.messiasproject.financial.core.config.modelMapper.ModelMapperConvert.convertList;

@AllArgsConstructor
@Component
public class SearchAllTransactionImple implements SearchAllTransaction {

    private final TransactionRepository repository;

    @Override
    public Page<SearchTransactionDTO> find(Pageable pageable) {
        List<SearchTransactionDTO> searchTransactionDTOS = convertList(repository.findAll(pageable).getContent(), SearchTransactionDTO.class);
        return new PageImpl<>(searchTransactionDTOS, pageable, searchTransactionDTOS.size());
    }
}
