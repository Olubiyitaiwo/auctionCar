package org.olubiyi.mycarauction.dtos.request;

import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidRequestDto {
    private UUID itemId;
    private BigDecimal amount;
    private String bidderName;
}

