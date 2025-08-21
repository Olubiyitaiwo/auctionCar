package org.olubiyi.mycarauction.service;

import lombok.AllArgsConstructor;
import org.olubiyi.mycarauction.data.enums.Status;
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
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuctionServiceImpl implements Auctionservice {

    private final AuctionRepository auctionRepository;
    private final ItemsRepository itemsRepository;

    @Override
    public AuctionResponseDto getAuctionById(String id) {
        UUID uuid = UUID.fromString(id); // ✅ convert String -> UUID
        Auction auction = auctionRepository.findById(uuid)
                .orElseThrow(() -> new AuctionNotFoundException("Auction not found with ID: " + id));

        Items item = auction.getItem();

        return new AuctionResponseDto(
                (item != null) ? item.getId().toString() : null,
                (auction.getStatus() != null) ? auction.getStatus().name() : "UNKNOWN",
                "Auction retrieved successfully"
        );
    }

    @Override
    public List<AuctionResponseDto> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();

        return auctions.stream()
                .map(auction -> new AuctionResponseDto(
                        auction.getItem() != null ? auction.getItem().getId().toString() : null,
                        auction.getStatus() != null ? auction.getStatus().name() : "UNKNOWN",
                        "Auction retrieved successfully"
                ))
                .toList();
    }

    @Override
    public AuctionResponseDto getAuctionByItemId(UUID itemId) {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new AuctionNotFoundException("Item not found with ID: " + itemId));

        Auction auction = auctionRepository.findByItem(item)
                .orElseThrow(() -> new AuctionNotFoundException("Auction not found for item ID: " + itemId));

        return new AuctionResponseDto(
                item.getId().toString(),
                auction.getStatus() != null ? auction.getStatus().name() : "UNKNOWN",
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
        auction.setCurrentPrice(auctionRequestDto.getStartingPrice());

        // Set default status
        auction.setStatus(Status.Live);

        // Ensure item exists
        Items item = itemsRepository.findById(UUID.fromString(auctionRequestDto.getItemId()))
                .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + auctionRequestDto.getItemId()));

        auction.setItem(item);
        auctionRepository.save(auction);

        return new AuctionResponseDto(
                item.getId().toString(),
                auction.getStatus().name(),
                "Auction created successfully"
        );
    }


    @Override
    public AuctionResponseDto updateAuction(String auctionId, AuctionRequestDto auctionRequestDto) {
        UUID uuid = UUID.fromString(auctionId); // ✅ convert String -> UUID
        Auction auction = auctionRepository.findById(uuid)
                .orElseThrow(() -> new AuctionNotFoundException("Auction not found with ID: " + auctionId));

        auction.setUpdatedAt(LocalDateTime.now());
        auction.setReservedPrice(auctionRequestDto.getReservedPrice());
        auction.setSeller(auctionRequestDto.getSeller());
        auction.setCurrentPrice(auctionRequestDto.getStartingPrice());

        // If item is updated
        if (auctionRequestDto.getItemId() != null) {
            UUID itemUuid = UUID.fromString(auctionRequestDto.getItemId()); // ✅ convert
            Items item = itemsRepository.findById(itemUuid)
                    .orElseThrow(() -> new AuctionNotFoundException("Item not found with ID: " + auctionRequestDto.getItemId()));
            auction.setItem(item);
        }

        auctionRepository.save(auction);

        return new AuctionResponseDto(
                auction.getItem() != null ? auction.getItem().getId().toString() : null,
                auction.getStatus() != null ? auction.getStatus().name() : "UNKNOWN",
                "Auction updated successfully"
        );
    }

    @Override
    public AuctionResponseDto deleteAuction(String auctionId) {
        UUID uuid = UUID.fromString(auctionId); // ✅ convert
        Auction auction = auctionRepository.findById(uuid)
                .orElseThrow(() -> new AuctionNotFoundException("Auction not found with ID: " + auctionId));

        auctionRepository.delete(auction);

        return new AuctionResponseDto(
                auction.getItem() != null ? auction.getItem().getId().toString() : null,
                auction.getStatus() != null ? auction.getStatus().name() : "UNKNOWN",
                "Deleted successfully"
        );
    }
}
