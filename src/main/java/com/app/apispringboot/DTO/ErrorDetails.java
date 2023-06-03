package com.app.apispringboot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

}
