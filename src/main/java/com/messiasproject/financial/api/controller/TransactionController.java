package com.messiasproject.financial.api.controller;

import com.messiasproject.financial.api.model.transaction.CreateTransactionDTO;
import com.messiasproject.financial.api.model.transaction.SearchTransactionDTO;
import com.messiasproject.financial.domain.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping
    public Page<SearchTransactionDTO> findAllTransactions(@PageableDefault(sort = "dateTransaction",
            direction = Sort.Direction.DESC) Pageable pageable){
        return service.findAllTransactions(pageable);
    }

    @GetMapping("/tag/{uuidTag}")
    public Page<SearchTransactionDTO> findAllTransactionsByTags(@PathVariable String uuidTag, @PageableDefault(sort = "dateTransaction",
            direction = Sort.Direction.DESC) Pageable pageable){
        return service.findAllTransactionsByTag(uuidTag, pageable);
    }

    @PostMapping
    public void createTransaction(@RequestBody @Valid CreateTransactionDTO transactionDTO){
        service.createTransaction(transactionDTO);
    }

    @DeleteMapping("/{uuid}")
    public void deleteTransaction(@PathVariable String uuid){
        service.deleteTransaction(uuid);
    }
}
