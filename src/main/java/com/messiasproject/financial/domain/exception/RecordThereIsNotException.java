package com.messiasproject.financial.domain.exception;

import lombok.Getter;

@Getter
public class RecordThereIsNotException extends BusinessException {
    private Object[] objects;

    public RecordThereIsNotException(Object[] objects) {
        super("Record-ThereIsNot-Exception", objects);
        this.objects = objects;
    }
}
