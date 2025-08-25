package org.olubiyi.mycarauction.dtos.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AuctionRequestDto {
    private BigDecimal reservedPrice;
    private String seller;
    private BigDecimal startingPrice;
    private String itemId;   // 👈 changed to String (UUID)
}

