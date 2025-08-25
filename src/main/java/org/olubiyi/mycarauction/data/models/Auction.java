package org.olubiyi.mycarauction.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.olubiyi.mycarauction.data.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auctions")
@Getter
@Setter
public class Auction {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items item;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private BigDecimal reservedPrice;

    private BigDecimal currentPrice;

    private String seller;

    @Enumerated(EnumType.STRING)
    private Status status; // Status is an enum (e.g., Live, Closed)
}
