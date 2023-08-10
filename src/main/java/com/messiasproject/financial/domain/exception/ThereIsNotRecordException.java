package com.messiasproject.financial.domain.exception;

import lombok.Getter;

@Getter
public class ThereIsNotRecordException extends BusinessException {
    private Object[] objects;

    public ThereIsNotRecordException(Object[] objects) {
        super("ThereIsNot-Record-Exception", objects);
        this.objects = objects;
    }
}
