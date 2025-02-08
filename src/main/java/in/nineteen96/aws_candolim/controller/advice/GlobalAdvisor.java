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

//    HttpMessageNotReadableException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJobPostingAlreadyExistsException(HttpMessageNotReadableException e) {
        log.error("invoked handleJobPostingAlreadyExistsException \n{}", e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .exception("Http Message Not Readable Exception")
                .errorMessage("request body is missing")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .success(false)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
