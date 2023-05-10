package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Prueba {

    @Id
    @SerializedName("id")
    private int id;
    @NonNull
    @SerializedName("contenido")
    private String contenido;

}
