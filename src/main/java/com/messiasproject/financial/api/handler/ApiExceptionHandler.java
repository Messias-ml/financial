package com.messiasproject.financial.api.handler;

import com.messiasproject.financial.domain.exception.ListEmptyException;
import com.messiasproject.financial.domain.exception.RecordNotFoundException;
import com.messiasproject.financial.domain.exception.ThereIsNotRecordException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Problem> hendlerRecordNotFound(RecordNotFoundException recordNotFoundException) {
        String nameErro = "Registro não encontrado.";
        Problem problem = getProblem(recordNotFoundException.getMessage(), recordNotFoundException.getObjects(),
                nameErro, HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(problem);
    }
    @ExceptionHandler(ThereIsNotRecordException.class)
    public ResponseEntity<Problem> hendlerThereIsNotRecord(ThereIsNotRecordException thereIsNotRecordException) {
        String nameErro = "Registro não existente.";
        Problem problem = getProblem(thereIsNotRecordException.getMessage(), thereIsNotRecordException.getObjects(),
                nameErro, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problem);
    }
    @ExceptionHandler(ListEmptyException.class)
    public ResponseEntity<Problem> hendlerListEmpty(ListEmptyException listEmptyException) {
        String nameErro = "Lista sem conteúdo";
        Problem problem = getProblem(listEmptyException.getMessage(), null, nameErro, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problem);
    }
    private Problem getProblem(String message, Object[] objects, String nameErro, Integer status) {
        String detail = getMessage(objects, message.concat(".detail"));
        String messageUser = getMessage(objects, message.concat(".message_user"));
        return Problem.builder()
                .status(status)
                .title(nameErro)
                .detail(detail)
                .messageUser(messageUser)
                .build();
    }
    private String getMessage(Object[] objects, String message) {
        return messageSource.getMessage(message, objects, LocaleContextHolder.getLocale());
    }
}
