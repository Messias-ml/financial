package com.messiasproject.financial.api.model.tag;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResumeToExternalTagDTO {
    private String name;
    private BigDecimal currentValue;
    private BigDecimal balance;
}
