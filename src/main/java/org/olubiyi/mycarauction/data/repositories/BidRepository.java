package org.olubiyi.mycarauction.data.repositories;

import org.olubiyi.mycarauction.data.models.Auction;
import org.olubiyi.mycarauction.data.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BidRepository extends JpaRepository<Bid, UUID> {
    // ✅ Only method signature, no logic here
    Optional<Bid> findTopByAuctionOrderByAmountDesc(Auction auction);
}
