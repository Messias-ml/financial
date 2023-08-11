package com.messiasproject.financial.api.model.tag;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TagDTO {

    private String uuid;

    private String name;

    private StatusTag statusTag;

    private BigDecimal initialValue;

    private BigDecimal balance;
}
