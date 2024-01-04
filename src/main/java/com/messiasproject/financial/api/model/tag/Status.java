package com.messiasproject.financial.api.model.tag;

public enum Status {
    ATIVO("ativo"),
    INATIVO("inativo");
    private String StatusTag;

    Status(String statusTag) {
        StatusTag = statusTag;
    }
}
