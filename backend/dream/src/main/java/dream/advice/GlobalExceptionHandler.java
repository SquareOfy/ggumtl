package dream.advice;

import dream.common.domain.ResultTemplate;
import dream.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( {NotFoundException.class} )
    public ResultTemplate handleBadRequestExceptions(Exception e){
        
        return ResultTemplate.builder().status(HttpStatus.BAD_REQUEST).data(e.getMessage()).build();
    }

}