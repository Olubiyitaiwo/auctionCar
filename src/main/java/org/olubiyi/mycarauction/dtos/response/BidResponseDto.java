package org.olubiyi.mycarauction.dtos.response;



import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BidResponseDto {
    private UUID bidId;
    private UUID userId;
    private UUID auctionId;
    private BigDecimal amount;
    private String message;
}


