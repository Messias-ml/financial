package com.messiasproject.financial.api.model.transaction;

import com.messiasproject.financial.domain.model.TypeTransaction;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateTransactionDTO {

    @NotBlank
    @Size(min = 2)
    private String description;

    @NotNull
    private TypeTransaction typeTransaction;

    @Size(min = 2)
    private String uuidTag;

    @NotNull
    @Min(0)
    private BigDecimal value;

    private LocalDateTime dateTransaction;
}
