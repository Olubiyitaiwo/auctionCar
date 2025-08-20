package org.olubiyi.mycarauction.data.models;

import jakarta.persistence.*;
import lombok.Data;
import org.olubiyi.mycarauction.data.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auctions")
@Data
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double reservedPrice;
    private String seller;
    private String winner;
    private double soldPrice;
    private double currentPrice;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    // 👇 Auction owns the relationship (FK = item_id in auctions table)
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", unique = true)
    private Items item;
}
