package org.olubiyi.mycarauction.service;


import org.olubiyi.mycarauction.data.models.Bid;
import org.olubiyi.mycarauction.data.models.User;
import org.olubiyi.mycarauction.dtos.request.BidRequestDto;
import org.olubiyi.mycarauction.dtos.response.BidResponseDto;

import java.util.UUID;

public interface BidService {
    BidResponseDto createBid(BidRequestDto request);
    BidResponseDto getBidById(UUID id);

    void sendBidConfirmationEmail(User user, Bid bid);
}

