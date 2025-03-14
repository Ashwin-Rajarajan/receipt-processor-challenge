package com.fetch.interview.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This API acts as a fallback in case an unmapped API is called. This will also show a message on a browser if the APIs
 * are being accessed from one.
 */
@RestController
public class FallBackApi {

    // /** is used to map to all undeclared API paths.
    @RequestMapping("/**")
    public ResponseEntity<String> handleUnMappedRequests(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("<html><body><h1>If you are calling this API to test installation of the Receipt Processor code, it has been installed successfully! " +
                        "This application only supports two APIs. Please read the documentation for instructions on how to access them.</h1></body></html>");
    }
}
