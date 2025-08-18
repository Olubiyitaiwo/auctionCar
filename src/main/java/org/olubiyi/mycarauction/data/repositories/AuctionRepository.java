package org.olubiyi.mycarauction.data.repositories;

import org.olubiyi.mycarauction.data.models.Auction;
import org.olubiyi.mycarauction.data.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction, String> {
    Optional<Auction> findByItem(Items item);
}
