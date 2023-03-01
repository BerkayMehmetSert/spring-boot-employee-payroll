package com.bms.employeepayroll.core.exceptions.problemdetails;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InternalServerProblemDetails extends ProblemDetail {
    public InternalServerProblemDetails(String detail){
        setTitle("Internal Server Error");
        setDetail(detail);
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        setType(URI.create("https://example.com/problem/internal-server-error"));
        setProperty("timestamp", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }
}
