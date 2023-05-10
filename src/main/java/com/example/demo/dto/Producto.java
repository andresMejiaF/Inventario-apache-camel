package com.example.demo.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Producto {

    @Id
    @SerializedName("id")
    private int id;


    @NonNull
    @SerializedName("nombre")
    private String nombre;


    @NonNull
    @SerializedName("descripcion")
    private String descripcion;

    @NonNull
    @SerializedName("precio")
    private float precio;

    @NonNull
    @SerializedName("cantidad")
    private int cantidad;

}
