package com.messiasproject.financial.domain.service.implementation.tags.microservices.update;

import com.messiasproject.financial.domain.model.entity.TagEntity;
import com.messiasproject.financial.domain.model.entity.TransactionEntity;
import com.messiasproject.financial.domain.repository.TagRepository;
import com.messiasproject.financial.infrastructure.interfaces.tags.microservices.update.AlterValueBalance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class AlterValueBalanceImple implements AlterValueBalance {
    private final TagRepository tagRepository;

    @Override
    public void update(TransactionEntity transactionEntity) {
        TagEntity tag = transactionEntity.getTag();
        BigDecimal balance = transactionEntity.getCurrentValueTag();
        tag.setBalance(balance);
        tagRepository.save(tag);
    }
}
