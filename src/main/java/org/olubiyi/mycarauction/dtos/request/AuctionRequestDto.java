package org.olubiyi.mycarauction.dtos.request;

import lombok.Data;

@Data
public class AuctionRequestDto {
    private double reservedPrice;
    private String seller;
    private double startingPrice;
    private String itemId;   // 👈 changed to String (UUID)
}

