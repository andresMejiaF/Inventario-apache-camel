package com.example.demo.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class OrderDto {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private int price;

}
