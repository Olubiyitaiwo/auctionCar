package org.olubiyi.mycarauction.dtos.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AuctionRequestDto {
    private int reservedPrice;
    private String seller;
    private double startingPrice;
    private Long itemId; // ID of the item to link
}
