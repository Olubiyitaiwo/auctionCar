package org.olubiyi.mycarauction.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuctionResponseDto {
    private String itemId;
    private String status;
    private String message;
}
