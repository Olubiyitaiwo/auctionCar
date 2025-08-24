package org.olubiyi.mycarauction.service;


import lombok.RequiredArgsConstructor;
import org.olubiyi.mycarauction.data.models.Bid;
import org.olubiyi.mycarauction.data.models.User;
import org.olubiyi.mycarauction.data.repositories.BidRepository;
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
    private final JavaMailSender mailSender;


    @Override
    public BidResponseDto createBid(BidRequestDto request) {
        Bid bid = Bid.builder()
                .itemId(request.getItemId())
                .amount(request.getAmount())
                .bidderName(request.getBidderName())
                .build();

        Bid savedBid = bidRepository.save(bid);

        return BidResponseDto.builder()
                .id(savedBid.getId())
                .itemId(savedBid.getItemId())
                .amount(savedBid.getAmount())
                .bidderName(savedBid.getBidderName())
                .build();
    }

    @Override
    public BidResponseDto getBidById(UUID id) {
        Bid bid = bidRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bid not found with id: " + id));

        return BidResponseDto.builder()
                .id(bid.getId())
                .itemId(bid.getItemId())
                .amount(bid.getAmount())
                .bidderName(bid.getBidderName())
                .build();
    }
    @Override
    public void sendBidConfirmationEmail(User user, Bid bid) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("✅ Your Bid Was Placed");
        message.setText("Hello " + user.getFirstName() + ",\n\n" +
                "Your bid of ₦" + bid.getAmount() + " has been placed successfully on auction " + bid.getAuctionId() + ".\n\n" +
                "Thank you for using MyCarAuction!");
        mailSender.send(message);
    }
}

