package ru.practicum.main.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(NotFoundException e) {
        log.warn("NotFoundException: {}", e.getMessage(), e);
        return new ApiError(
                getStackTraceAsList(e),
                e.getMessage(),
                "Требуемый объект не был найден.",
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(ValidationException e) {
        log.warn("ValidationException: {}", e.getMessage(), e);
        return new ApiError(
                getStackTraceAsList(e),
                e.getMessage(),
                "Некорректно составленный запрос.",
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(ConflictException e) {
        log.warn("ConflictException: {}", e.getMessage(), e);
        return new ApiError(
                getStackTraceAsList(e),
                e.getMessage(),
                "Нарушение целостности данных.",
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleForbiddenException(ForbiddenException e) {
        log.warn("ForbiddenException: {}", e.getMessage(), e);
        return new ApiError(
                getStackTraceAsList(e),
                e.getMessage(),
                "Для запрошенной операции условия не выполнены.",
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleAllExceptions(Exception e) {
        log.error("Internal server error: ", e);
        return new ApiError(
                getStackTraceAsList(e),
                "Внутренняя ошибка сервера",
                "Произошла непредвиденная ошибка.",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private List<String> getStackTraceAsList(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stackTrace = sw.toString();

        // Разбиваем stacktrace на строки и ограничиваем длину
        return List.of(stackTrace.split("\n"))
                .stream()
                .limit(20) // Ограничиваем количество строк для безопасности
                .toList();
    }
}
