package com.example.demo.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionClass {

    Integer code;
    Boolean successful;

    List<Error> errors;
}
