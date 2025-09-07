package com.authservice.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse <T>{

    private Integer statusCode;

    private String message;

    private T data;

    
    
}
