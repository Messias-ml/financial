package com.messiasproject.financial.domain.exception;

import lombok.Getter;

@Getter
public class RecordNotFoundException extends BusinessException{
    private Object[] objects;

    public RecordNotFoundException(Object[] objects) {
        super("Record_Not_Found", objects);
        this.objects = objects;
    }
}
