package com.messiasproject.financial.domain.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private final transient Object objects[];

    public BusinessException(String message, Object[] objects) {
        super(message);
        this.objects = objects;
    }

    public BusinessException(Object[] objects) {
        super("businees_Exception");
        this.objects = objects;
    }
}
