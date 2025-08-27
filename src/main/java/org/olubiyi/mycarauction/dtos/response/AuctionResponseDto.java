package org.olubiyi.mycarauction.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuctionResponseDto {
    private String itemId;
    private String status;
    private String sellerName;  // ✅ seller details
    private String winnerName;  // ✅ winner details
    private String message;
}
