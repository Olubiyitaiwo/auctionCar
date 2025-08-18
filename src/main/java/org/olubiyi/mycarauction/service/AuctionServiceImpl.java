package org.olubiyi.mycarauction.service;

import lombok.AllArgsConstructor;
import org.olubiyi.mycarauction.data.models.Auction;
import org.olubiyi.mycarauction.data.models.Items;
import org.olubiyi.mycarauction.data.repositories.AuctionRepository;
import org.olubiyi.mycarauction.data.repositories.ItemsRepository;
import org.olubiyi.mycarauction.dtos.request.AuctionRequestDto;
import org.olubiyi.mycarauction.dtos.response.AuctionResponseDto;
import org.olubiyi.mycarauction.exceptions.AuctionNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AuctionServiceImpl implements Auctionservice {

    private final AuctionRepository auctionRepository;
    private final ItemsRepository itemsRepository;

    @Override
    public AuctionResponseDto getAuctionById(String id) {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new AuctionNotFoundException("Auction not found with ID: " + id));

        Items item = auction.getItem();

        return new AuctionResponseDto(
                item != null ? item.getId() : null,
                auction.getStatus().name(),
                "Auction retrieved successfully"
        );
    }

    @Override
    public List<AuctionResponseDto> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();

        return auctions.stream()
                .map(auction -> new AuctionResponseDto(
                        auction.getItem() != null ? auction.getItem().getId() : null,
                        auction.getStatus().name(),
                        "Auction retrieved successfully"
                ))
                .toList(); // Java 16+, or use Collectors.toList() for older versions
    }



    @Override
    public AuctionResponseDto getAuctionByItemId(String itemId) {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new AuctionNotFoundException("Item not found with ID: " + itemId));

        Auction auction = auctionRepository.findByItem(item)
                .orElseThrow(() -> new AuctionNotFoundException("Auction not found for item ID: " + itemId));

        return new AuctionResponseDto(
                item.getId(),
                auction.getStatus().name(),
                "Auction retrieved successfully by item ID"
        );
    }

    @Override
    public AuctionResponseDto createAuction(AuctionRequestDto auctionRequestDto) {
        Auction auction = new Auction();

        LocalDateTime now = LocalDateTime.now();

        auction.setCreatedAt(now);
        auction.setReservedPrice(auctionRequestDto.getReservedPrice());
        auction.setSeller(auctionRequestDto.getSeller());
        auction.setWinner(auctionRequestDto.getSeller());

        auctionRepository.save(auction);

        return new AuctionResponseDto(
                auction.getItem() != null ? auction.getItem().getId() : null,
                auction.getStatus().name(),
                "Auction created successfully"
        );
    }
    @Override
    public AuctionResponseDto updateAuction(String auctionId, AuctionRequestDto auctionRequestDto) {
        Auction auction = new Auction();

        auctionRepository.findById(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("Auction not found with ID: " + auctionId));

        auction.setUpdatedAt(LocalDateTime.now());
        auction.setReservedPrice(auctionRequestDto.getReservedPrice());
        auction.setSeller(auctionRequestDto.getSeller());
        auction.setWinner(auctionRequestDto.getSeller());

        auctionRepository.save(auction);

        return new AuctionResponseDto(
                auction.getItem() != null ? auction.getItem().getId() : null,
                auction.getStatus().name(),
                "Auction updated successfully"
        );
    }

    @Override
    public AuctionResponseDto deleteAuction(String auctionId) {
        Auction auction = new Auction();

        auctionRepository.findById(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("Auction not found with ID: " + auctionId));

        auctionRepository.delete(auction);

        return new AuctionResponseDto(
                auction.getItem() != null ? auction.getItem().getId() : null,
                auction.getStatus().name(),
                "Deleted successfully"
        );
    }


    // You can add more methods here like createAuction, updateAuction, deleteAuction, etc.

}
