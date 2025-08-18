package org.olubiyi.mycarauction.service;

import org.olubiyi.mycarauction.data.models.Auction;
import org.olubiyi.mycarauction.dtos.request.AuctionRequestDto;
import org.olubiyi.mycarauction.dtos.response.AuctionResponseDto;

import java.util.List;

public interface Auctionservice {

    AuctionResponseDto getAuctionById(String id);

    List<AuctionResponseDto> getAllAuctions();

    AuctionResponseDto getAuctionByItemId(String itemId);

    AuctionResponseDto createAuction(AuctionRequestDto auctionRequestDto);

    AuctionResponseDto updateAuction(String auctionId, AuctionRequestDto auctionRequestDto);

    AuctionResponseDto deleteAuction(String auctionId);
}
