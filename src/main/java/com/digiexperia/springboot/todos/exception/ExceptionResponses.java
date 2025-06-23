package com.digiexperia.springboot.todos.exception;

import lombok.Data;

@Data
public class ExceptionResponses {
    private int status;
    private String message;
    private long timeStamp;
}
