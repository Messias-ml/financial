package com.messiasproject.financial.api.model.transaction;

import com.messiasproject.financial.api.model.tag.ResumeToExternalTagDTO;
import com.messiasproject.financial.domain.model.TypeTransaction;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SearchTransactionDTO {
    private String uuid;

    private String description;

    private TypeTransaction typeTransaction;

    private ResumeToExternalTagDTO tag;

    private BigDecimal value;

    private LocalDateTime dateTransaction;
}
