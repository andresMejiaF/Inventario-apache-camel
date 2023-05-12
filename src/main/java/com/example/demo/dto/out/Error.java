package com.example.demo.dto.out;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    Integer code;

    String description;
}
