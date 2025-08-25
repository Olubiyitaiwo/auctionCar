package org.olubiyi.mycarauction.dtos.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class BidRequestDto {
    private UUID userId;      // ✅ who is bidding
    private UUID auctionId;   // ✅ auction being bid on
    private BigDecimal amount;
}


