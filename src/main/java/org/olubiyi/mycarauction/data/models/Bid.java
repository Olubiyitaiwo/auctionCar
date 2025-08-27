package org.olubiyi.mycarauction.data.models;



import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bids")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bid {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;   // ✅ The auction this bid belongs to

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;   // ✅ The bidder

    private BigDecimal amount;  // ✅ Bid amount

    private LocalDateTime timestamp; // ✅ When the bid was placed
}




