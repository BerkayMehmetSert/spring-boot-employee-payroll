package com.bms.employeepayroll.core.exceptions.problemdetails;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotFoundProblemDetails extends ProblemDetail {
    public NotFoundProblemDetails(String detail) {
        setTitle("NotFound Exception");
        setDetail(detail);
        setStatus(HttpStatus.NOT_FOUND);
        setType(URI.create("https://example.com/notfound-exception"));
        setProperty("timestamp", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }
}
