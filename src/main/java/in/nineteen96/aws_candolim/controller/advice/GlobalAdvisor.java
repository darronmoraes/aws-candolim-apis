package in.nineteen96.aws_candolim.controller.advice;

import in.nineteen96.aws_candolim.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
@Slf4j
public class GlobalAdvisor {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("invoked handlerHttpMessageNotReadableException \n{}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("Http Message Not Readable Exception");
        errorResponse.setErrorMessage("request body is missing");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setSuccess(false);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception e) {
        log.error("invoked handlerException \n{}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("Exception");
        errorResponse.setErrorMessage("Oops! something went wrong..");
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setSuccess(false);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
