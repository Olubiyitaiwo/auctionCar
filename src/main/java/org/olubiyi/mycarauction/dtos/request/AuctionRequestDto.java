package org.olubiyi.mycarauction.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AuctionRequestDto {
    private String itemId;
    private String sellerId; // ✅ seller is a User, not a String
    private BigDecimal reservedPrice;
    private BigDecimal startingPrice;
}


