package org.olubiyi.mycarauction.service;

import lombok.RequiredArgsConstructor;
import org.olubiyi.mycarauction.data.models.Auction;
import org.olubiyi.mycarauction.data.models.Bid;
import org.olubiyi.mycarauction.data.models.User;
import org.olubiyi.mycarauction.data.repositories.AuctionRepository;
import org.olubiyi.mycarauction.data.repositories.BidRepository;
import org.olubiyi.mycarauction.data.repositories.UserRepository;
import org.olubiyi.mycarauction.dtos.request.BidRequestDto;
import org.olubiyi.mycarauction.dtos.response.BidResponseDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final UserRepository userRepository;
    private final AuctionRepository auctionRepository;
    private final JavaMailSender mailSender;   // ✅ email sender

    @Override
    public BidResponseDto createBid(BidRequestDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Auction auction = auctionRepository.findById(request.getAuctionId())
                .orElseThrow(() -> new RuntimeException("Auction not found"));

        Bid bid = Bid.builder()
                .user(user)
                .auction(auction)
                .amount(request.getAmount())
                .build();

        bidRepository.save(bid);

        // ✅ Send instant email
        sendBidConfirmationEmail(user, auction, bid);

        return new BidResponseDto(
                bid.getId(),
                user.getId(),
                auction.getId(),
                bid.getAmount(),
                "✅ Bid placed successfully. Email sent!"
        );
    }

    @Override
    public BidResponseDto getBidById(UUID id) {
        Bid bid = bidRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bid not found"));

        return new BidResponseDto(
                bid.getId(),
                bid.getUser().getId(),
                bid.getAuction().getId(),
                bid.getAmount(),
                "✅ Bid retrieved successfully"
        );
    }

    @Override
    public void sendBidConfirmationEmail(User user, Auction auction, Bid bid) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Bid Confirmation Auction " + auction.getId());
        message.setText("Hello " + user.getFirstName() + ",\n\n"
                + "Your bid of $" + bid.getAmount() + " has been placed successfully "
                + "on auction: " + auction.getId() + ".\n\n"
                + "Thank you for using MyCarAuction!\n");

        mailSender.send(message);
    }

    @Override
    public BidResponseDto getHighestBid(Auction auctionId) {
        Bid bid = bidRepository.findTopByAuctionOrderByAmountDesc(auctionId)
                .orElseThrow(() -> new RuntimeException("No bids found for this auction"));

        return new BidResponseDto(
                bid.getId(),
                bid.getUser().getId(),
                bid.getAuction().getId(),
                bid.getAmount(),
                "✅ Highest bid retrieved successfully"
        );
    }

}
