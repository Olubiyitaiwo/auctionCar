package org.olubiyi.mycarauction.dtos.request;

import lombok.Data;

@Data
public class AuctionRequestDto {
    private int reservedPrice;
    private String seller;
    private double startingPrice;
    private String itemId;   // 👈 changed to String (UUID)
}

