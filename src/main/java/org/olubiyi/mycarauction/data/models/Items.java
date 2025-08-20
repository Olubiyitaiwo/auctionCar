package org.olubiyi.mycarauction.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "items")
@Data
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String make;
    private String model;
    private int year;
    private String color;
    private Double mileage;
    private String imageUrl;

    // 👇 Mapped by "item" in Auction
    @OneToOne(mappedBy = "item")
    private Auction auction;
}
