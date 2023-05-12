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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



    @SerializedName("nombre")
    private String nombre;


    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("precio")
    private float precio;


    @SerializedName("cantidad")
    private int cantidad;

}
