package org.olubiyi.mycarauction.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "items")
@Data
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String make;
    private String model;
    private int year;
    private String color;
    private Double mileage;
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "auction_id", referencedColumnName = "id", unique = true)
    private Auction auction;
}
