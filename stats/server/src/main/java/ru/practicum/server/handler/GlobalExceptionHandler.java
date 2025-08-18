package ru.practicum.server.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgument(final IllegalArgumentException exception){
        log.warn("illegal argument", exception);
        return new ErrorResponse("illegal argument", exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(final Exception exception) {
        log.warn("неизвестная ошибка", exception);
        return new ErrorResponse("неизвестная ошибка", exception.getMessage());
    }
}
