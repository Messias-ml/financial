package com.messiasproject.financial.domain.model;

public enum TypeTransaction {
    REMOVE_VALUE("remove value"),
    ADD_VALUE("add value");
    private String typeTransaction;

    TypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }
}
