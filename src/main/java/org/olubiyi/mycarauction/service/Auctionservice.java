package org.olubiyi.mycarauction.service;

import org.olubiyi.mycarauction.data.models.Auction;
import org.olubiyi.mycarauction.dtos.request.AuctionRequestDto;
import org.olubiyi.mycarauction.dtos.response.AuctionResponseDto;

import java.util.List;
import java.util.UUID;

public interface Auctionservice {

    AuctionResponseDto createAuction(AuctionRequestDto auctionRequestDto);

    void closeAuction(UUID auctionId);

    AuctionResponseDto updateAuction(String auctionId, AuctionRequestDto auctionRequestDto);
}
