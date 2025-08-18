package org.olubiyi.mycarauction.data.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.olubiyi.mycarauction.data.enums.Status;
import java.time.LocalDateTime;

@Entity
@Table(name = "auctions")
@Data
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int reservedPrice;
    private String seller;
    private String winner;
    private double soldPrice;
    private double currentPrice;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "auction", cascade = CascadeType.ALL)
    private Items item;
}
