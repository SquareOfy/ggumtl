package dream.advice;

import dream.common.exception.ExceptionResponse;
import dream.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception e) {
        log.error("error", e);
        return ResponseEntity.badRequest().body(ExceptionResponse.from(e.getMessage()));
    }

}