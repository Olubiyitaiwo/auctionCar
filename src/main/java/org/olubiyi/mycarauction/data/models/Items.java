package org.olubiyi.mycarauction.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Items {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    private String color;

    private double mileage;

    private String imageUrl;
}
