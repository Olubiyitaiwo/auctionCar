package org.olubiyi.mycarauction.service;

import lombok.RequiredArgsConstructor;
import org.olubiyi.mycarauction.data.enums.Status;
import org.olubiyi.mycarauction.data.models.Auction;
import org.olubiyi.mycarauction.data.models.Bid;
import org.olubiyi.mycarauction.data.models.User;
import org.olubiyi.mycarauction.data.repositories.AuctionRepository;
import org.olubiyi.mycarauction.data.repositories.BidRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionSchedulerService {

    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;
    private final JavaMailSender mailSender;

    @Scheduled(fixedRate = 60000) // runs every 1 min
    public void closeExpiredAuctions() {
        List<Auction> expiredAuctions = auctionRepository
                .findByStatusAndEndTimeBefore(Status.Live, LocalDateTime.now());

        for (Auction auction : expiredAuctions) {
            Optional<Bid> highestBid = bidRepository
                    .findTopByAuctionIdOrderByAmountDesc(auction.getId());

            if (highestBid.isPresent()) {
                Bid bid = highestBid.get();
                User winner = bid.getUser();

                // send email to winner
                sendWinnerEmail(winner.getEmail(), auction);

                // send email to seller
                sendSellerEmail(auction.getSeller(), winner, auction);

                // mark auction as closed
                auction.setStatus(Status.Closed);
                auction.setCurrentPrice(bid.getAmount());
                auctionRepository.save(auction);
            } else {
                // no bids placed -> just close auction
                auction.setStatus(Status.Closed);
                auctionRepository.save(auction);
            }
        }
    }

    private void sendWinnerEmail(String email, Auction auction) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("🎉 Congratulations! You won the auction");
        message.setText("Dear Bidder,\n\n" +
                "Congratulations! You won the auction for item: " + auction.getItem().getMake() +
                " at a final price of $" + auction.getCurrentPrice() + ".\n\n" +
                "Please contact the seller to complete the transaction.\n\n" +
                "Best regards,\nCar Auction Team");

        mailSender.send(message);
    }

    private void sendSellerEmail(String sellerEmail, User winner, Auction auction) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sellerEmail);
        message.setSubject("📢 Your item has been sold!");
        message.setText("Hello,\n\n" +
                "Your item: " + auction.getItem().getMake() +
                " has been successfully auctioned.\n\n" +
                "Winner: " + winner.getFirstName() + " " + winner.getLastName() +
                "\nEmail: " + winner.getEmail() +
                "\nFinal Price: $" + auction.getCurrentPrice() + "\n\n" +
                "Best regards,\nCar Auction Team");

        mailSender.send(message);
    }
}

