package org.olubiyi.mycarauction.service;

import lombok.AllArgsConstructor;
import org.olubiyi.mycarauction.data.enums.Status;
import org.olubiyi.mycarauction.data.models.Auction;
import org.olubiyi.mycarauction.data.models.Bid;
import org.olubiyi.mycarauction.data.models.Items;
import org.olubiyi.mycarauction.data.models.User;
import org.olubiyi.mycarauction.data.repositories.AuctionRepository;
import org.olubiyi.mycarauction.data.repositories.BidRepository;
import org.olubiyi.mycarauction.data.repositories.ItemsRepository;
import org.olubiyi.mycarauction.data.repositories.UserRepository;
import org.olubiyi.mycarauction.dtos.request.AuctionRequestDto;
import org.olubiyi.mycarauction.dtos.response.AuctionResponseDto;
import org.olubiyi.mycarauction.exceptions.AuctionNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuctionServiceImpl implements Auctionservice {

    private final AuctionRepository auctionRepository;
    private final ItemsRepository itemsRepository;
    private final UserRepository userRepository;
    private final BidRepository bidRepository;
    private final EmailService emailService;

    @Override
    public AuctionResponseDto createAuction(AuctionRequestDto auctionRequestDto) {
        // ✅ Validate item exists
        Items item = itemsRepository.findById(UUID.fromString(auctionRequestDto.getItemId()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "❌ Item not found with ID: " + auctionRequestDto.getItemId()
                ));

        // ✅ Validate seller exists
        User seller = userRepository.findById(UUID.fromString(auctionRequestDto.getSellerId()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "❌ Seller not found with ID: " + auctionRequestDto.getSellerId()
                ));

        // ✅ Build auction
        Auction auction = new Auction();
        auction.setStartTime(LocalDateTime.now());
        auction.setReservedPrice(auctionRequestDto.getReservedPrice());
        auction.setCurrentPrice(auctionRequestDto.getStartingPrice());
        auction.setStatus(Status.Live);
        auction.setItem(item);
        auction.setSeller(seller); // must not be null

        auctionRepository.save(auction);

        return new AuctionResponseDto(
                item.getId().toString(),
                auction.getStatus().name(),
                seller.getUsername(),
                null,
                "✅ Auction created successfully"
        );
    }

    @Override
    public void closeAuction(UUID auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("❌ Auction not found with ID: " + auctionId));

        Bid highestBid = bidRepository.findTopByAuctionOrderByAmountDesc(auction)
                .orElseThrow(() -> new RuntimeException("❌ No bids placed for this auction"));

        auction.setWinner(highestBid.getUser());
        auction.setCurrentPrice(highestBid.getAmount());
        auction.setStatus(Status.Closed);
        auctionRepository.save(auction);

        // ✅ Notify winner
        User winner = highestBid.getUser();
        String fullName = winner.getFirstName() + " " + winner.getLastName();
        String itemName = auction.getItem().getYear() + " " +
                auction.getItem().getMake() + " " +
                auction.getItem().getModel();

        emailService.sendEmail(
                winner.getEmail(),
                "🎉 Congratulations! You won the auction",
                "Dear " + fullName +
                        ",\n\nYou won the auction for: " + itemName +
                        " with a bid of $" + highestBid.getAmount() + ".\n\nCongratulations!"
        );
    }

    @Override
    public AuctionResponseDto updateAuction(String auctionId, AuctionRequestDto auctionRequestDto) {
        UUID uuid = UUID.fromString(auctionId);
        Auction auction = auctionRepository.findById(uuid)
                .orElseThrow(() -> new AuctionNotFoundException("❌ Auction not found with ID: " + auctionId));

        // ✅ Update prices
        auction.setReservedPrice(auctionRequestDto.getReservedPrice());
        auction.setCurrentPrice(auctionRequestDto.getStartingPrice());

        // ✅ Update seller if provided
        if (auctionRequestDto.getSellerId() != null) {
            UUID sellerId = UUID.fromString(auctionRequestDto.getSellerId());
            User seller = userRepository.findById(sellerId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "❌ Seller not found with ID: " + auctionRequestDto.getSellerId()
                    ));
            auction.setSeller(seller);
        }

        // ✅ Update item if provided
        if (auctionRequestDto.getItemId() != null) {
            UUID itemUuid = UUID.fromString(auctionRequestDto.getItemId());
            Items item = itemsRepository.findById(itemUuid)
                    .orElseThrow(() -> new AuctionNotFoundException(
                            "❌ Item not found with ID: " + auctionRequestDto.getItemId()
                    ));
            auction.setItem(item);
        }

        auctionRepository.save(auction);

        return new AuctionResponseDto(
                auction.getItem() != null ? auction.getItem().getId().toString() : null,
                auction.getStatus() != null ? auction.getStatus().name() : "UNKNOWN",
                auction.getSeller() != null ? auction.getSeller().getUsername() : null,
                auction.getWinner() != null ? auction.getWinner().getUsername() : null,
                "✅ Auction updated successfully"
        );
    }
}
