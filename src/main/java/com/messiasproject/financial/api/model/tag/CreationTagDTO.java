package com.messiasproject.financial.api.model.tag;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreationTagDTO {

    @NotBlank
    @Size(min = 2)
    private String name;

    @Min(value = 0)
    private BigDecimal initialValue;

    @Min(value = 0)
    private BigDecimal balance;
}
