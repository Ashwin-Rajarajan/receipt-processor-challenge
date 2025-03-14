package com.fetch.interview;

import com.fetch.interview.dto.response.BaseErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * To handle errors for all APIs in this application. In case of an error like passing an object instead of a
 * string, passing wrongly formatted dates,etc. the instruction of the receipts API says we should return a custom
 * error message. So I manually added the error message for that API and return just a HTTP 400 for other APIs.
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseErrorResponse> handleJsonError(HttpMessageNotReadableException exception, HttpServletRequest request) {
        if(request.getRequestURI().contains("process")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BaseErrorResponse.builder().description("The receipt is invalid.").build());
        }
        return ResponseEntity.badRequest().build();
    }

}
