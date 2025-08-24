package org.olubiyi.mycarauction.dtos.response;


import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidResponseDto {
    private UUID id;
    private UUID itemId;
    private BigDecimal amount;
    private String bidderName;
}

